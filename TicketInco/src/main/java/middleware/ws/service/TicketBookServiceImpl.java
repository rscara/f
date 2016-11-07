package middleware.ws.service;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

import middleware.business.EventManager;
import middleware.ws.TicketBookRequest;
import middleware.ws.TicketBookResponse;

@WebService(endpointInterface = "middleware.ws.service.TicketBookService", name = "TicketBookService", targetNamespace = "http://ticketinco.com/")
public class TicketBookServiceImpl implements TicketBookService {
    
	private static final Logger logger = LoggerFactory.getLogger(TicketBookServiceImpl.class);

    private EventManager eventManager;
    
	private XStream xstream = new XStream();

    @Override
    public TicketBookResponse bookEvent(TicketBookRequest request) throws Exception {
        logger.debug("Request:");
		logger.debug(xstream.toXML(request));

        long bookId = eventManager.bookTickets(request.getEventId(), request.getEventDate(), request.getEventSchedules());
        TicketBookResponse response = new TicketBookResponse();
        response.setBookId(bookId);
        
        logger.debug("Response:");
		logger.debug(xstream.toXML(response));

        return response;
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }
}
