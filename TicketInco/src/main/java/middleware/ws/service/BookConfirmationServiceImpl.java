package middleware.ws.service;

import java.text.SimpleDateFormat;

import javax.jws.WebService;
import javax.xml.ws.soap.Addressing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import middleware.ws.BookConfirmationRequest;
import wsdlgenerated.LocalPaymentAuthorizor;
import wsdlgenerated.LocalPaymentRequest;
import wsdlgenerated.LocalPaymentResponse;
import wsdlgenerated.PagosYaPaymentAuthorizor;
import wsdlgenerated.PagosYaPaymentRequest;
import wsdlgenerated.PagosYaPaymentResponse;

@Addressing
@WebService(endpointInterface = "middleware.ws.service.BookConfirmationService", name = "BookConfirmationService", targetNamespace = "http://ticketinco.com/")
public class BookConfirmationServiceImpl implements BookConfirmationService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookConfirmationServiceImpl.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	
	private PagosYaPaymentAuthorizor pagosYaPaymentAuthorizor;
	private LocalPaymentAuthorizor localPaymentAuthorizor;

	@Override
	public String confirmBook(BookConfirmationRequest bookConfirmationRequest) throws Exception {
		
		logger.debug("Request:" + bookConfirmationRequest);
		
		if (bookConfirmationRequest.getPaymentModeId()==1) {
			PagosYaPaymentRequest request = new PagosYaPaymentRequest();
			//ver monto!!
			request.setAmount(10);
			request.setCheckDigit(bookConfirmationRequest.getCreditCardCheckDigit());
			request.setCreditCardNumber(Long.parseLong(bookConfirmationRequest.getCreditCardNumber()));
			request.setExpiration(sdf.format(bookConfirmationRequest.getCreditCardExpiration()));
			
			try {
				logger.debug("Autorizando PagosYa:" + request);
				//ver errores!
				PagosYaPaymentResponse response = pagosYaPaymentAuthorizor.authorizePayment(request);
				logger.debug("Respuesta PagosYa:" + response);
				return Long.toString(response.getConfirmationNumber());
			} catch (Exception e) {
				throw new Exception("Error autorizando con PagosYa", e);
			}			
		}
		else if (bookConfirmationRequest.getPaymentModeId()==2) {
			LocalPaymentRequest request = new LocalPaymentRequest();
			//ver monto!!
			request.setAmount("10.0");
			request.setCheckDigit(Integer.toString(bookConfirmationRequest.getCreditCardCheckDigit()));
			request.setExpiration(sdf.format(bookConfirmationRequest.getCreditCardExpiration()));
			request.setCreditCardNumber(bookConfirmationRequest.getCreditCardNumber());
			
			try {
				logger.debug("Autorizando pago Local:" + request);
				//ver errores!
				LocalPaymentResponse response = localPaymentAuthorizor.authorizePayment(request);
				logger.debug("Respuesta pago Local:" + response);
				return Long.toString(response.getConfirmationNumber());
			} catch (Exception e) {
				throw new Exception("Error autorizando con pago Local", e);
			}
		}
		else
			throw new Exception("Error: Medio de pago no reconocido");
	}

	public void setPagosYaPaymentAuthorizor(PagosYaPaymentAuthorizor pagosYaPaymentAuthorizor) {
		this.pagosYaPaymentAuthorizor = pagosYaPaymentAuthorizor;
	}

	public void setLocalPaymentAuthorizor(LocalPaymentAuthorizor localPaymentAuthorizor) {
		this.localPaymentAuthorizor = localPaymentAuthorizor;
	}

}
