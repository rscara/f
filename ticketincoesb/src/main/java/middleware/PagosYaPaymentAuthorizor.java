package middleware;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "PagosYaPaymentAuthorizor", targetNamespace = "http://ticketincoesb.com/")
public interface PagosYaPaymentAuthorizor {
	
	@WebMethod(action = "authorizePayment", operationName = "authorizePayment")
	public PagosYaPaymentResponse authorizePayment(@WebParam(name = "request") PagosYaPaymentRequest request);
	
	@WebMethod(action = "voidPayment", operationName = "voidPayment")
	public PagosYaVoidResponse voidPayment(@WebParam(name = "request") PagosYaVoidRequest request);

}
