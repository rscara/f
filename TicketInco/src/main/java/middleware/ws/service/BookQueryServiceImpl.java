package middleware.ws.service;

import javax.jws.WebService;

import middleware.ws.BookQueryRequest;
import middleware.ws.BookQueryResponse;

@WebService(endpointInterface = "middleware.ws.service.BookQueryService", name = "BookQueryService", targetNamespace = "http://ticketinco.com/")
public class BookQueryServiceImpl implements BookQueryService {

	@Override
	public BookQueryResponse  queryBook(BookQueryRequest bookQueryRequest) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
