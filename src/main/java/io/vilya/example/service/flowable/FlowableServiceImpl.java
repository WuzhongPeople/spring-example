/**
 * spring-example
 * io.vilya.example.service.flowable
 * 2017年2月10日 上午12:14:28
 */
package io.vilya.example.service.flowable;

import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author iamaprin
 * 2017年2月10日 上午12:14:28
 */
public class FlowableServiceImpl implements FlowableService {
    
    @Autowired
    private RuntimeService runtimeService;

}
