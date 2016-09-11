package middleware;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

public class OutboxDispatcher {

private static Logger logger = Logger.getLogger(OutboxDispatcher.class);
	
	public void dispatch(Document orderItem){
		logger.info("Warehouse dispatching orderItem: \n" + XmlUtil.docAsString(orderItem));
	}
}
