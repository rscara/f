package middleware.ws.service;

import javax.jws.WebService;

import middleware.ws.BookConfirmationRequest;

@WebService(endpointInterface = "middleware.ws.service.BookConfirmationService", name = "BookConfirmationService", targetNamespace = "http://ticketinco.com/")
public class BookConfirmationServiceImpl implements BookConfirmationService {

	@Override
	public String confirmBook(BookConfirmationRequest bookConfirmationRequest) throws Exception {
		return "la respuesta";
	}

}
