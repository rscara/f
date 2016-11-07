package middleware;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "LocalPaymentAuthorizor", targetNamespace = "http://ticketincoesb.com/")
public interface LocalPaymentAuthorizor {
	
	@WebMethod(action = "authorizePayment", operationName = "authorizePayment")
	public LocalPaymentResponse authorizePayment(@WebParam(name = "request") LocalPaymentRequest request);
	
	@WebMethod(action = "voidPayment", operationName = "voidPayment")
	public LocalVoidResponse voidPayment(@WebParam(name = "request") LocalVoidRequest request);

}
