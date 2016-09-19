package middleware;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class MyWebAppInitializer implements ServletContextInitializer  {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		XmlWebApplicationContext root = new XmlWebApplicationContext();
	    root.setConfigLocation("/WEB-INF/applicationContext.xml");
	    servletContext.addListener(new ContextLoaderListener(root));
		
	}

   
}
