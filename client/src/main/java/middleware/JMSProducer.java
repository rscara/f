package middleware;

import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.MessageProducer;
import com.sun.messaging.Queue;
import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.ConnectionConfiguration;

public class JMSProducer {

	public static void main(String[] args) {
		try {
			ConnectionFactory myConnFactory = new ConnectionFactory();
			myConnFactory.setProperty(ConnectionConfiguration.imqBrokerHostName, "vm://localhost");

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
