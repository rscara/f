package middleware.ws;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebService(endpointInterface = "middleware.ws.BookConfirmationService", name = "BookConfirmationService", targetNamespace = "http://confirmationservice.com/")
public class BookConfirmationServiceImpl implements BookConfirmationService {

	private static final Logger logger = LoggerFactory.getLogger(BookConfirmationServiceImpl.class);

	@Override
	public void confirm(String request) {
		logger.debug("Llego request:" + request);
	}

}
