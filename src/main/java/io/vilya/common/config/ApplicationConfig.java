/**
 * test-gradle
 * io.vilya.common.config
 * 2017年1月23日 下午9:08:25
 */
package io.vilya.common.config;

import java.io.File;
import java.util.Arrays;
import java.util.function.Consumer;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.ServletContextAware;

import com.alibaba.druid.pool.DruidDataSource;

import io.vilya.common.CustomizedPropertyPlaceholderConfigurer;
import io.vilya.common.utils.PropertyUtils;

/**
 * @author iamaprin
 * 2017年1月23日 下午9:08:25
 */
@Configuration
public class ApplicationConfig implements ServletContextAware {
    
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfig.class);
    
    private ServletContext servletContext;
    
    private static final PropertyUtils propertyUtils = PropertyUtils.getInstance();
    
    @Bean(initMethod="init", destroyMethod="close")
    public DataSource dataSource() {
	DruidDataSource dataSource = new DruidDataSource();
	dataSource.setDriverClassName(propertyUtils.get("spring.datasource.driverClassNam"));
	dataSource.setUrl(propertyUtils.get("spring.datasource.url"));
	dataSource.setUsername(propertyUtils.get("spring.datasource.username"));
	dataSource.setPassword(propertyUtils.get("spring.datasource.password"));
	dataSource.setInitialSize(propertyUtils.getInt("spring.datasource.initialSize"));
	dataSource.setMinIdle(propertyUtils.getInt("spring.datasource.minIdle"));
	dataSource.setMaxActive(propertyUtils.getInt("spring.datasource.maxActive"));
	
	return dataSource;
    }
    
    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
	PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new CustomizedPropertyPlaceholderConfigurer();
		
	propertyPlaceholderConfigurer.setLocations(getLocations());
	return propertyPlaceholderConfigurer;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
	// TODO Auto-generated method stub
	this.servletContext = servletContext;
    }
    
    private String getConfigPath(String configName) {
	String configHome = (String) servletContext.getAttribute("config.home");
	return configHome + File.separator + configName;
    }
    
    private String[] getConfigFiles() {
	String configs = (String) servletContext.getAttribute("configs");
	return configs.split(File.pathSeparator);
    }
    
    private Resource[] getLocations() {
	String[] configFiles = getConfigFiles();
	int len = configFiles.length;
	
	Resource[] locations = new Resource[len];
	Resource resource;
	for (int i = 0; i < len; i++) {
	    resource = new FileSystemResource(getConfigPath(configFiles[i]));
	    locations[i] = resource;
	}
	
	return locations;
    }
}



