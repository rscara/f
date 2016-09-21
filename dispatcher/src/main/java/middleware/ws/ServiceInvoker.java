package middleware.ws;

import java.util.List;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.CXFBusFactory;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transport.http.HTTPConduitConfigurer;
import org.apache.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.messaging.Message;

import com.redstrawberry.RedStrawberryOrder;

@ConfigurationProperties(prefix = "ws")
public class ServiceInvoker {

	private static final Logger logger = Logger.getLogger(ServiceInvoker.class);

	private String wsdlLocation;
	private String user;
	private String password;
	
	private Client client;
	
	public void init() {
		try {
			if (client==null){
				Bus bus = CXFBusFactory.getThreadDefaultBus();
				MyHTTPConduitConfigurer conf = new MyHTTPConduitConfigurer(user, password); 
				bus.setExtension(conf, HTTPConduitConfigurer.class); 
				
				JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance(bus);
				client = dcf.createClient(wsdlLocation);
				logger.info("WS client created");
			}
		} catch (Exception e) {
			logger.error("Error creando cliente de ws", e);
		}
	}

	public Message<List<RedStrawberryOrder>> invoke(Message<List<RedStrawberryOrder>> message){
		try {
			init();
			for (RedStrawberryOrder order : message.getPayload()) {
				client.invoke("order", order);				
			}
		} catch (Exception e) {
			logger.error("Error llamando al ws redstrawberry", e);
			return message;
		}
		return null;
	}

	public void setWsdlLocation(String wsdlLocation) {
		this.wsdlLocation = wsdlLocation;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	class MyHTTPConduitConfigurer implements HTTPConduitConfigurer {

		private final String username;
		private final String password;

		public MyHTTPConduitConfigurer(String username, String password) {
			this.username = username;
			this.password = password;
		}

		public void configure(String name, String address, HTTPConduit c) {
			AuthorizationPolicy authorizationPolicy = new AuthorizationPolicy();
			authorizationPolicy.setUserName(username);
			authorizationPolicy.setPassword(password);
			c.setAuthorization(authorizationPolicy);
		}
	}

}
