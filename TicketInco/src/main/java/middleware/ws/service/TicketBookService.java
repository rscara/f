package middleware.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import middleware.ws.TicketBookRequest;
import middleware.ws.TicketBookResponse;

@WebService(name = "TicketBookService", targetNamespace = "http://ticketinco.com/")
public interface TicketBookService {
	
	@WebMethod(action = "bookEvent", operationName = "bookEvent")
	public TicketBookResponse bookEvent(@WebParam(name = "request") TicketBookRequest ticketBookRequest);

}
