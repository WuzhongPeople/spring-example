/**
 * test-gradle
 * io.vilya.web.filter
 * 2017年1月23日 下午10:18:47
 */
package io.vilya.web.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author iamaprin
 * 2017年1月23日 下午10:18:47
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    
    private static final Logger LOG = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {
	// TODO Auto-generated method stub
	
	
	
	return super.preHandle(request, response, handler);
    }
    
    

}
