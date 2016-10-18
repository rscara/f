import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ws.security.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

import middleware.ws.TicketQueryRequest;
import middleware.ws.TicketQueryResponse;
import middleware.ws.service.TicketQueryService;

public class ServiceClient {
	
	private static Logger logger = LogManager.getLogger(ServiceClient.class);
	
	public static void main(String[] args) throws Exception {
		
		
		URL wsdlURL = new URL("http://localhost:8080/services/TicketQueryService?wsdl");
		QName SERVICE_NAME = new QName("http://ticketinco.com/", "TicketQueryServiceImplService");
		Service service = Service.create(wsdlURL, SERVICE_NAME);
		TicketQueryService clientService = service.getPort(TicketQueryService.class);
		
		Client client = ClientProxy.getClient(clientService);
		Endpoint cxfEndpoint = client.getEndpoint();
//		Map<String,Object> inProps = new HashMap<String,Object>();
//		
//		inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
//		inProps.put(WSHandlerConstants.USER, "joe");
//		inProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
//		inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, 
//				ClientCallbackHandler.class.getName());
//		
//		 
//		WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
//		cxfEndpoint.getInInterceptors().add(wssIn);
		 
		Map<String,Object> outProps = new HashMap<String,Object>();
		outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		// Specify our username
		outProps.put(WSHandlerConstants.USER, "joe");
		// Password type : plain text
		outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
		// for hashed password use:
		//properties.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
		// Callback used to retrieve password for given user.
		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, 
				ClientCallbackHandler.class.getName());
		 
		WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
		cxfEndpoint.getOutInterceptors().add(wssOut);
		
		
		
		TicketQueryRequest request=new TicketQueryRequest();
		request.setEventDate(new Date());
		request.setEventID(8100);
//		TicketQueryResponse result = client.queryTickets(request);
		
		
		Object[] resp = client.invoke("queryTickets", request);
		TicketQueryResponse result =(TicketQueryResponse) resp[0];
		
	}

}
