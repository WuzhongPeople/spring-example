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
import org.flowable.spring.ProcessEngineFactoryBean;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

import io.vilya.example.common.constant.FlowableConsts;
import io.vilya.example.common.utils.PropertyUtils;

/**
 * 工作流框架Flowable配置
 * @author iamaprin
 * 2017年2月9日 下午10:37:16
 */
@Configuration
@EnableTransactionManagement
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
    public DataSourceTransactionManager flowableTransactionManager(DataSource flowableDataSource) {
	return new DataSourceTransactionManager(flowableDataSource);
    }
    
    @Bean
    public SpringProcessEngineConfiguration engineConfiguration(DataSource flowableDataSource, DataSourceTransactionManager transactionManager) {
	SpringProcessEngineConfiguration engineConfiguration = 
		new SpringProcessEngineConfiguration();
	
	engineConfiguration.setDataSource(flowableDataSource);
	engineConfiguration.setTransactionManager(transactionManager);
	engineConfiguration.setDatabaseSchemaUpdate("true");
	engineConfiguration.setAsyncExecutorActivate(false);
	engineConfiguration.setActivityFontName(FlowableConsts.DEFAULT_FONT);
	engineConfiguration.setLabelFontName(FlowableConsts.DEFAULT_FONT);
	
	return engineConfiguration;
    }
    
    @Bean
    public ProcessEngineFactoryBean processEngine(SpringProcessEngineConfiguration engineConfiguration) {
	ProcessEngineFactoryBean processEngine = new ProcessEngineFactoryBean();
	processEngine.setProcessEngineConfiguration(engineConfiguration);
	return processEngine;
    }
    
    @Bean
    public RuntimeService runtimeService(ProcessEngineFactoryBean processEngine) throws Exception {
	return processEngine.getObject().getRuntimeService();
    }
    
    @Bean
    public RepositoryService repositoryService(ProcessEngineFactoryBean processEngine) throws Exception {
	return processEngine.getObject().getRepositoryService();
    }
    
    @Bean
    public TaskService taskService(ProcessEngineFactoryBean processEngine) throws Exception {
	return processEngine.getObject().getTaskService();
    }
    
    @Bean
    public ManagementService managementService(ProcessEngineFactoryBean processEngine) throws Exception {
	return processEngine.getObject().getManagementService();
    }
    
    @Bean
    public IdentityService identityService (ProcessEngineFactoryBean processEngine) throws Exception {
	return processEngine.getObject().getIdentityService();
    }
    
    @Bean
    public HistoryService historyService(ProcessEngineFactoryBean processEngine) throws Exception {
	return processEngine.getObject().getHistoryService();
    }
    

    
}






