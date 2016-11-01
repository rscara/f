package middleware.ws;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "BookConfirmationService", targetNamespace = "http://confirmationservice.com/")
public interface BookConfirmationService {
	
	@WebMethod(action = "confirmBook", operationName = "confirm")
	@Oneway
	public void confirmBook(@WebParam(name = "request") String request);

}
