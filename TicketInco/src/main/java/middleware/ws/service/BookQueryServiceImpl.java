package middleware.ws.service;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

import middleware.business.EventManager;
import middleware.ws.BookQueryRequest;
import middleware.ws.BookQueryResponse;

@WebService(endpointInterface = "middleware.ws.service.BookQueryService", name = "BookQueryService", targetNamespace = "http://ticketinco.com/")
public class BookQueryServiceImpl implements BookQueryService {
        
	private static final Logger logger = LoggerFactory.getLogger(BookQueryServiceImpl.class);

    private EventManager eventManager;
    
	private XStream xstream = new XStream();

    @Override
    public BookQueryResponse queryBook(BookQueryRequest request) throws Exception {
        logger.debug("Request:");
		logger.debug(xstream.toXML(request));

        int bookState = eventManager.getBookState(request.getBookId());
        BookQueryResponse response = new BookQueryResponse();
        response.setBookState(bookState);
        
        logger.debug("Response:");
		logger.debug(xstream.toXML(response));

        return response;
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }
}
