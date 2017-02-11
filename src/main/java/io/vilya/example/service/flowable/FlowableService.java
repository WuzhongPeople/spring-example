/**
 * spring-example
 * io.vilya.example.service.flowable
 * 2017年2月10日 上午12:08:59
 */
package io.vilya.example.service.flowable;

import org.flowable.engine.repository.Deployment;

/**
 * @author iamaprin
 * 2017年2月10日 上午12:08:59
 */
public interface FlowableService {
    
    Deployment deploy();

    void undeploy(String deploymentId);
    
}
