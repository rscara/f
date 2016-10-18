package middleware.ws.service;

import javax.jws.WebService;

import middleware.ws.TicketBookRequest;
import middleware.ws.TicketBookResponse;

@WebService(endpointInterface = "middleware.ws.service.TicketBookService", name = "TicketBookService", targetNamespace = "http://ticketinco.com/")
public class TicketBookServiceImpl implements TicketBookService {

	@Override
	public TicketBookResponse bookEvent(TicketBookRequest ticketBookRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
