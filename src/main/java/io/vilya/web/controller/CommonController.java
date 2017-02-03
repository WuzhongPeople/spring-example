/**
 * test-gradle
 * io.vilya.web.controller
 * 2017年1月23日 下午11:12:53
 */
package io.vilya.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.vilya.common.annotation.ClearInterceptor;

/**
 * @author iamaprin
 * 2017年1月23日 下午11:12:53
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    
    @RequestMapping("")
    @ResponseBody
    @ClearInterceptor
    public String index() {
	return "test";
    }
    
}
