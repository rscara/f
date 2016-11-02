package middleware;

import javax.jws.WebService;

@WebService(endpointInterface = "middleware.LocalPaymentAuthorizor", name = "LocalPaymentAuthorizor", targetNamespace = "http://ticketincoesb.com/")
public class LocalPaymentAuthorizorImpl implements LocalPaymentAuthorizor {

	@Override
	public LocalPaymentResponse authorizePayment(LocalPaymentRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void voidPayment(PagosYaVoidRequest request) {

	}

}
