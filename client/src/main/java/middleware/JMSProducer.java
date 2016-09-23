package middleware;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public final class JMSProducer {

  public static void main(final String[] args) throws Exception {
    final ConnectionFactory connFactory = new ActiveMQConnectionFactory();

    final Connection conn = connFactory.createConnection();

    final Session sess = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

    final Destination dest = sess.createQueue("queue.inbox");

    final MessageProducer prod = sess.createProducer(dest);
    int ordersQuantity=1000;
    for (int j = 0; j < ordersQuantity; j++) {
		List<OrderItem> items = new ArrayList<OrderItem>();
	    int itemQuantity = new Random().nextInt()%10;
	    BigDecimal total=BigDecimal.ZERO;
	    for (int i = 0; i < itemQuantity; i++) {
	    	OrderItem item = new OrderItem();
	    	item.setAmount(BigDecimal.valueOf(new Random().nextInt()%1500));
	    	item.setSku(i+"sku");
	    	item.setType(((i%3)+1)+"");
	    	item.setQuantity(BigDecimal.valueOf(i));
	    	item.setNumber(i+"");
	    	total=item.getQuantity().multiply(item.getAmount()).add(total);
	    	items.add(item);
		}
	    
	    Message msg = sess.createTextMessage(buildOrder(j+"", total+"", items));
	    prod.send(msg);
    }

    conn.close();
  }
  
  
  private static String buildOrder(String orderNumber,String total, List<OrderItem> items){
	  String order= "<order>"+
		"<head>"+
			"<number>"+orderNumber+"</number>"+
			"<date>23/05/2016 23:05:00</date>"+
			"<client-id>123456</client-id>"+
			"<payment-mode>E</payment-mode>"+
		"</head>"+
		"<detail>"+
			"<currency>858</currency>"+
			"<amount>"+total+"</amount>"+
			"<payments>1</payments>"+
		"</detail>";
		for (OrderItem orderItem : items) {
			order+="<item>"+
				"<number>"+orderItem.getNumber()+"</number>"+
				"<category>"+orderItem.getType()+"</category>"+
				"<sku>"+orderItem.getSku()+"</sku>"+
				"<description>"+orderItem.getDescription()+"</description>"+
				"<quantity>"+orderItem.getQuantity()+"</quantity>"+
				"<price>"+orderItem.getAmount()+"</price>"+
			"</item>";
		}
	 	order+="</order>";
	 	return order;
  }
  

}