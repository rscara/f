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
	private final XPathExpression itemsSelectingXPath;
	private final XPathExpression itemTotalSelectingXPath;
	private final XPathExpression itemQuantitySelectingXPath;
	private static final Logger logger = Logger.getLogger(OrderTotalChecker.class);

	public OrderTotalChecker(XPathExpression isbnSelectingXPath,XPathExpression itemsSelectingXPath,XPathExpression itemTotalSelectingXPath,XPathExpression itemQuantitySelectingXPath) {
		this.totalSelectingXPath = isbnSelectingXPath;
		this.itemsSelectingXPath = itemsSelectingXPath;
		this.itemTotalSelectingXPath = itemTotalSelectingXPath;
		this.itemQuantitySelectingXPath = itemQuantitySelectingXPath;
	}

	public Document checkStockLevel(Document doc) {
		boolean valid = false;
		try{
			String tot = totalSelectingXPath.evaluateAsString(doc);
			List<Node> items = itemsSelectingXPath.evaluateAsNodeList(doc);
			BigDecimal total = new BigDecimal(tot);
			for (Node node : items) {
				System.out.println(node.getTextContent());
				BigDecimal quantity=new BigDecimal(itemQuantitySelectingXPath.evaluateAsString(node));
				BigDecimal price=new BigDecimal(itemTotalSelectingXPath.evaluateAsString(node));
				total=total.subtract(quantity.multiply(price));
			}
			if(total.compareTo(BigDecimal.ZERO)==0)
				valid=true;
		}catch(Exception e){
			logger.error("Total valdiation error,",e);
		}
		doc.getDocumentElement().setAttribute("valid-total", String.valueOf(valid));
		return doc;
	}

}
