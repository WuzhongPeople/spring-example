/**
 * spring-example
 * io.vilya.example.common.config
 * 2017年2月9日 下午10:37:16
 */
package io.vilya.example.common.config;

import javax.sql.DataSource;

import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

import io.vilya.example.common.utils.PropertyUtils;

/**
 * 工作流框架Flowable配置
 * @author iamaprin
 * 2017年2月9日 下午10:37:16
 */
@Configuration
public class FlowableConfig {
    
    private static final Logger LOG = LoggerFactory.getLogger(FlowableConfig.class);
    
    private static final PropertyUtils propertyUtils = PropertyUtils.getInstance();
    
    @Bean
    public DataSource flowableDataSource() {
	LOG.info("flowable dataSource inited.");
	
	DruidDataSource dataSource = new DruidDataSource();
	dataSource.setDriverClassName(propertyUtils.get("spring.datasource.driverClassNam"));
	dataSource.setInitialSize(propertyUtils.getInt("spring.datasource.initialSize"));
	dataSource.setMinIdle(propertyUtils.getInt("spring.datasource.minIdle"));
	dataSource.setMaxActive(propertyUtils.getInt("spring.datasource.maxActive"));
	
	dataSource.setUrl(propertyUtils.get("db.flowable.url"));
	dataSource.setUsername(propertyUtils.get("db.flowable.username"));
	dataSource.setPassword(propertyUtils.get("db.flowable.password"));
	
	return dataSource;
    }
    
    @Bean
    public StandaloneProcessEngineConfiguration engineConfiguration(DataSource flowableDataSource) {
	StandaloneProcessEngineConfiguration engineConfiguration = 
		new StandaloneProcessEngineConfiguration();
	
	engineConfiguration.setDataSource(flowableDataSource);
	engineConfiguration.setDatabaseSchemaUpdate("true");
	
	return engineConfiguration;
    }
    
    @Bean
    public ProcessEngine processEngine(StandaloneProcessEngineConfiguration engineConfiguration) {
	return engineConfiguration.buildProcessEngine();
    }
    
    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) {
	return processEngine.getRuntimeService();
    }
    
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {
	return processEngine.getRepositoryService();
    }
    
    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
	return processEngine.getTaskService();
    }
    
    @Bean
    public ManagementService managementService(ProcessEngine processEngine) {
	return processEngine.getManagementService();
    }
    
    @Bean
    public IdentityService identityService (ProcessEngine processEngine) {
	return processEngine.getIdentityService();
    }
    
    @Bean
    public HistoryService historyService(ProcessEngine processEngine) {
	return processEngine.getHistoryService();
    }
    

    
}






