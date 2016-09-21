package middleware.aggregator;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.xml.xpath.XPathExpression;
import org.w3c.dom.Document;

import com.redstrawberry.RedStrawberryOrder;

public class OrderAggregator {
	
	private static final Logger logger = Logger.getLogger(OrderAggregator.class);
	
	private XPathExpression selectOrderNumberXpath;
	private XPathExpression selectItemQuantityXpath;
	private XPathExpression selectItemIdXpath;
	
	public OrderAggregator(XPathExpression selectOrderNumberXpath, XPathExpression selectItemQuantityXpath, XPathExpression selectItemIdXpath) {
		this.selectOrderNumberXpath = selectOrderNumberXpath;
		this.selectItemQuantityXpath = selectItemQuantityXpath;
		this.selectItemIdXpath = selectItemIdXpath;
	}
	
	public Message<List<RedStrawberryOrder>> aggregate(Collection<Message<String>> results) {		
		Date date = Calendar.getInstance().getTime();
		List<RedStrawberryOrder> orders = new ArrayList<>();
		for (Message<String> message : results) {
			try {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				documentBuilderFactory.setNamespaceAware(true);
				Document doc = documentBuilderFactory.newDocumentBuilder().parse(new ByteArrayInputStream(
						((String)message.getPayload()).getBytes()));
				
				String orderId = selectOrderNumberXpath.evaluateAsString(doc);
				String quantity = selectItemQuantityXpath.evaluateAsString(doc);
				String itemId = selectItemIdXpath.evaluateAsString(doc);
				
				RedStrawberryOrder order = new RedStrawberryOrder();
				order.setCantidad(Integer.parseInt(quantity));
				order.setIdProducto(Long.parseLong(itemId));
				order.setIdTransaccion(orderId + ":" + itemId);
				order.setFecha(date);
				
				orders.add(order);
			} catch (Exception e) {
				logger.error("error de formato agregando items", e);
			}
		}
		

		Message<List<RedStrawberryOrder>> newMessage = MessageBuilder.withPayload(orders).build();		

		return newMessage;
	}

}
