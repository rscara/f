package mobilesys.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;

public class MobileSysConsumer {
	
	

	@Autowired
	@Qualifier("outputChannel")
	private SubscribableChannel outputChannel;
	
	public void subscribe() {
		
		outputChannel.subscribe(new MessageHandler() {
						@Override
			public void handleMessage(Message<?> message) throws MessagingException {
							System.out.println(message);
			}
			
		});
	}
    
    
    
}
