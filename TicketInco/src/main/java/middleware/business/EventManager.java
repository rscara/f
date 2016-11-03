package middleware.business;

import java.util.Date;
import java.util.List;

import middleware.dal.EventBook;

public interface EventManager {
	
	public Event getScheduleForEvent(long eventId, Date eventDate);
	
	public long bookTickets(long eventId, Date eventDate, List<EventSchedule> eventSchedules) throws Exception;
	
	public int getBookState(long bookId) throws Exception;
	
	public List<EventBook> getBooksByBookId(long bookId);
	
	public void confirmBooks(List<EventBook> eventBooks);
	
	public void cleanBooks();

}
