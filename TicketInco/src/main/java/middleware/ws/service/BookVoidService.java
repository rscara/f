package middleware.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import middleware.ws.BookVoidRequest;
import middleware.ws.BookVoidServiceResponse;

@WebService(name = "BookVoidService", targetNamespace = "http://ticketinco.com/")
public interface BookVoidService {
	
	@WebMethod(action = "bookVoid", operationName = "bookVoid")
	public BookVoidServiceResponse bookVoid(@WebParam(name = "request") BookVoidRequest bookVoidRequest) throws Exception;

}
