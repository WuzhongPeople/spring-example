/**
 * spring-example
 * io.vilya.example.service.flowable
 * 2017年2月10日 上午12:14:28
 */
package io.vilya.example.service.flowable.impl;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.vilya.example.service.flowable.FlowableService;
import io.vilya.example.service.flowable.FlowableService;

/**
 * @author iamaprin
 * 2017年2月10日 上午12:14:28
 */
@Service
@Transactional("flowableTransactionManager")
public class FlowableServiceImpl implements FlowableService {
    
    @Autowired
    private RuntimeService runtimeService;
    
    @Autowired
    private RepositoryService repositoryService;
    
    @Override
    public Deployment deploy() {
	// TODO Auto-generated method stub
	Deployment deployment =  repositoryService.createDeployment()
		.name("test")
		.addClasspathResource("diagram/test.bpmn")
		.deploy();
	
	return deployment;
	
    }
    
    @Override
    public void undeploy(String deploymentId) {
	repositoryService.deleteDeployment(deploymentId);
    }
    
    @Override
    public void test() {
	repositoryService.createDeployment()
        	.name("test")
        	.addClasspathResource("diagram/test.bpmn")
        	.deploy();
	
	repositoryService.deleteDeployment("tets");
    }



}
