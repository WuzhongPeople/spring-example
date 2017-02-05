/**
 * spring-example
 * io.vilya.common
 * 2017年2月5日 上午9:10:34
 */
package io.vilya.common.utils;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author iamaprin
 * 2017年2月5日 上午9:10:34
 */
public class PropertyInitializer {
    
    private static final Logger LOG  = LoggerFactory.getLogger(Properties.class);
    
    public static void init() {
	LOG.info("PropertyInitializer inited.");
	ClassPathResource resource = new ClassPathResource("classpath:config/app.properties");
	
    }
    
    
    
}
