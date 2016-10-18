package middleware.ws.service;

import javax.jws.WebService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import middleware.business.EventManager;
import middleware.ws.TicketBookRequest;
import middleware.ws.TicketBookResponse;

@WebService(endpointInterface = "middleware.ws.service.TicketBookService", name = "TicketBookService", targetNamespace = "http://ticketinco.com/")
public class TicketBookServiceImpl implements TicketBookService {
    
    private static final Logger logger = LogManager.getLogger(TicketBookServiceImpl.class);
    private EventManager eventManager;

    @Override
    public TicketBookResponse bookEvent(TicketBookRequest request) throws Exception {
        logger.debug("Request:" + request.toString());
        long bookId = eventManager.bookTickets(request.getEventId(), request.getEventDate(), request.getEventSchedules());
        TicketBookResponse response = new TicketBookResponse();
        response.setBookId(bookId);
        logger.debug("Response:" + response.toString());
        return response;
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }
}

