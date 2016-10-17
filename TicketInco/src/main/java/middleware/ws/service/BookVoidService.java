package middleware.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import middleware.ws.BookVoidRequest;
import middleware.ws.BookVoidResponse;

@WebService(name = "BookVoidService", targetNamespace = "http://ticketinco.com/")
public interface BookVoidService {
	
	@WebMethod(action = "bookVoid", operationName = "bookVoid")
	public BookVoidResponse bookVoid(@WebParam(name = "request") BookVoidRequest bookVoidRequest) throws Exception;

}
