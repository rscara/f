package middleware.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import middleware.dal.EventDatasource;
import middleware.dal.EventTicket;
import middleware.dal.EventTicketsDatasource;

public class EventManagerImpl implements EventManager {
	
	private EventDatasource eventDatasource;
	private EventTicketsDatasource eventTicketsDatasource;	

	@Override
	public Event getScheduleForEvent(long eventId) throws Exception{
		middleware.dal.Event event = eventDatasource.getEvent(eventId);
		if (event==null)
			throw new Exception("No se encuentra el evento con id:" + eventId);
		
		List<EventTicket> tickets = eventTicketsDatasource.getTicketsForEvent(event.getId());
		
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
		
		return eventForDate;
	}
	
	@Override
	public long bookTickets(long eventId, Date eventDate, List<EventSchedule> eventSchedules) throws Exception {
		return 0;
	}

	public void setEventDatasource(EventDatasource eventDatasource) {
		this.eventDatasource = eventDatasource;
	}

	public void setEventTicketsDatasource(EventTicketsDatasource eventTicketsDatasource) {
		this.eventTicketsDatasource = eventTicketsDatasource;
	}
}
