/**
 * spring-example
 * io.vilya.common.listener
 * 2017年2月5日 上午8:58:31
 */
package io.vilya.common.listener;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import io.vilya.common.utils.PropertyInitializer;
import io.vilya.common.utils.PropertyUtils;

/**
 * @author iamaprin
 * 2017年2月5日 上午8:58:31
 */
public class CustomizedServletContextListener implements ServletContextListener {

    private static final Logger LOG = LoggerFactory.getLogger(CustomizedServletContextListener.class);
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
	// TODO Auto-generated method stub
	ServletContext context = sce.getServletContext();
	
	try {
	    initConfig(context);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    LOG.error("", e);
	}
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
	// TODO Auto-generated method stub

    }
    
    private void initConfig(ServletContext context) throws IOException {
	Resource resource = new ClassPathResource("config/app.properties");
	Properties properties = new Properties();
	properties.load(resource.getInputStream());
	
	String projectName = properties.getProperty("project.name");
	String configs = properties.getProperty("project.configs");
	
	context.setAttribute("config.home", System.getProperty("user.home") + File.separator + "config" +  File.separator + projectName);
	context.setAttribute("configs", configs);
	
	LOG.info("config.home: " + (String) context.getAttribute("config.home"));
	LOG.info("configs: " + (String) context.getAttribute("configs"));
    }

}
