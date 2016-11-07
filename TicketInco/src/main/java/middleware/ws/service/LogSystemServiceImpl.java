package middleware.ws.service;

import javax.jws.WebService;

import middleware.dal.EventTicketsDatasource;
import middleware.ws.LogSystemResponse;

@WebService(endpointInterface = "middleware.ws.service.LogSystemService", name = "LogSystemService", targetNamespace = "http://ticketinco.com/")
public class LogSystemServiceImpl implements LogSystemService {
	
	private EventTicketsDatasource eventTicketsDatasource;

	@Override
	public LogSystemResponse logAll() {
		LogSystemResponse response = new LogSystemResponse();
		response.setAllEventBooks(eventTicketsDatasource.getAllBooks());
		response.setTotalTickets(eventTicketsDatasource.getAllTickets());
		response.setAllEventConfirmations(eventTicketsDatasource.getAllConfirmations());
		response.setAllEventBookVoids(eventTicketsDatasource.getAllVoids());
		
		return response;
	}

	public void setEventTicketsDatasource(EventTicketsDatasource eventTicketsDatasource) {
		this.eventTicketsDatasource = eventTicketsDatasource;
	}

}
