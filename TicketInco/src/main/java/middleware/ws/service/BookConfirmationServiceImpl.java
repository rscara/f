package middleware.ws.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.xml.ws.soap.Addressing;

import org.apache.cxf.attachment.ByteDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import middleware.business.EventManager;
import middleware.dal.EventBook;
import middleware.ws.BookConfirmationRequest;
import middleware.ws.BookConfirmationResponse;
import wsdl.local.LocalPaymentAuthorizor;
import wsdl.local.LocalPaymentRequest;
import wsdl.local.LocalPaymentResponse;
import wsdl.pagoya.PagosYaPaymentAuthorizor;
import wsdl.pagoya.PagosYaPaymentRequest;
import wsdl.pagoya.PagosYaPaymentResponse;

@Addressing
@WebService(endpointInterface = "middleware.ws.service.BookConfirmationService", name = "BookConfirmationService", targetNamespace = "http://ticketinco.com/")
public class BookConfirmationServiceImpl implements BookConfirmationService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookConfirmationServiceImpl.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	private SimpleDateFormat onlydateformat = new SimpleDateFormat("dd-MM-yyyy");
	private SimpleDateFormat onlyhourformat = new SimpleDateFormat("HH:mm");

	private DecimalFormat df = new DecimalFormat("###.##");
	
	private PagosYaPaymentAuthorizor pagosYaPaymentAuthorizor;
	private LocalPaymentAuthorizor localPaymentAuthorizor;
	
	private EventManager eventManager;

	@Override
	public BookConfirmationResponse confirmBook(BookConfirmationRequest bookConfirmationRequest) throws Exception {
		
		logger.debug("Request:" + bookConfirmationRequest);
		
		try {
			int bookstate = eventManager.getBookState(bookConfirmationRequest.getBookId());
			if (bookstate!=1) {
				logger.error("Estado de reserva invalido: book=" + bookConfirmationRequest.getBookId() + " estado=" + bookstate);
				throw new Exception("Estado de reserva invalido: book=" + bookConfirmationRequest.getBookId() + " estado=" + bookstate);
			}
		} catch (Exception e) {
			logger.error("Error consultando books:" + bookConfirmationRequest.getBookId(), e);
			throw new Exception("Error consultando books:" + bookConfirmationRequest.getBookId(), e);
		}
		
		List<EventBook> eventBooks = eventManager.getBooksByBookId(bookConfirmationRequest.getBookId());
		
		double totalPrice = 0;
		long confirmationNumber = 0;
		for (EventBook eventBook : eventBooks) {
			totalPrice += eventBook.getPrice() * eventBook.getQuantity();
		}
		
		if (bookConfirmationRequest.getPaymentModeId()==1) {
			PagosYaPaymentRequest request = new PagosYaPaymentRequest();
			request.setAmount(totalPrice);
			request.setCheckDigit(bookConfirmationRequest.getCreditCardCheckDigit());
			request.setCreditCardNumber(Long.parseLong(bookConfirmationRequest.getCreditCardNumber()));
			request.setExpiration(sdf.format(bookConfirmationRequest.getCreditCardExpiration()));
			
			try {
				logger.debug("Autorizando PagosYa:" + request);
				//ver errores!
				PagosYaPaymentResponse response = pagosYaPaymentAuthorizor.authorizePayment(request);
				logger.debug("Respuesta PagosYa:" + response);
				confirmationNumber = response.getConfirmationNumber();
			} catch (Exception e) {
				throw new Exception("Error autorizando con PagosYa", e);
			}			
		}
		else if (bookConfirmationRequest.getPaymentModeId()==2) {
			LocalPaymentRequest request = new LocalPaymentRequest();
			request.setAmount(df.format(totalPrice));
			request.setCheckDigit(Integer.toString(bookConfirmationRequest.getCreditCardCheckDigit()));
			request.setExpiration(sdf.format(bookConfirmationRequest.getCreditCardExpiration()));
			request.setCreditCardNumber(bookConfirmationRequest.getCreditCardNumber());
			
			try {
				logger.debug("Autorizando pago Local:" + request);
				//ver errores!
				LocalPaymentResponse response = localPaymentAuthorizor.authorizePayment(request);
				logger.debug("Respuesta pago Local:" + response);
				confirmationNumber = response.getConfirmationNumber();
			} catch (Exception e) {
				throw new Exception("Error autorizando con pago Local", e);
			}
		}
		else
			throw new Exception("Error: Medio de pago no reconocido");
		
		BookConfirmationResponse bookConfirmationResponse = new BookConfirmationResponse();
		bookConfirmationResponse.setBookId(bookConfirmationRequest.getBookId());
		
		List<DataHandler> images = new ArrayList<DataHandler>();

		eventManager.confirmBooks(eventBooks);
		
		for (EventBook eventBook : eventBooks) {
			
			
			for (int i = 0; i < eventBook.getQuantity(); i++) {
				BufferedImage baseTicketImage = ImageIO.read(new File("ticket.png"));
				Graphics g = baseTicketImage.getGraphics();
				g.setFont(Font.getFont(Font.SANS_SERIF));
				g.setFont(g.getFont().deriveFont(20f));
				g.setColor(Color.BLACK);
				g.drawString("Reserva #: " + bookConfirmationRequest.getBookId(), 40, 120);
				g.drawString("Evento #: " + eventBook.getEventId() + " Sector: " + eventBook.getSector(), 40, 150);
				g.drawString("Fecha y hora: " 
						+ onlydateformat.format(eventBook.getEventDate()) + " " + onlyhourformat.format(eventBook.getHour()), 40, 180);
				if (bookConfirmationRequest.getPaymentModeId()==1) {
					g.drawString("Autorizado por PagoYa, #Tarjeta:" + 
							bookConfirmationRequest.getCreditCardNumber(), 40, 210);
				}
				else {
					g.drawString("Autorizado por TicketINCO", 40, 210);
				}
				g.drawString("#Tarjeta:" + bookConfirmationRequest.getCreditCardNumber(), 40, 240);
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
		bookConfirmationResponse.setImages(images);
		return bookConfirmationResponse;	
		
	}

	public void setPagosYaPaymentAuthorizor(PagosYaPaymentAuthorizor pagosYaPaymentAuthorizor) {
		this.pagosYaPaymentAuthorizor = pagosYaPaymentAuthorizor;
	}

	public void setLocalPaymentAuthorizor(LocalPaymentAuthorizor localPaymentAuthorizor) {
		this.localPaymentAuthorizor = localPaymentAuthorizor;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

}
