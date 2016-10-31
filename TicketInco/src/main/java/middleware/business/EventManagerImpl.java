package middleware.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import middleware.dal.Book;
import middleware.dal.EventBook;
import middleware.dal.EventConfirmation;
import middleware.dal.EventDatasource;
import middleware.dal.EventTicket;
import middleware.dal.EventTicketsDatasource;

public class EventManagerImpl implements EventManager {
	
	private static final Logger logger = LoggerFactory.getLogger(EventManagerImpl.class);

	private EventDatasource eventDatasource;
	private EventTicketsDatasource eventTicketsDatasource;	

	@Override
	public Event getScheduleForEvent(long eventId, Date eventDate){
		logger.debug("getScheduleForEvent->" + "eventId:" + eventId + " eventDate:" + eventDate.toString());
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
				EventAvailability availability = new EventAvailability();
				availability.setSector(eventTicket.getSector());
				availability.setQuantity(eventTicket.getQuantity());
				availability.setPrice(eventTicket.getPrice());
				
				eventAvailabilities.add(availability);
			}
			eventSchedule.setAvailabilities(eventAvailabilities);
			
			eventSchedules.add(eventSchedule);
		}
		
		eventForDate.setEventSchedules(eventSchedules);
		logger.debug("eventForDate->" + eventSchedules.toString());
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
				if (!checkAvailability(eventAvailability, eventTicket, eventBooks, eventConfirmations))
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
			List<EventConfirmation> confirmations) {
		
		int bookQuantity = 0;
		for (EventBook book : books) {
			bookQuantity += book.getQuantity();
		}
		
		int confirmationQuantity = 0;
		for (EventConfirmation confirmation : confirmations) {
			confirmationQuantity += confirmation.getQuantity();
		}
		
		int remainingTickets = eventTicket.getQuantity() - bookQuantity - confirmationQuantity;
		return eventAvailability.getQuantity() <= remainingTickets;
	}
	
	@Override
	@Scheduled(initialDelayString="${cron.timer}", fixedDelayString = "${cron.timer}")
	public void cleanBooks() {
		logger.debug("Limpiar reservas");	
		eventTicketsDatasource.removeAllBooks();
	}
	
	@Scheduled(initialDelayString="${log.timer}", fixedDelayString = "${log.timer}")
	public void logState(){
		logger.debug("Tickets: " + eventTicketsDatasource.getAllTickets());
		logger.debug("Books:" + eventTicketsDatasource.getAllBooks());
		logger.debug("confirmations:" + eventTicketsDatasource.getAllConfirmations());
	}

	public void setEventDatasource(EventDatasource eventDatasource) {
		this.eventDatasource = eventDatasource;
	}

	public void setEventTicketsDatasource(EventTicketsDatasource eventTicketsDatasource) {
		this.eventTicketsDatasource = eventTicketsDatasource;
	}
}
