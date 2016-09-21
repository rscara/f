package service;

import javax.annotation.security.RolesAllowed;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import middleware.ws.RedStrawberryOrder;

@WebService(name = "ItemsListService", targetNamespace = "http://redstrawberry.com/")
public interface ItemsListService {
	
	@WebMethod(action = "order", operationName = "order")
	@RolesAllowed("role")
	@Oneway
	public void order(@WebParam(name = "orders", targetNamespace = "http://redstrawberry.com/") RedStrawberryOrder order);

}
