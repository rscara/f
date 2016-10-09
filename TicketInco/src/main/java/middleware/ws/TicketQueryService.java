package middleware.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "TicketQueryService", targetNamespace = "http://ticketinco.com/")
public interface TicketQueryService {
		
	@WebMethod(action = "queryTickets", operationName = "queryTickets")
	public TicketQueryResponse queryTickets(@WebParam(name = "request") TicketQueryRequest request);
}
