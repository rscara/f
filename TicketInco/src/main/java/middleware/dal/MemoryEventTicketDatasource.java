package middleware.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemoryEventTicketDatasource implements EventTicketsDatasource {
	
	private Map<Long, List<EventTicket>> ticketsByEvent;

	@Override
	public List<EventTicket> getTicketsForEvent(long eventId) {
		List<EventTicket> ticketsForEvent = new ArrayList<EventTicket>();
		List<EventTicket> tickets = ticketsByEvent.get(eventId);
		if (tickets!=null) {
			ticketsForEvent.addAll(tickets);
		}
		return ticketsForEvent;
	}

	public void setTicketsByEvent(Map<Long, List<EventTicket>> ticketsByEvent) {
		this.ticketsByEvent = ticketsByEvent;
	}

}
