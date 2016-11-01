package middleware.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.soap.Addressing;

import middleware.ws.BookConfirmationRequest;

@Addressing
@WebService(name = "BookConfirmationService", targetNamespace = "http://ticketinco.com/")
public interface BookConfirmationService {
	
	@WebMethod(action = "confirmBook", operationName = "confirmBook")
	public String confirmBook(@WebParam(name = "request") BookConfirmationRequest bookConfirmationRequest) throws Exception;

}
