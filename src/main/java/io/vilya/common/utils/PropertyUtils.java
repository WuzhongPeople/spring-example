/**
 * spring-example
 * io.vilya.common
 * 2017年2月5日 上午9:10:34
 */
package io.vilya.common.utils;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author iamaprin
 * 2017年2月5日 上午9:10:34
 */
public class PropertyUtils {
    
    private static final Logger LOG  = LoggerFactory.getLogger(Properties.class);
    
    private static Properties _properties = new Properties();
    private static final PropertyUtils INSTANCE = new PropertyUtils();
    
    
    public static PropertyUtils getInstance() {
	return INSTANCE;
    }
    
    public static void init(Properties properties) {
	LOG.info("PropertyUtils inited.");
	_properties = properties;
    }
    
    public String get(String key) {
	return get(key, StringUtils.EMPTY);
    }
    
    public String get(String key, String defaultvalue) {
	return _properties.getProperty(key, defaultvalue);
    }
    
    public int getInt(String key) {
	return getInt(key, Integer.MIN_VALUE);
    }
    
    public int getInt(String key, int defaultValue) {
	String value = get(key);
	return StringUtils.isEmpty(value) ? defaultValue : Integer.valueOf(value);
    }
    
    
}
