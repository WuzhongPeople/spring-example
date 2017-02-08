/**
 * test-gradle
 * io.vilya.web.filter
 * 2017年1月23日 下午10:18:47
 */
package io.vilya.example.web.filter;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import io.vilya.example.common.annotation.ClearInterceptor;

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
	
	
	LOG.info(request.getPathInfo());

	HandlerMethod handlerMethod = (HandlerMethod) handler;
	Annotation annotation = handlerMethod.getMethodAnnotation(ClearInterceptor.class);
	if (annotation == null) {
	    LOG.info("no ClearInterceptor");
	} else {
	    LOG.info(annotation.toString());	    
	}
	
	return super.preHandle(request, response, handler);
    }
    
    

}
