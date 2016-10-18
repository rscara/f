package middleware.ws.service;

import javax.jws.WebService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import middleware.business.EventManager;
import middleware.ws.TicketQueryRequest;
import middleware.ws.TicketQueryResponse;

@WebService(endpointInterface = "middleware.ws.service.TicketQueryService", name = "TicketQueryService", targetNamespace = "http://ticketinco.com/")
public class TicketQueryServiceImpl implements TicketQueryService {
	
	private static final Logger logger = LogManager.getLogger(TicketQueryServiceImpl.class);
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
