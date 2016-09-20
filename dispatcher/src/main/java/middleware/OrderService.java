package middleware;

import java.util.Collection;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;


@RestController
@RequestMapping("/orderService")
public class OrderService {
	
	private MessageChannel inboxChannel;
	
	
	public OrderService(MessageChannel inboxChannel) {
		this.inboxChannel=inboxChannel;
	}
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public Collection<Object> getAll() {
//		MessageChannel messageChannel = (MessageChannel) applicationContext.getBean("inboxChannel");
		GenericMessage<Document> orderMessage;
		try {
			Message<String> message = MessageBuilder.withPayload(createXmlMessageFromResource("META-INF/middleware/order.xml")).build();
			inboxChannel.send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return null;
	}



private static String createXmlMessageFromResource(String path) throws Exception {
	Resource orderRes = new ClassPathResource(path);
	return IOUtils.toString(orderRes.getInputStream());
}

public void setInboxChannel(MessageChannel inboxChannel) {
	this.inboxChannel = inboxChannel;
}

	

}
