package middleware;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.Queue;

public class JMSProducer {

	public static void main(String[] args) {
		try {
			Properties jndiParameters = new Properties() ;
		    jndiParameters.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		    jndiParameters.put(Context.PROVIDER_URL, "tcp://localhost:61616");
		    Context context = new InitialContext(jndiParameters);
		    ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
		    Queue queue = (Queue) context.lookup("inboxQueue");

			
			ConnectionFactory myConnFactory = new ConnectionFactory();
			//myConnFactory.setProperty(com.sun.messaging.ConnectionConfiguration.imqConnectionType, "HTTP");			
			//myConnFactory.setProperty(ConnectionConfiguration.imqAddressList, new StringBuffer().append("mq://127.0.0.1:61616/").toString());

			Connection myConn = myConnFactory.createConnection();
			Session mySess = myConn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue myQueue = new com.sun.messaging.Queue("inboxQueue");
			MessageProducer myMsgProducer = mySess.createProducer(myQueue);
			
			while(true) {
				TextMessage myTextMsg = mySess.createTextMessage();
				myTextMsg.setText("Hello World");
				System.out.println("Sending Message: " + myTextMsg.getText());
				myMsgProducer.send(myTextMsg);				
			}

			//mySess.close();

		} catch (Exception jmse) {
			System.out.println("Exception occurred : " + jmse.toString());
			jmse.printStackTrace();
		}
	}
}
