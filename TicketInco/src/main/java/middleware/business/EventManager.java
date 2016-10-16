package middleware.business;

import java.util.Date;
import java.util.List;

public interface EventManager {
	
	public Event getScheduleForEvent(long eventId, Date eventDate);
	
	public long bookTickets(long eventId, Date eventDate, List<EventSchedule> eventSchedules) throws Exception;
	
	public int getBookState(long bookId) throws Exception;

}
