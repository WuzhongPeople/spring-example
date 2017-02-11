/**
 * test-gradle
 * io.vilya.web.controller
 * 2017年1月23日 下午11:12:53
 */
package io.vilya.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.vilya.example.service.test.TestService;
import io.vilya.example.service.test.impl.TestServiceImpl;

/**
 * @author iamaprin
 * 2017年1月23日 下午11:12:53
 */
@RestController
@RequestMapping("/rest")
public class CommonController {
    
    @Autowired
    private TestService testService;
    // private TestServiceImpl testService
    // user class need add "@EnableAspectJAutoProxy(proxyTargetClass=true)" to ApplicationConfig
    
    @RequestMapping("/insert")
    public String insert(@RequestParam String value) {
	return testService.insert(value) + "";
    }
    
    
    
}
