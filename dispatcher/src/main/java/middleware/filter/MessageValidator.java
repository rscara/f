package middleware.filter;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;
import org.springframework.xml.xpath.XPathExpression;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class MessageValidator implements MessageSelector {
	
	private static final Logger logger = Logger.getLogger(MessageValidator.class);
	
	private final XPathExpression totalSelectingXPath;
	private final XPathExpression selectItemsXpath;
	private final XPathExpression selectCurrencyModeXpath;
	
	public MessageValidator(XPathExpression totalSelectingXPath,XPathExpression selectItemsXpath, XPathExpression selectCurrencyModeXpath) {
		this.totalSelectingXPath = totalSelectingXPath;
		this.selectItemsXpath = selectItemsXpath;
		this.selectCurrencyModeXpath = selectCurrencyModeXpath;
	}

	@Override
	public boolean accept(Message<?> message) {
		try{
			logger.info("Validando mensaje de entrada");
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setNamespaceAware(true);
			Document doc = documentBuilderFactory.newDocumentBuilder().parse(new ByteArrayInputStream(
					((String)message.getPayload()).getBytes()));
			String currency = selectCurrencyModeXpath.evaluateAsString(doc);
			if (!(currency.equals("858") || currency.equals("840"))){
				logger.error("Error de forma de pago:" + currency);
				return false;
			}
			BigDecimal total = new BigDecimal(totalSelectingXPath.evaluateAsString(doc));
			List<Node> nodes = selectItemsXpath.evaluateAsNodeList(doc);
			BigDecimal itemTotal = BigDecimal.ZERO;
			for (Node node : nodes) {
				itemTotal=itemTotal.add(new BigDecimal(getXmlProp(node,"price")).multiply(new BigDecimal(getXmlProp(node,"quantity"))));
			}
			if (total.compareTo(itemTotal)!=0){
				logger.error("Error con totales, Total:" + total + " Items:" + itemTotal);
				return false;
			}
		}catch(Exception e){
			logger.error("Error validando mensaje",e);
			return false;
		}
		return true;
	}
	
	private String getXmlProp(Node node,String propName){
		return ((org.w3c.dom.Element)node).getElementsByTagName(propName).item(0).getTextContent();
	}
}
