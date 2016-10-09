package middleware.ws;

import javax.jws.WebService;

import middleware.business.EventManager;

@WebService(endpointInterface = "middleware.ws.TicketQueryService", name = "TicketQueryService", targetNamespace = "http://ticketinco.com/")
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
