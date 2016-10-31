import java.net.MalformedURLException;
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
import org.apache.ws.security.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import middleware.ws.BookConfirmationRequest;
import middleware.ws.TicketQueryRequest;
import middleware.ws.TicketQueryResponse;
import middleware.ws.service.BookConfirmationService;
import middleware.ws.service.TicketQueryService;

public class ServiceClient {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceClient.class);
	
	public static void main(String[] args) throws Exception {
		
		confirmation();
		
		//query();
		
	}

	private static void confirmation() throws Exception {

		
        URL wsdlURL = new URL("http://localhost:8080/services/BookConfirmationService?wsdl");
		QName SERVICE_NAME = new QName("http://ticketinco.com/", "BookConfirmationServiceImplService");
		Service service = Service.create(wsdlURL, SERVICE_NAME);
		BookConfirmationService clientService = service.getPort(BookConfirmationService.class);
		
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
		outProps.put(WSHandlerConstants.ACTION,WSHandlerConstants.ENCRYPT);
		// Specify our username
		outProps.put(WSHandlerConstants.USER, "myservicekey");
		// Password type : plain text
		outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
		// for hashed password use:
		//properties.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
		// Callback used to retrieve password for given user.
		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, 
				ClientCallbackHandler.class.getName());
		outProps.put(WSHandlerConstants.SIG_PROP_FILE, "config/application.properties");
		
		WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
		cxfEndpoint.getOutInterceptors().add(wssOut);
		
		
		
		BookConfirmationRequest request=new BookConfirmationRequest();
//		TicketQueryResponse result = client.queryTickets(request);
		
		Object[] resp = client.invoke("confirmBook", request);
		
	}

	private static void query() throws Exception {

		
		URL wsdlURL = new URL("file:///desarrollo/facultad/f/TicketInco/TicketQueryService.wsdl");
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
		outProps.put(WSHandlerConstants.ACTION,WSHandlerConstants.SIGNATURE);
		// Specify our username
		outProps.put(WSHandlerConstants.USER, "myservicekey");
		// Password type : plain text
		outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
		// for hashed password use:
		//properties.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
		// Callback used to retrieve password for given user.
		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, 
				ClientCallbackHandler.class.getName());
		outProps.put(WSHandlerConstants.SIG_PROP_FILE, "config/application.properties");
		
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
