/**
 * test-gradle
 * io.vilya.common.config
 * 2017年1月23日 下午10:15:59
 */
package io.vilya.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import io.vilya.web.filter.SecurityInterceptor;

/**
 * @author iamaprin
 * 2017年1月23日 下午10:15:59
 */
@Configuration
@EnableWebMvc
@ComponentScan(value="io.vilya.web.controller")
public class SpringWebConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/**");
    }
    
    
    
    
    
}
