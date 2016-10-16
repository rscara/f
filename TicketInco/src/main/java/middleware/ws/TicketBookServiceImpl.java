package middleware.ws;

import middleware.business.EventManager;

public class TicketBookServiceImpl implements TicketBookService {
	
	private EventManager eventManager;

	@Override
	public TicketBookResponse bookEvent(TicketBookRequest request) throws Exception {
		long bookId = eventManager.bookTickets(request.getEventId(), request.getEventDate(), request.getEventSchedules());
		TicketBookResponse response = new TicketBookResponse();
		response.setBookId(bookId);
		return response;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

}
