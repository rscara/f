/*
 * Copyright 2002-2012 the original author or authors.
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.w3c.dom.Document;


public class Main {

	public static void main(String[] args) throws Exception {
		AbstractApplicationContext applicationContext = 
			new ClassPathXmlApplicationContext(new String[]{
					"/META-INF/middleware/context.xml",
					"/META-INF/middleware/jms.xml"
					},
					Main.class);
		MessageChannel messageChannel = (MessageChannel) applicationContext.getBean("inboxChannel");
		GenericMessage<Document> orderMessage = 
			createXmlMessageFromResource("META-INF/middleware/order.xml");
		messageChannel.send(orderMessage);
		applicationContext.close();
	}

	private static GenericMessage<Document> createXmlMessageFromResource(String path) throws Exception {
		Resource orderRes = new ClassPathResource(path);

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		DocumentBuilder builder = builderFactory.newDocumentBuilder();

		Document orderDoc = builder.parse(orderRes.getInputStream());
		return new GenericMessage<Document>(orderDoc);
	}

}
