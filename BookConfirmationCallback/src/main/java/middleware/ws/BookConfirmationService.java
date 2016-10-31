package middleware.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "BookConfirmationService", targetNamespace = "http://confirmationservice.com/")
public interface BookConfirmationService {
	
	@WebMethod(action = "confirm", operationName = "confirm")
	public void confirm(@WebParam(name = "request") String request);

}
