package middleware.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import middleware.ws.BookConfirmationRequest;


@WebService(name = "BookConfirmationService", targetNamespace = "http://ticketinco.com/")
public interface BookConfirmationService {
	
	@WebMethod(action = "confirmBook", operationName = "confirmBook")
	public void confirmBook(@WebParam(name = "request") BookConfirmationRequest bookConfirmationRequest) throws Exception;

}
