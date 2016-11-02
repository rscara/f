package middleware;

import javax.jws.WebService;

@WebService(endpointInterface = "middleware.PagosYaPaymentAuthorizor", name = "PagosYaPaymentAuthorizor", targetNamespace = "http://ticketincoesb.com/")
public class PagosYaPayentAuthorizorImpl implements PagosYaPaymentAuthorizor {

	@Override
	public PagosYaPaymentResponse authorizePayment(PagosYaPaymentRequest request) {
		PagosYaPaymentResponse response = new PagosYaPaymentResponse();
		response.setOk(true);
		response.setConfirmationNumber(1234);
		
		return response;
	}

	@Override
	public PagosYaVoidResponse voidPayment(PagosYaVoidRequest request) {
		PagosYaVoidResponse response = new PagosYaVoidResponse();
		response.setOk(true);
		response.setConfirmationNumber(9999);
			
		return response;
	}

}
