package middleware.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "BookConfirmationService", targetNamespace = "http://ticketinco.com/")
public interface BookConfirmationService {
	
	@WebMethod(action = "confirmBookResponse", operationName = "confirmBookResponse")
	public void confirmBook(@WebParam(name = "return") String request) throws Exception;

}
