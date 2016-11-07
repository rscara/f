package middleware.ws.service;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

import middleware.business.EventManager;
import middleware.ws.TicketQueryRequest;
import middleware.ws.TicketQueryResponse;

@WebService(endpointInterface = "middleware.ws.service.TicketQueryService", name = "TicketQueryService", targetNamespace = "http://ticketinco.com/")
public class TicketQueryServiceImpl implements TicketQueryService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookQueryServiceImpl.class);

	private EventManager eventManager;
	
	private XStream xstream = new XStream();

	@Override
	public TicketQueryResponse queryTickets(TicketQueryRequest request) {
		logger.info("Request:");
		logger.info(xstream.toXML(request));
		
		TicketQueryResponse response = new TicketQueryResponse();
		response.setEvent(eventManager.getScheduleForEvent(request.getEventID(), request.getEventDate()));
		
		logger.info("Response:");
		logger.info(xstream.toXML(response));		
		return response;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

}
