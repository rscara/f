package middleware.ws.service;

import java.util.List;

import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.xml.ws.soap.Addressing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

import middleware.business.EventManager;
import middleware.ws.BookConfirmationRequest;
import middleware.ws.BookConfirmationResponse;

@Addressing(required=true)
@WebService(endpointInterface = "middleware.ws.service.BookConfirmationService", name = "BookConfirmationService", 
	targetNamespace = "http://ticketinco.com/")
public class BookConfirmationServiceImpl implements BookConfirmationService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookConfirmationServiceImpl.class);
	
	private EventManager eventManager;
	
	private XStream xstream = new XStream();


	@Override
	public BookConfirmationResponse confirmBook(BookConfirmationRequest bookConfirmationRequest){
		
		logger.debug("Request:");
		logger.debug(xstream.toXML(bookConfirmationRequest));
		BookConfirmationResponse bookConfirmationResponse = new BookConfirmationResponse();
		bookConfirmationResponse.setBookId(bookConfirmationRequest.getBookId());
		
		List<DataHandler> images;
		try {
			images = eventManager.confirmBooks(bookConfirmationRequest.getBookId(), bookConfirmationRequest.getPaymentModeId(), 
					bookConfirmationRequest.getCreditCardNumber(), bookConfirmationRequest.getCreditCardCheckDigit(), 
					bookConfirmationRequest.getCreditCardExpiration());
			bookConfirmationResponse.setImages(images);
		} catch (Exception e) {
			logger.error("Error confirmando reserva", e);
			bookConfirmationResponse.setError(true);
			bookConfirmationResponse.setErrorDescription(e.getMessage());
		}

		logger.debug("Response:");
		logger.debug(xstream.toXML(bookConfirmationResponse));

		return bookConfirmationResponse;	
		
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

}
