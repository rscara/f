package middleware;

import java.util.Collection;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
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
			orderMessage = createXmlMessageFromResource("META-INF/middleware/order.xml");
			inboxChannel.send(orderMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return null;
	}

private static GenericMessage<Document> createXmlMessageFromResource(String path) throws Exception {
	ClassPathResource orderRes = new ClassPathResource(path);

	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
	builderFactory.setNamespaceAware(true);
	DocumentBuilder builder = builderFactory.newDocumentBuilder();

	Document orderDoc = builder.parse(orderRes.getInputStream());
	return new GenericMessage<Document>(orderDoc);
}

public void setInboxChannel(MessageChannel inboxChannel) {
	this.inboxChannel = inboxChannel;
}
	

}
