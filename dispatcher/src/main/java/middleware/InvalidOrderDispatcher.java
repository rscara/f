package middleware;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

public class InvalidOrderDispatcher {

private static Logger logger = Logger.getLogger(InvalidOrderDispatcher.class);
	
	public void dispatch(Document orderItem){
		logger.info("Invalid Order: \n" + XmlUtil.docAsString(orderItem));
	}
}
