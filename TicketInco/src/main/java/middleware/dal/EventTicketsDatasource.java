package middleware.dal;

import java.util.List;

public interface EventTicketsDatasource {
	
	public List<EventTicket> getTicketsForEvent(long eventId);
}
