package middleware.dal;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import middleware.business.EventAvailability;
import middleware.business.EventSchedule;

public class MemoryEventTicketDatasource implements EventTicketsDatasource {
	
	private List<EventTicket> eventTickets;
	private List<EventBook> eventBooks;
	private List<EventConfirmation> confirmations;
	private List<Book> books;
	private List<EventBookVoid> voids;
	
	private long localPaymentAuthorizationSequence;
	
	public MemoryEventTicketDatasource() {
		eventTickets = new ArrayList<EventTicket>();
		eventBooks = new ArrayList<EventBook>();
		confirmations = new ArrayList<EventConfirmation>();
		books = new ArrayList<Book>();
		voids = new ArrayList<EventBookVoid>();
		localPaymentAuthorizationSequence = 0;
	}

	@Override
	public List<EventTicket> getTicketsForEvent(long eventId, Date eventDate) {
		List<EventTicket> ticketsForEvent = new ArrayList<EventTicket>();
		for (EventTicket eventTicket : eventTickets) {
			if (eventTicket.getEventId()==eventId && eventTicket.getEventDate().equals(eventDate))
				ticketsForEvent.add(eventTicket);
		}
		return ticketsForEvent;
	}

	@Override
	public EventTicket getTicketForEventHourAndSector(long eventId, Date eventDate, Date eventHour, String sector) {
		for (EventTicket eventTicket : eventTickets) {
			if (eventTicket.getEventId()==eventId && eventTicket.getEventDate().equals(eventDate) && 
					eventTicket.getHour().equals(eventHour) && eventTicket.getSector().equals(sector))
				return eventTicket;
		}
		return null;
	}
	
	@Override
	public List<EventBook> getBooksForEventHourAndSector(long eventId, Date eventDate, Date eventHour, String sector) {
		List<EventBook> booksForEvent = new ArrayList<EventBook>();
		for (EventBook eventBook : eventBooks) {
			if (eventBook.getEventId()==eventId && eventBook.getEventDate().equals(eventDate) && 
					eventBook.getHour().equals(eventHour) && eventBook.getSector().equals(sector))
				booksForEvent.add(eventBook);
		}
		return booksForEvent;
	}

	@Override
	public List<EventConfirmation> getConfirmationsForEventHourAndSector(long eventId, Date eventDate, Date eventHour, String sector) {
		List<EventConfirmation> confirmationsForEvent = new ArrayList<EventConfirmation>();
		for (EventConfirmation eventConfirmation : confirmations) {
			if (eventConfirmation.getEventId()==eventId && eventConfirmation.getEventDate().equals(eventDate) && 
					eventConfirmation.getHour().equals(eventHour) && eventConfirmation.getSector().equals(sector))
				confirmationsForEvent.add(eventConfirmation);
		}
		return confirmationsForEvent;
	}
	
	@Override
	public List<EventBookVoid> getVoidsForEventHourAndSector(long eventId, Date eventDate, Date eventHour,
			String sector) {
		List<EventBookVoid> voidsForEvent = new ArrayList<EventBookVoid>();
		for (EventBookVoid eventBookVoid : voids) {
			if (eventBookVoid.getEventId()==eventId && eventBookVoid.getEventDate().equals(eventDate) && 
					eventBookVoid.getHour().equals(eventHour) && eventBookVoid.getSector().equals(sector))
				voidsForEvent.add(eventBookVoid);
		}
		return voidsForEvent;
	}

	@Override
	public long addBooks(long eventId, Date eventDate, List<EventSchedule> schedules) {
		long bookId = books.size() + 1;
		List<EventBook> newBooks = new ArrayList<EventBook>();
		for (EventSchedule schedule : schedules) {
			for (EventAvailability availability : schedule.getAvailabilities()) {
				newBooks.add(new EventBook(eventId, eventDate, schedule.getHour(), availability.getSector(), 
						availability.getQuantity(), availability.getPrice(), bookId));
			}
		}
		eventBooks.addAll(newBooks);
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
		for (EventBook eventBook : eventBooks) {
			for (Book book : books) {
				if (book.getId()==eventBook.getBookId()) {
					 book.setState(0);
				}
			}
			
		}
		eventBooks = new ArrayList<EventBook>();
	}

	@Override
	public List<EventTicket> getAllTickets() {
		return eventTickets;
	}

	@Override
	public List<EventBook> getAllBooks() {
		return eventBooks;
	}

	@Override
	public List<EventConfirmation> getAllConfirmations() {
		return confirmations;
	}

	@Override
	public List<EventBook> getEventBooksByBookId(long bookId) {
		List<EventBook> eventBooksForId = new ArrayList<EventBook>();
		for (EventBook eventBook : eventBooks) {
			if (eventBook.getBookId()==bookId)
				eventBooksForId.add(eventBook);
		}
		return eventBooksForId;
	}

	@Override
	public void addConfirmation(long eventId, Date eventDate, Date eventHour, String sector, int quantity, long bookId, double price, 
			long confirmationId, long paymentMode) {
		EventConfirmation confirmation = new EventConfirmation(eventId, eventDate, eventHour, sector, quantity, bookId, price, 
				confirmationId, paymentMode);
		
		confirmations.add(confirmation);
	}

	@Override
	public void changeBookState(long bookId, int state){
		for (Book book : books) {
			if (book.getId()==bookId) {
				book.setState(state);
				return;
			}
		}
	}

	@Override
	public void removeEventBooks(long bookId) {
		for (Iterator<EventBook> eventBookIterator = eventBooks.iterator(); eventBookIterator.hasNext();) {
			EventBook eventBook = (EventBook) eventBookIterator.next();
			if (eventBook.getBookId()==bookId)
				eventBookIterator.remove();
		}
	}

	@Override
	public List<EventBookVoid> getAllVoids() {
		return voids;
	}

	@Override
	public List<EventConfirmation> getConfirmationsByPayment(long paymentConfirmationId, long paymentModeId) {
		List<EventConfirmation> confirmationsForPayment = new ArrayList<EventConfirmation>();
		for (EventConfirmation confirmation : confirmations) {
			if (confirmation.getPaymentConfirmationId()==paymentConfirmationId && confirmation.getPaymentMode()==paymentModeId)
				confirmationsForPayment.add(confirmation);
		}
		return confirmationsForPayment;
	}
	
	public List<EventBookVoid> getVoidByPayment(long paymentConfirmationId, long paymentModeId) {
		List<EventBookVoid> voidsForPayment = new ArrayList<EventBookVoid>();
		for (EventBookVoid eventBookVoid : voids) {
			if (eventBookVoid.getPaymentConfirmationId()==paymentConfirmationId && eventBookVoid.getPaymentMode()==paymentModeId)
				voidsForPayment.add(eventBookVoid);
		}
		return voidsForPayment;
	}

	@Override
	public void addBookVoid(long eventId, Date eventDate, Date eventHour, String sector, int quantity, double price, long bookId, 
			int voidConfirmationId, long paymentMode, long paymentConfirmationId) {
		
		EventBookVoid eventBookVoid = new EventBookVoid(eventId, eventDate, eventHour, sector, quantity, price, bookId, 
				voidConfirmationId, paymentMode, paymentConfirmationId);
		voids.add(eventBookVoid);
	}
	
	public long getLocalAuthorizationSequence() {
		return localPaymentAuthorizationSequence++;
	}

	public void setEventTickets(List<EventTicket> eventTickets) {
		this.eventTickets = eventTickets;
	}
}
