package middleware.ws.service;

import javax.jws.WebService;

import middleware.business.EventManager;
import middleware.ws.TicketBookRequest;
import middleware.ws.TicketBookResponse;

@WebService(endpointInterface = "middleware.ws.service.TicketBookService", name = "TicketBookService", targetNamespace = "http://ticketinco.com/")
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
