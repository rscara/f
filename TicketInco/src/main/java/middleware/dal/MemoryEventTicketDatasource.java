package middleware.dal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import middleware.business.EventAvailability;
import middleware.business.EventSchedule;

public class MemoryEventTicketDatasource implements EventTicketsDatasource {
	
	private Map<Event, List<EventTicket>> ticketsByEvent;
	private Map<Event, List<EventBook>> booksByEvent;
	private Map<Event, List<EventConfirmation>> confirmationsByEvent;
	private List<Book> books;
	
	public MemoryEventTicketDatasource() {
		ticketsByEvent = new HashMap<Event, List<EventTicket>>();
		booksByEvent = new HashMap<Event, List<EventBook>>();
		confirmationsByEvent = new HashMap<Event, List<EventConfirmation>>();
		books = new ArrayList<Book>();
	}

	@Override
	public List<EventTicket> getTicketsForEvent(long eventId, Date eventDate) {
		List<EventTicket> ticketsForEvent = new ArrayList<EventTicket>();
		List<EventTicket> tickets = ticketsByEvent.get(new Event(eventId, eventDate));
		if (tickets!=null) {
			ticketsForEvent.addAll(tickets);
		}
		return ticketsForEvent;
	}

	@Override
	public EventTicket getTicketForEventHourAndSector(long eventId, Date eventDate, Date eventHour, String sector) {
		List<EventTicket> tickets = ticketsByEvent.get(new Event(eventId, eventDate));
		if (tickets!=null) {
			for (EventTicket eventTicket : tickets) {
				if (eventTicket.getHour().equals(eventHour) && eventTicket.getSector().equals(sector))
					return eventTicket;
			}
		}
		return null;
	}
	
	@Override
	public List<EventBook> getBooksForEventHourAndSector(long eventId, Date eventDate, Date eventHour, String sector) {
		List<EventBook> booksForEvent = new ArrayList<EventBook>();
		List<EventBook> books = booksByEvent.get(new Event(eventId, eventDate));
		if (books!=null) {
			for (EventBook eventBook : books) {
				if (eventBook.getHour().equals(eventHour) && eventBook.getSector().equals(sector))
					booksForEvent.add(eventBook);
			}
		}
		return booksForEvent;
	}

	@Override
	public List<EventConfirmation> getConfirmationsForEventHourAndSector(long eventId, Date eventDate, Date eventHour, String sector) {
		List<EventConfirmation> confirmationsForEvent = new ArrayList<EventConfirmation>();
		List<EventConfirmation> confirmations = confirmationsByEvent.get(new Event(eventId, eventDate));
		if (confirmations!=null) {
			for (EventConfirmation eventConfirmation : confirmations) {
				if (eventConfirmation.getHour().equals(eventHour) && eventConfirmation.getSector().equals(sector))
					confirmationsForEvent.add(eventConfirmation);
			}
		}
		return confirmationsForEvent;
	}


	@Override
	public long addBooks(long eventId, Date eventDate, List<EventSchedule> schedules) {
		long bookId = books.size() + 1;
		List<EventBook> eventBooks = new ArrayList<EventBook>();
		for (EventSchedule schedule : schedules) {
			for (EventAvailability availability : schedule.getAvailabilities()) {
				eventBooks.add(new EventBook(eventId, eventDate, schedule.getHour(), availability.getSector(), availability.getQuantity(), bookId));
			}
		}
		
		if (!booksByEvent.containsKey(new Event(eventId, eventDate)))
			booksByEvent.put(new Event(eventId, eventDate), new ArrayList<EventBook>());
		
		booksByEvent.get(new Event(eventId, eventDate)).addAll(eventBooks);
		books.add(new Book(bookId, 1, eventId, eventDate));
		
		return bookId;		
	}
	
	@Override
	public Book getBookById(long bookId) {
		for (Book book : books) {
			if (book.getId()==bookId)
				return book;
		}
		return null;
	}
	
	@Override
	public void removeAllBooks() {
		for (List<EventBook> eventsBooks : booksByEvent.values()) {
			for (EventBook eventBook : eventsBooks) {
				for (Book book : books) {
					if (book.getId()==eventBook.getBookId()) {
						 book.setState(0);
					}
				}
				
			}
		}
		new HashMap<Event, List<EventBook>>();
	}
	
	public void setTicketsByEvent(Map<Event, List<EventTicket>> ticketsByEvent) {
		this.ticketsByEvent = ticketsByEvent;
	}

	public void setBooksByEvent(Map<Event, List<EventBook>> booksByEvent) {
		this.booksByEvent = booksByEvent;
	}

	@Override
	public List<EventTicket> getAllTickets() {
		List<EventTicket> allTickets = new ArrayList<EventTicket>();
		for (List<EventTicket> eventTicketList : ticketsByEvent.values()) {
			allTickets.addAll(eventTicketList);
		}
		return allTickets;
	}

	@Override
	public List<EventBook> getAllBooks() {
		List<EventBook> allBooks = new ArrayList<EventBook>();
		for (List<EventBook> eventBookList : booksByEvent.values()) {
			allBooks.addAll(eventBookList);
		}
		return allBooks;
	}

	@Override
	public List<EventConfirmation> getAllConfirmations() {
		List<EventConfirmation> allConfirmations = new ArrayList<EventConfirmation>();
		for (List<EventConfirmation> eventConfirmationList : confirmationsByEvent.values()) {
			allConfirmations.addAll(eventConfirmationList);
		}
		return allConfirmations;
	}




}
