package middleware.business;

import java.util.Date;
import java.util.List;

public interface EventManager {
	
	public Event getScheduleForEvent(long eventId) throws Exception;
	
	public long bookTickets(long eventId, Date eventDate, List<EventSchedule> eventSchedules) throws Exception;

}
