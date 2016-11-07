package middleware.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import middleware.ws.LogSystemResponse;

@WebService(name = "LogSystemService", targetNamespace = "http://ticketinco.com/")
public interface LogSystemService {
	
	@WebMethod(action = "logAll", operationName = "logAll")
	public LogSystemResponse logAll();

}
