package middleware;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Main {
 
    public static void main(String[] args) {
    	SpringApplication.run(Main.class, args);
    }
 
    @Bean
    public ServletRegistrationBean dispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/callback/*");
    }
 
    @Bean(name=Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
    	SpringBus bus = new SpringBus();
//    	bus.getInInterceptors().add(new LoggingInInterceptor());
        return bus;
    }
}