package middleware;

import javax.jws.WebService;

@WebService(endpointInterface = "middleware.LocalPaymentAuthorizor", name = "LocalPaymentAuthorizor", targetNamespace = "http://ticketincoesb.com/")
public class LocalPaymentAuthorizorImpl implements LocalPaymentAuthorizor {

	@Override
	public LocalPaymentResponse authorizePayment(LocalPaymentRequest request) {
		LocalPaymentResponse response = new LocalPaymentResponse();
		response.setOk(true);
		response.setConfirmationNumber(1234);
		
		return response;	
	}

	@Override
	public void voidPayment(PagosYaVoidRequest request) {

	}

}
