package middleware.dal;

import java.util.Date;
import java.util.List;

import middleware.business.EventSchedule;

public interface EventTicketsDatasource {
	
	public List<EventTicket> getTicketsForEvent(long eventId, Date eventDate);
	
	public EventTicket getTicketForEventHourAndSector(long eventId, Date eventDate, Date eventHour, String sector);
		
	public List<EventBook> getBooksForEventHourAndSector(long eventId, Date eventDate, Date eventHour, String sector);
	
	public List<EventConfirmation> getConfirmationsForEventHourAndSector(long eventId, Date eventDate, Date eventHour, String sector);
	
	public long addBooks(long eventId, Date eventDate, List<EventSchedule> schedules);
	
	public Book getBookById(long bookId);
	
	public List<EventTicket> getAllTickets();
	
	public List<EventBook> getAllBooks();
	
	public List<EventConfirmation> getAllConfirmations();
	
	public void removeAllBooks();
	
	public List<EventBook> getEventBooksByBookId(long bookId);
	
	public void addConfirmation(long eventId, Date eventDate, Date eventHour, String sector, int quantity, long bookId, double price);
	
	public void changeBookState(long bookId, int state);
	
	public void removeEventBooks(long bookId);
	
}
