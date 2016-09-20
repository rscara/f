package middleware.jdbc;

import java.io.ByteArrayInputStream;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.integration.jdbc.SqlParameterSourceFactory;
import org.springframework.jdbc.core.namedparam.AbstractSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.xml.xpath.XPathExpression;
import org.w3c.dom.Node;

public class  DispatcherSqlParameterSourceFactory implements SqlParameterSourceFactory {

	private Map<String,XPathExpression> expressions;
	
	
	@Override
	public SqlParameterSource createParameterSource(Object paramObject) {
		
		
		
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		DocumentBuilder builder;
		try {
			builder = builderFactory.newDocumentBuilder();
			return new DispatcherSqlParameterSource(builder.parse(new ByteArrayInputStream(((GenericMessage<String>)paramObject).getPayload().getBytes("utf-8"))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}


	public void setExpressions(Map<String, XPathExpression> expressions) {
		this.expressions = expressions;
	}

	private class DispatcherSqlParameterSource extends AbstractSqlParameterSource{
		
		private Node doc;

		public DispatcherSqlParameterSource(Node doc) {
			super();
			this.doc = doc;
		}

		@Override
		public boolean hasValue(String key) {
			return expressions.containsKey(key);
		}
		
		@Override
		public Object getValue(String key) throws IllegalArgumentException {
			return expressions.get(key).evaluateAsNumber(doc);
		}
		
		
		
		
	}
	
}
