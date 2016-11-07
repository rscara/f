package middleware.business;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.imageio.ImageIO;

import org.apache.cxf.attachment.ByteDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import com.thoughtworks.xstream.XStream;

import middleware.dal.Book;
import middleware.dal.EventBook;
import middleware.dal.EventBookVoid;
import middleware.dal.EventConfirmation;
import middleware.dal.EventDatasource;
import middleware.dal.EventTicket;
import middleware.dal.EventTicketsDatasource;
import wsdl.local.LocalPaymentAuthorizor;
import wsdl.local.LocalPaymentRequest;
import wsdl.local.LocalPaymentResponse;
import wsdl.local.LocalVoidRequest;
import wsdl.pagoya.PagosYaPaymentAuthorizor;
import wsdl.pagoya.PagosYaPaymentRequest;
import wsdl.pagoya.PagosYaPaymentResponse;
import wsdl.pagoya.PagosYaVoidRequest;
import wsdl.pagoya.PagosYaVoidResponse;

public class EventManagerImpl implements EventManager {
	
	private static final Logger logger = LoggerFactory.getLogger(EventManagerImpl.class);

	private EventDatasource eventDatasource;
	private EventTicketsDatasource eventTicketsDatasource;
	
	private PagosYaPaymentAuthorizor pagosYaPaymentAuthorizor;
	private LocalPaymentAuthorizor localPaymentAuthorizor;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	private DecimalFormat df = new DecimalFormat("###.##");
	private SimpleDateFormat onlydateformat = new SimpleDateFormat("dd-MM-yyyy");
	private SimpleDateFormat onlyhourformat = new SimpleDateFormat("HH:mm");
	
	private XStream xstream = new XStream();

	@Override
	public Event getScheduleForEvent(long eventId, Date eventDate){
		middleware.dal.Event event = eventDatasource.getEvent(eventId, eventDate);
		if (event==null)
			return null;
		
		List<EventTicket> tickets = eventTicketsDatasource.getTicketsForEvent(event.getId(), event.getDate());
		
		Map<Date, List<EventTicket>> ticketsByHour = new HashMap<Date, List<EventTicket>>();
		for (EventTicket eventTicket : tickets) {
			if (!ticketsByHour.containsKey(eventTicket.getHour())){
				ticketsByHour.put(eventTicket.getHour(), new ArrayList<EventTicket>());
			}
			ticketsByHour.get(eventTicket.getHour()).add(eventTicket);
		}
		
		Event eventForDate = new Event();
		eventForDate.setEventId(event.getId());
		eventForDate.setEventDate(event.getDate());
		
		List<EventSchedule> eventSchedules = new ArrayList<EventSchedule>();
		
		for (Date hour : ticketsByHour.keySet()) {
			EventSchedule eventSchedule = new EventSchedule();
			eventSchedule.setHour(hour);
			
			List<EventAvailability> eventAvailabilities = new ArrayList<EventAvailability>();
			for (EventTicket eventTicket : ticketsByHour.get(hour)) {
				
				List<EventBook> books = eventTicketsDatasource.getBooksForEventHourAndSector(
						event.getId(), event.getDate(), eventTicket.getHour(), eventTicket.getSector());
				int bookQuantity = 0;
				for (EventBook eventBook : books) {
					bookQuantity += eventBook.getQuantity();
				}
				
				int confirmationQuantity = 0;
				List<EventConfirmation> confirmations = eventTicketsDatasource.getConfirmationsForEventHourAndSector(
						event.getId(), event.getDate(), eventTicket.getHour(), eventTicket.getSector());
				for (EventConfirmation eventConfirmation : confirmations) {
					confirmationQuantity += eventConfirmation.getQuantity();
				}
				EventAvailability availability = new EventAvailability();
				availability.setSector(eventTicket.getSector());
				availability.setQuantity(eventTicket.getQuantity() - bookQuantity - confirmationQuantity);
				availability.setPrice(eventTicket.getPrice());
				
				eventAvailabilities.add(availability);
			}
			eventSchedule.setAvailabilities(eventAvailabilities);
			
			eventSchedules.add(eventSchedule);
		}
		
		eventForDate.setEventSchedules(eventSchedules);
		return eventForDate;
	}
	
	@Override
	public long bookTickets(long eventId, Date eventDate, List<EventSchedule> eventSchedules) throws Exception {
		middleware.dal.Event event = eventDatasource.getEvent(eventId, eventDate);
		if (event==null)
			throw new Exception("No existe el evento con id=" + eventId + " fecha:" + eventDate.toString());
		
		for (EventSchedule eventSchedule : eventSchedules) {
			for (EventAvailability eventAvailability : eventSchedule.getAvailabilities()) {
				EventTicket eventTicket = eventTicketsDatasource.getTicketForEventHourAndSector(
						eventId, eventDate, eventSchedule.getHour(), eventAvailability.getSector());

				if (eventTicket==null)
					throw new Exception("No existe el evento:" + eventId + " hora:" + eventSchedule.getHour().toString() + "sector:" + 
							eventAvailability.getSector());
				
				List<EventBook> eventBooks = eventTicketsDatasource.getBooksForEventHourAndSector(
						eventId, eventDate, eventSchedule.getHour(), eventAvailability.getSector());
				List<EventConfirmation> eventConfirmations = eventTicketsDatasource.getConfirmationsForEventHourAndSector(
						eventId, eventDate, eventSchedule.getHour(), eventAvailability.getSector());
				List<EventBookVoid> eventVoids = eventTicketsDatasource.getVoidsForEventHourAndSector(eventId, eventDate, 
						eventSchedule.getHour(), eventAvailability.getSector());
				
				if (!checkAvailability(eventAvailability, eventTicket, eventBooks, eventConfirmations, eventVoids))
					throw new Exception("No hay lugares para el evento:" + eventId + " hora:" + eventSchedule.getHour().toString() + "sector:" + 
							eventAvailability.getSector());
			}
		}
		long bookId = eventTicketsDatasource.addBooks(eventId, eventDate, eventSchedules);		
		return bookId;
	}
	
	@Override
	public int getBookState(long bookId) throws Exception {
		Book book = eventTicketsDatasource.getBookById(bookId);
		if (book!=null)
			return book.getState();
		else
			throw new Exception("No existe la reserva con id:" + bookId);
	}
	
	//chequea si existen lugares libres para un evento/hora/sector
	private boolean checkAvailability(EventAvailability eventAvailability, EventTicket eventTicket, List<EventBook> books, 
			List<EventConfirmation> confirmations, List<EventBookVoid> voids) {
		
		int bookQuantity = 0;
		for (EventBook book : books) {
			bookQuantity += book.getQuantity();
		}
		
		int confirmationQuantity = 0;
		for (EventConfirmation confirmation : confirmations) {
			confirmationQuantity += confirmation.getQuantity();
		}
		
		int voidsQuantity = 0;
		for (EventBookVoid bookVoid : voids) {
			voidsQuantity += bookVoid.getQuantity();
		}
		
		int remainingTickets = eventTicket.getQuantity() - bookQuantity - confirmationQuantity + voidsQuantity;
		return eventAvailability.getQuantity() <= remainingTickets;
	}
	
	@Override
	@Scheduled(initialDelayString="${cron.timer}", fixedDelayString = "${cron.timer}")
	public void cleanBooks() {
		logger.debug("Limpiar reservas");	
		eventTicketsDatasource.removeAllBooks();
	}

	@Override
	public List<EventBook> getBooksByBookId(long bookId) {
		return eventTicketsDatasource.getEventBooksByBookId(bookId);
	}

	@Override
	public List<DataHandler> confirmBooks(long bookId, long paymentModeId, String creditCardNumber, int creditCardCheckDigit, 
			Date creditCardExpiration) throws Exception {
		try {
			int bookstate = getBookState(bookId);
			if (bookstate!=1) {
				logger.error("Estado de reserva invalido: book=" + bookId + " estado=" + bookstate);
				throw new Exception("Estado de reserva invalido: book=" + bookId + " estado=" + bookstate);
			}
		} catch (Exception e) {
			logger.error("Error consultando books:" + bookId, e);
			throw new Exception("Error consultando books:" + bookId, e);
		}
		
		List<EventBook> eventBooks = eventTicketsDatasource.getEventBooksByBookId(bookId);
		
		double totalPrice = 0;
		long confirmationNumber = 0;
		for (EventBook eventBook : eventBooks) {
			totalPrice += eventBook.getPrice() * eventBook.getQuantity();
		}
		
		if (paymentModeId==1) {
			PagosYaPaymentRequest request = new PagosYaPaymentRequest();
			request.setAmount(totalPrice);
			request.setCheckDigit(creditCardCheckDigit);
			request.setCreditCardNumber(Long.parseLong(creditCardNumber));
			request.setExpiration(sdf.format(creditCardExpiration));
			
			try {
				logger.info("Autorizando PagosYa:");
				logger.info(xstream.toXML(request));
				//ver errores!
				PagosYaPaymentResponse response = pagosYaPaymentAuthorizor.authorizePayment(request);
				logger.info("Respuesta PagosYa:");
				logger.info(xstream.toXML(response));
				confirmationNumber = response.getConfirmationNumber();
			} catch (Exception e) {
				logger.error("Error autorizando con PagosYa", e);
				throw new Exception("Error autorizando con PagosYa", e);
			}
		}
		else if (paymentModeId==2) {
			LocalPaymentRequest request = new LocalPaymentRequest();
			request.setAmount(df.format(totalPrice));
			request.setCheckDigit(Integer.toString(creditCardCheckDigit));
			request.setExpiration(sdf.format(creditCardExpiration));
			request.setCreditCardNumber(creditCardNumber);
			
			try {
				logger.info("Autorizando pago Local:");
				logger.info(xstream.toXML(request));

				//ver errores!
				LocalPaymentResponse response = localPaymentAuthorizor.authorizePayment(request);
				logger.info("Respuesta pago Local:");
				logger.info(xstream.toXML(response));
				//le pongo numero de autorizacion trucha ya que no hay respuesta en la autorizacion de pago
				confirmationNumber = eventTicketsDatasource.getLocalAuthorizationSequence();
			} catch (Exception e) {
				logger.error("Error autorizando con pago Local", e);
				throw new Exception("Error autorizando con pago Local", e);
			}
		}
		else {
			logger.error("Error: Medio de pago no reconocido");
			throw new Exception("Error: Medio de pago no reconocido");
		}

		for (EventBook eventBook : eventBooks) {
			eventTicketsDatasource.addConfirmation(eventBook.getEventId(), eventBook.getEventDate(), eventBook.getHour(), eventBook.getSector(), 
					eventBook.getQuantity(), eventBook.getBookId(), eventBook.getPrice(), confirmationNumber, paymentModeId);
			eventTicketsDatasource.changeBookState(eventBook.getBookId(), 2);
			eventTicketsDatasource.removeEventBooks(eventBook.getBookId());
		}
		
		List<DataHandler> images = new ArrayList<DataHandler>();
		for (EventBook eventBook : eventBooks) {
			for (int i = 0; i < eventBook.getQuantity(); i++) {
				BufferedImage baseTicketImage = ImageIO.read(new File("ticket.png"));
				Graphics g = baseTicketImage.getGraphics();
				g.setFont(Font.getFont(Font.SANS_SERIF));
				g.setFont(g.getFont().deriveFont(20f));
				g.setColor(Color.BLACK);
				g.drawString("Reserva #: " + bookId, 40, 120);
				g.drawString("Evento #: " + eventBook.getEventId() + " Sector: " + eventBook.getSector(), 40, 150);
				g.drawString("Fecha y hora: " 
						+ onlydateformat.format(eventBook.getEventDate()) + " " + onlyhourformat.format(eventBook.getHour()), 40, 180);
				if (paymentModeId==1) {
					g.drawString("Autorizado por PagoYa" , 40, 210);
				}
				else {
					g.drawString("Autorizado por TicketINCO", 40, 210);
				}
				g.drawString("#Tarjeta:" + creditCardNumber, 40, 240);
				g.drawString("TOTAL $ " + df.format(eventBook.getPrice()) + " Cod. Aut.:" + confirmationNumber, 40, 270);
				g.dispose();
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(baseTicketImage, "png", baos);
				baos.flush();
				byte[] imageInByte = baos.toByteArray();
				baos.close();
				
				DataSource source = new ByteDataSource(imageInByte);
				images.add(new DataHandler(source));
			}
		}
		return images;
	}
	
	@Override
	public int voidConfirmation(long paymentConfirmationId, long paymentMode) throws Exception{
		List<EventConfirmation> confirmationsForPayment = eventTicketsDatasource.getConfirmationsByPayment(paymentConfirmationId, paymentMode);
		
		if (confirmationsForPayment.isEmpty())
			throw new Exception("No hay registro de pago con confirmacion:" + paymentConfirmationId + " y tipo de pago:" + paymentMode);
		
		List<EventBookVoid> voidForPayment = eventTicketsDatasource.getVoidByPayment(paymentConfirmationId, paymentMode);
		
		if (!voidForPayment.isEmpty())
			throw new Exception("El pago ya fue anulado confirmacion:" + paymentConfirmationId + " y tipo de pago:" + paymentMode);
		
		int voidConfirmationId = 0;
		if (paymentMode==1) {
			PagosYaVoidRequest request = new PagosYaVoidRequest();
			request.setConfirmationNumber(paymentConfirmationId);
			
			try {
				PagosYaVoidResponse response = pagosYaPaymentAuthorizor.voidPayment(request);
				voidConfirmationId = response.getConfirmationNumber();
			} catch (Exception e) {
				logger.error("Error autorizando anulacion de PagoYa para a confirmacion:" + paymentConfirmationId, e);
				throw new Exception("Error autorizando anulacion de PagoYa para a confirmacion:" + paymentConfirmationId);
			}
		}
		else {
			LocalVoidRequest request = new LocalVoidRequest();
			request.setConfirmationNumber(Long.toString(paymentConfirmationId));
			
			try {
				localPaymentAuthorizor.voidPayment(request);
				//le pongo el mismo numero de autorizacion que el pago ya que no tiene respuesta de la anulacion
				voidConfirmationId = (int)paymentConfirmationId;
			} catch (Exception e) {
				logger.error("Error autorizando anulacion de PagoYa para a confirmacion:" + paymentConfirmationId, e);
				throw new Exception("Error autorizando anulacion de PagoYa para a confirmacion:" + paymentConfirmationId);
			}
		}
		
		for (EventConfirmation eventConfirmation : confirmationsForPayment) {
			eventTicketsDatasource.addBookVoid(eventConfirmation.getEventId(), eventConfirmation.getEventDate(), 
					eventConfirmation.getHour(), eventConfirmation.getSector(), eventConfirmation.getQuantity(), eventConfirmation.getPrice(), 
					eventConfirmation.getBookId(), voidConfirmationId, paymentMode, paymentConfirmationId);			
		}
		return voidConfirmationId;
	}

	public void setEventDatasource(EventDatasource eventDatasource) {
		this.eventDatasource = eventDatasource;
	}

	public void setEventTicketsDatasource(EventTicketsDatasource eventTicketsDatasource) {
		this.eventTicketsDatasource = eventTicketsDatasource;
	}

	public void setPagosYaPaymentAuthorizor(PagosYaPaymentAuthorizor pagosYaPaymentAuthorizor) {
		this.pagosYaPaymentAuthorizor = pagosYaPaymentAuthorizor;
	}

	public void setLocalPaymentAuthorizor(LocalPaymentAuthorizor localPaymentAuthorizor) {
		this.localPaymentAuthorizor = localPaymentAuthorizor;
	}
}
