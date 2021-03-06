package middleware.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

public class PagosYaAuthorizorImpl implements PagosYaAuthorizor {
	
	private static final Logger logger = LoggerFactory.getLogger(PagosYaAuthorizorImpl.class);
	
	private long confirmationId = 0;
	private int voidId = 0;
	
	private XStream xstream = new XStream();

	@Override
	public PagosYaAuthorizeResponse authorizePayment(PagosYaAuthorizeRequest request) {
		logger.info("Request:");
		logger.info(xstream.toXML(request));
		
		PagosYaAuthorizeResponse response = new PagosYaAuthorizeResponse();
		if (request.getCheckDigit()>5){
			response.setOk(false);
			response.setErrorDescription("No autorizado pago por PagosYa");
		}
		else {
			response.setErrorDescription("OK");
			response.setOk(true);
			response.setConfirmationNumber(confirmationId++);
		}
		
		logger.info("Response:");
		logger.info(xstream.toXML(response));
		return response;
	}

	@Override
	public PagosYaVoidResponse voidPayment(PagosYaVoidRequest request) {
		logger.info("Request:");
		logger.info(xstream.toXML(request));
		
		PagosYaVoidResponse response = new PagosYaVoidResponse();
		if (request.getConfirmationNumber()%2==0) {
			response.setOk(false);
			response.setErrorDescription("Error autorizando anulacion por PagosYa");
		}
		else {
			response.setOk(true);
			response.setErrorDescription("OK");
			response.setConfirmationNumber(voidId++);
		}
		
		logger.info("Response:");
		logger.info(xstream.toXML(response));
		return response;
	}

}
