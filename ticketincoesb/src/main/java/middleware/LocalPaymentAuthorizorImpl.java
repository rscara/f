package middleware;

import javax.jws.WebService;

@WebService(endpointInterface = "middleware.LocalPaymentAuthorizor", name = "LocalPaymentAuthorizor", targetNamespace = "http://ticketincoesb.com/")
public class LocalPaymentAuthorizorImpl implements LocalPaymentAuthorizor {

	@Override
	public LocalPaymentResponse authorizePayment(LocalPaymentRequest request) {
		LocalPaymentResponse response = new LocalPaymentResponse();
		response.setOk(true);
		
		return response;	
	}

	@Override
	public LocalVoidResponse voidPayment(LocalVoidRequest request) {
		LocalVoidResponse response = new LocalVoidResponse();
		response.setOk(true);
		
		return response;
	}

}
