package middleware.ws.service;

import javax.jws.WebService;
import javax.xml.ws.soap.Addressing;

import middleware.ws.BookConfirmationRequest;
import wsdlgenerated.PagosYaPaymentAuthorizor;
import wsdlgenerated.PagosYaPaymentRequest;
import wsdlgenerated.PagosYaPaymentResponse;

@Addressing
@WebService(endpointInterface = "middleware.ws.service.BookConfirmationService", name = "BookConfirmationService", targetNamespace = "http://ticketinco.com/")
public class BookConfirmationServiceImpl implements BookConfirmationService {
	
	private PagosYaPaymentAuthorizor pagosYaPaymentAuthorizor;

	@Override
	public String confirmBook(BookConfirmationRequest bookConfirmationRequest) throws Exception {
		
		PagosYaPaymentRequest request = new PagosYaPaymentRequest();
		request.setAmount(10);
		request.setCheckDigit(1234);
		request.setCreditCardNumber(1234567);
		request.setExpiration("12-06-2018 00:00");
		
		try {
			PagosYaPaymentResponse response = pagosYaPaymentAuthorizor.authorizePayment(request);
			return Long.toString(response.getConfirmationNumber());
		} catch (Exception e) {
			return "Error";
		}
		
	}

	public void setPagosYaPaymentAuthorizor(PagosYaPaymentAuthorizor pagosYaPaymentAuthorizor) {
		this.pagosYaPaymentAuthorizor = pagosYaPaymentAuthorizor;
	}

}
