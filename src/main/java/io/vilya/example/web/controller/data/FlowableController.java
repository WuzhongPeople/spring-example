/**
 * spring-example
 * io.vilya.example.web.controller.data
 * 2017年2月11日 上午12:02:38
 */
package io.vilya.example.web.controller.data;

import org.flowable.engine.repository.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.vilya.example.service.flowable.FlowableService;

/**
 * @author iamaprin
 * 2017年2月11日 上午12:02:38
 */
@RestController("dataFlowableController")
@RequestMapping("/data/flowable")
public class FlowableController {
    
    private static final Logger LOG = LoggerFactory.getLogger(FlowableController.class);
    
    @Autowired
    private FlowableService flowableService;
    
    @RequestMapping("")
    public String index() {
	return "index";
    }
    
    @RequestMapping("/deploy")
    public String deploy() {
	Deployment deploy = flowableService.deploy();
	return deploy.getId() + ": " + deploy.getKey() + ": " + deploy.getName();
    }
    
    @RequestMapping("/undeploy")
    public String undeploy(@RequestParam String id) {
	try {
	    flowableService.undeploy(id);	    
	} catch (Exception e) {
	    LOG.error("", e);
	}
	
	return "undeploy";
    }
    
    @RequestMapping("/test")
    public String test() {
	flowableService.test();
	return "test";
    }
}




