package middleware;

import javax.jws.WebService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;

@WebService(endpointInterface = "middleware.PagosYaPaymentAuthorizor", name = "PagosYaPaymentAuthorizor", targetNamespace = "http://ticketincoesb.com/")
public class PagosYaPayentAuthorizorImpl implements PagosYaPaymentAuthorizor {

	private static final Logger logger = LogManager.getLogger(PagosYaPayentAuthorizorImpl.class);
	private XStream xstream = new XStream();
	
	@Override
	public PagosYaPaymentResponse authorizePayment(PagosYaPaymentRequest request) {
		logger.info("Request:" + xstream.toXML(request));

		PagosYaPaymentResponse response = new PagosYaPaymentResponse();
		response.setOk(true);
		response.setConfirmationNumber(1234);

		logger.info("Response:" + xstream.toXML(response));

		return response;
	}

	@Override
	public PagosYaVoidResponse voidPayment(PagosYaVoidRequest request) {
		logger.info("Request:" + xstream.toXML(request));

		PagosYaVoidResponse response = new PagosYaVoidResponse();
		response.setOk(true);
		response.setConfirmationNumber(9999);

		logger.info("Response:" + xstream.toXML(response));
		return response;
	}

}
