package middleware.ws;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import middleware.business.EventManager;

@WebService(endpointInterface = "middleware.ws.TicketQueryService", name = "TicketQueryService", targetNamespace = "http://ticketinco.com/")
public class TicketQueryServiceImpl implements TicketQueryService {
	
	private static final Logger logger = Logger.getLogger(TicketQueryServiceImpl.class);
	private EventManager eventManager;

	@Override
	public TicketQueryResponse queryTickets(TicketQueryRequest request) {
		logger.debug("Request:" + request.toString());
		TicketQueryResponse response = new TicketQueryResponse();
		response.setEvent(eventManager.getScheduleForEvent(request.getEventID(), request.getEventDate()));
		logger.debug("Response:" + response.toString());
		return response;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

}
