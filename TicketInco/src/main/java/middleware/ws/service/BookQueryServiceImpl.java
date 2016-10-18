package middleware.ws.service;

import javax.jws.WebService;

import middleware.ws.BookQueryRequest;
import middleware.ws.BookQueryResponse;

@WebService(endpointInterface = "middleware.ws.service.BookQueryService", name = "BookQueryService", targetNamespace = "http://ticketinco.com/")
public class BookQueryServiceImpl implements BookQueryService {
    
    private static final Logger logger = Logger.getLogger(BookQueryServiceImpl.class);
    private EventManager eventManager;

    @Override
    public BookQueryResponse queryBook(BookQueryRequest request) throws Exception {
        logger.debug("Request:" + request.toString());
        int bookState = eventManager.getBookState(request.getBookId());
        BookQueryResponse response = new BookQueryResponse();
        response.setBookState(bookState);
        logger.debug("Response:" + response.toString());
        return response;
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }
