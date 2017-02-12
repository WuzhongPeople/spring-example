/**
 * test-gradle
 * io.vilya.common.config
 * 2017年1月23日 下午10:15:59
 */
package io.vilya.example.common.config;

import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import io.vilya.example.web.filter.SecurityInterceptor;

/**
 * @author iamaprin 2017年1月23日 下午10:15:59
 */
@Configuration
@EnableWebMvc
@ComponentScan(value = "io.vilya.example.web.controller")
public class SpringWebConfig extends WebMvcConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(SpringWebConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/**");
    }
    
    /**
     * 解析参数
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
	
    }
    
    /**
     * 解析返回值
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().indentOutput(true)
		.dateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
		
	converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }
}
