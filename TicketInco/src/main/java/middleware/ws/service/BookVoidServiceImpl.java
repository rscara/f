package middleware.ws.service;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

import middleware.business.EventManager;
import middleware.ws.BookVoidRequest;
import middleware.ws.BookVoidServiceResponse;

@WebService(endpointInterface = "middleware.ws.service.BookVoidService", name = "BookVoidService", targetNamespace = "http://ticketinco.com/")
public class BookVoidServiceImpl implements BookVoidService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookVoidServiceImpl.class);
	private XStream xstream = new XStream();

	private EventManager eventManager;

	@Override
	public BookVoidServiceResponse bookVoid(BookVoidRequest bookVoidRequest) throws Exception {
		logger.info("Request:");
		logger.info(xstream.toXML(bookVoidRequest));
		
		BookVoidServiceResponse response = new BookVoidServiceResponse();
		response.setVoidPaymentConfirmationId(eventManager.voidConfirmation(bookVoidRequest.getPaymentConfirmationId(), 
				bookVoidRequest.getPaymentMode()));
		
		logger.info("Response:");
		logger.info(xstream.toXML(response));
		
		return response;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

}
