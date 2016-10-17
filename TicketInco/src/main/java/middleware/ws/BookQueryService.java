package middleware.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "BookQueryService", targetNamespace = "http://ticketinco.com/")
public interface BookQueryService {
	
	@WebMethod(action = "queryBook", operationName = "queryBook")
	public BookQueryResponse queryBook(@WebParam(name = "request") BookQueryRequest request) throws Exception;

}
