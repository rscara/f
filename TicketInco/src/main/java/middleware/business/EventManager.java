package middleware.business;

import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;

import middleware.dal.EventBook;

public interface EventManager {
	
	public Event getScheduleForEvent(long eventId, Date eventDate);
	
	public long bookTickets(long eventId, Date eventDate, List<EventSchedule> eventSchedules) throws Exception;
	
	public int getBookState(long bookId) throws Exception;
	
	public List<EventBook> getBooksByBookId(long bookId);
	
	public List<DataHandler> confirmBooks(long bookId, long paymentModeId, String creditCardNumber, int creditCardCheckDigit, 
			Date creditCardExpiration) throws Exception;
	
	public void cleanBooks();
	
	public int voidConfirmation(long paymentConfirmationId, long paymentMode) throws Exception;
}
