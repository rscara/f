package middleware.ws;

import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import middleware.DateAdapter;
import middleware.business.EventManager;

@WebService(endpointInterface = "middleware.ws.TicketQueryService", name = "TicketQueryService", targetNamespace = "http://ticketinco.com/")
public class TicketQueryServiceImpl implements TicketQueryService {
	
	private EventManager eventManager;

	@Override
	public TicketQueryResponse queryTickets(TicketQueryRequest request) {
		TicketQueryResponse response = new TicketQueryResponse();
		response.setEvent(eventManager.getScheduleForEvent(request.getEventID(), request.getEventDate()));
		return response;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

}
