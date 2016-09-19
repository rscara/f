package middleware.splitter;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.xml.xpath.XPathExpression;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class OrderSplitter extends AbstractMessageSplitter{
	
	private static final Logger logger = Logger.getLogger(OrderSplitter.class);
	
	private final XPathExpression selectHeadXpath;
	private final XPathExpression selectDetailXpath;
	private final XPathExpression selectItemsXpath;
	
	public OrderSplitter(XPathExpression selectHeadXpath,XPathExpression selectDetailXpath, XPathExpression selectItemsXpath) {
		this.selectHeadXpath = selectHeadXpath;
		this.selectDetailXpath = selectDetailXpath;
		this.selectItemsXpath = selectItemsXpath;
	}

	@Override
	protected Object splitMessage(Message<?> message) {
		Collection<Message<String>> splittedMessages = new ArrayList<Message<String>>();
		logger.info("Haciendo split de mensajes");
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setNamespaceAware(true);
			Document doc = documentBuilderFactory.newDocumentBuilder().parse(new ByteArrayInputStream(
					((String)message.getPayload()).getBytes()));
			
			Node headNode = selectHeadXpath.evaluateAsNode(doc);
			Node detailNode = selectDetailXpath.evaluateAsNode(doc);
			List<Node> itemNodes = selectItemsXpath.evaluateAsNodeList(doc);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			
			for (Node itemNode : itemNodes) {
				Document document = documentBuilderFactory.newDocumentBuilder().newDocument();
				document.appendChild(document.createElement("order"));
				document.getDocumentElement().appendChild(document.importNode(headNode, true));
				document.getDocumentElement().appendChild(document.importNode(detailNode, true));
				document.getDocumentElement().appendChild(document.importNode(itemNode, true));
				
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

				StringWriter writer = new StringWriter();
				transformer.transform(new DOMSource(document), new StreamResult(writer));
				String newMessagePayload = writer.toString();
				
				logger.info("Nuevo mensaje creado:" + newMessagePayload);
				
				Message<String> newMessage = MessageBuilder.withPayload(newMessagePayload).build();
				splittedMessages.add(newMessage);
			}

		} catch (Exception e) {
			logger.error("Error haciendo split de mensajes", e);
		}
		return splittedMessages;
	}

}
