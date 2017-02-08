/**
 * spring-example
 * io.vilya.common
 * 2017年2月5日 上午10:14:03
 */
package io.vilya.example.common;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import io.vilya.example.common.utils.PropertyUtils;

/**
 * @author iamaprin
 * 2017年2月5日 上午10:14:03
 */
public class CustomizedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
	    throws BeansException {
	super.processProperties(beanFactoryToProcess, props);
	
	PropertyUtils.init(props);
    }
    
}
