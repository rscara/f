package middleware;

import javax.jws.WebService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;

@WebService(endpointInterface = "middleware.LocalPaymentAuthorizor", name = "LocalPaymentAuthorizor", targetNamespace = "http://ticketincoesb.com/")
public class LocalPaymentAuthorizorImpl implements LocalPaymentAuthorizor {
	
	private static final Logger logger = LogManager.getLogger(LocalPaymentAuthorizorImpl.class);
	private XStream xstream = new XStream();

	@Override
	public LocalPaymentResponse authorizePayment(LocalPaymentRequest request) {
		logger.info("Request:" + xstream.toXML(request));
		
		LocalPaymentResponse response = new LocalPaymentResponse();
		response.setOk(true);
		
		logger.info("Response:" + xstream.toXML(response));

		return response;	
	}

	@Override
	public LocalVoidResponse voidPayment(LocalVoidRequest request) {
		logger.info("Request:" + xstream.toXML(request));

		LocalVoidResponse response = new LocalVoidResponse();
		response.setOk(true);
		
		logger.info("Response:" + xstream.toXML(response));
		return response;
	}

}
