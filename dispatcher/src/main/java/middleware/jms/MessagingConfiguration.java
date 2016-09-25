package middleware.jms;

import java.io.File;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.store.kahadb.KahaDBStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
public class MessagingConfiguration {
 
    @Bean
    public BrokerService broker() throws Exception{
//    	BrokerService broker = new BrokerService();
//    	broker.setBrokerName("dispatcher");
//    	broker.addConnector("tcp://localhost:61616");
//    	broker.addConnector("vm://localhost");
//    	KahaDBStore persistenceAdapter=new KahaDBStore();
//    	persistenceAdapter.setDirectory(new File("activemq-data-dispatcher"));
//    	broker.setPersistenceAdapter(persistenceAdapter);
//    	broker.start();
//    	return broker;
    	return null;
    }
     
}
