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

import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Main {

	public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

//		AbstractApplicationContext applicationContext = 
//			new ClassPathXmlApplicationContext(new String[]{
//					"/META-INF/middleware/context.xml",
//					"/META-INF/middleware/jms.xml"
//					},
//					Main.class);
		/*MessageChannel messageChannel = (MessageChannel) applicationContext.getBean("inboxChannel");
		Message<String> message = MessageBuilder.withPayload(createXmlMessageFromResource("META-INF/middleware/order.xml")).build();
		messageChannel.send(message);*/
	}

	private static String createXmlMessageFromResource(String path) throws Exception {
		Resource orderRes = new ClassPathResource(path);
		return IOUtils.toString(orderRes.getInputStream());
	}

}
