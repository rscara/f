/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package middleware;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.xml.xpath.XPathExpression;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author Jonas Partner
 */
public class OrderTotalChecker {

	private final XPathExpression totalSelectingXPath;
	private final XPathExpression selectItemTotalsTotalXpath;;
	private static final Logger logger = Logger.getLogger(OrderTotalChecker.class);

	public OrderTotalChecker(XPathExpression isbnSelectingXPath,XPathExpression selectItemTotalsTotalXpath) {
		this.totalSelectingXPath = isbnSelectingXPath;
		this.selectItemTotalsTotalXpath = selectItemTotalsTotalXpath;
	}
	
	private String getXmlProp(Node node,String propName){
		return ((org.w3c.dom.Element)node).getElementsByTagName(propName).item(0).getTextContent();
	}

	public Document checkStockLevel(Document doc) {
		boolean valid = false;
		try{
			BigDecimal total = new BigDecimal(totalSelectingXPath.evaluateAsString(doc));
			List<Node> nodes = selectItemTotalsTotalXpath.evaluateAsNodeList(doc);
			BigDecimal itemTotal = BigDecimal.ZERO;
			for (Node node : nodes) {
				System.out.println(node);
				itemTotal=itemTotal.add(new BigDecimal(getXmlProp(node,"price")).multiply(new BigDecimal(getXmlProp(node,"quantity"))));
				node.appendChild(doc.getElementsByTagName("detail").item(0));
				node.appendChild(doc.getElementsByTagName("head").item(0));
			}
			if(total.compareTo(itemTotal)==0)
				valid=true;
		}catch(Exception e){
			logger.error("Total valdiation error,",e);
		}
		doc.getDocumentElement().setAttribute("valid-total", String.valueOf(valid));
		return doc;
	}

}
