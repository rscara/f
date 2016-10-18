package middleware.ws.service;

import javax.jws.WebService;

import middleware.ws.BookVoidRequest;
import middleware.ws.BookVoidResponse;

@WebService(endpointInterface = "middleware.ws.service.BookVoidService", name = "BookVoidService", targetNamespace = "http://ticketinco.com/")
public class BookVoidServiceImpl implements BookVoidService {

	@Override
	public BookVoidResponse bookVoid(BookVoidRequest bookVoidRequest) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
