package middleware.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "TicketBookService", targetNamespace = "http://ticketinco.com/")
public interface TicketBookService {

	@WebMethod(action = "bookEvent", operationName = "bookEvent")
	public TicketBookResponse bookEvent(@WebParam(name = "request") TicketBookRequest request) throws Exception;

}
