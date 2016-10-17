package middleware.ws;

import javax.jws.WebService;

import middleware.business.EventManager;

@WebService(endpointInterface = "middleware.ws.BookQueryService", name = "BookQueryService", targetNamespace = "http://ticketinco.com/")
public class BookQueryServiceImpl implements BookQueryService {
	
	private EventManager eventManager;

	@Override
	public BookQueryResponse queryBook(BookQueryRequest request) throws Exception {
		int bookState = eventManager.getBookState(request.getBookId());
		BookQueryResponse response = new BookQueryResponse();
		response.setBookState(bookState);
		return response;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

}
