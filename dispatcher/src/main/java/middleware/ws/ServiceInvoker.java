package middleware.ws;

import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.CXFBusFactory;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transport.http.HTTPConduitConfigurer;
import org.apache.log4j.Logger;
import org.apache.tomcat.websocket.WsWebSocketContainer;
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
//				client.getUserProperties().put(WsWebSocketContainer.SSL_CONTEXT_PROPERTY, sslContext);
				
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
			
			X509ExtendedTrustManager mgr=new X509ExtendedTrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				
				@Override
				public void checkServerTrusted(
						X509Certificate[] paramArrayOfX509Certificate, String paramString)
						throws CertificateException {
				}
				
				@Override
				public void checkClientTrusted(
						X509Certificate[] paramArrayOfX509Certificate, String paramString)
						throws CertificateException {
				}
				
				@Override
				public void checkServerTrusted(
						X509Certificate[] paramArrayOfX509Certificate, String paramString,
						SSLEngine paramSSLEngine) throws CertificateException {
				}
				
				@Override
				public void checkServerTrusted(
						X509Certificate[] paramArrayOfX509Certificate, String paramString,
						Socket paramSocket) throws CertificateException {
				}
				
				@Override
				public void checkClientTrusted(
						X509Certificate[] paramArrayOfX509Certificate, String paramString,
						SSLEngine paramSSLEngine) throws CertificateException {
				}
				
				@Override
				public void checkClientTrusted(
						X509Certificate[] paramArrayOfX509Certificate, String paramString,
						Socket paramSocket) throws CertificateException {
				}
			};
			TrustManager[] trustManagers=new TrustManager[] { mgr};
		    SSLContext sslContext;
			try {
				sslContext = SSLContext.getInstance("TLS");
				sslContext.init(null/*keymanagers*/, trustManagers, new SecureRandom());
				SSLContext.setDefault(sslContext);
			} catch (Exception e) {
				e.printStackTrace();
			}
		  TLSClientParameters tcp = new TLSClientParameters();
		  tcp.setTrustManagers(trustManagers);
		  c.setTlsClientParameters( tcp );

		}
	}

}
