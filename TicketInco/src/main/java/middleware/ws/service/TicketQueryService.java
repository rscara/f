package middleware.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import middleware.ws.TicketQueryRequest;
import middleware.ws.TicketQueryResponse;

@WebService(name = "TicketQueryService", targetNamespace = "http://ticketinco.com/")
public interface TicketQueryService {
	
	@WebMethod(action = "queryTickets", operationName = "queryTickets")
	public TicketQueryResponse queryTickets(@WebParam(name = "request") TicketQueryRequest request);
}
