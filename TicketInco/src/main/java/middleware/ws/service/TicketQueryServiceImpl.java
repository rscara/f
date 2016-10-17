package middleware.ws.service;

import javax.jws.WebService;

import middleware.business.EventManager;
import middleware.ws.TicketQueryRequest;
import middleware.ws.TicketQueryResponse;

@WebService(endpointInterface = "middleware.ws.service.TicketQueryService", name = "TicketQueryService", targetNamespace = "http://ticketinco.com/")
public class TicketQueryServiceImpl implements TicketQueryService {
	
	private EventManager eventManager;

	@Override
	public TicketQueryResponse queryTickets(TicketQueryRequest request) {
		TicketQueryResponse response = new TicketQueryResponse();
		response.setEvent(eventManager.getScheduleForEvent(request.getEventID()));
		return response;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

}
