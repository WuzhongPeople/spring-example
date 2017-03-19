/**
 * test-gradle
 * io.vilya.common.config
 * 2017年1月23日 下午9:08:25
 */
package io.vilya.example.common.config;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.ServletContextAware;

import com.alibaba.druid.pool.DruidDataSource;

import io.vilya.example.common.CustomizedPropertyPlaceholderConfigurer;
import io.vilya.example.common.utils.PropertyUtils;

/**
 * @author iamaprin
 * 2017年1月23日 下午9:08:25
 */
@Configuration
@EnableTransactionManagement
@Import({ FlowableConfig.class, SpringSecurityConfig.class})
@ComponentScan(basePackages = {"io.vilya.example.model.dao", "io.vilya.example.service"})
public class ApplicationConfig implements ServletContextAware {
    
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfig.class);
    
    private ServletContext servletContext;
    
    private static final PropertyUtils propertyUtils = PropertyUtils.getInstance();
    
    @Bean(initMethod="init", destroyMethod="close")
    public DataSource dataSource() {
	LOG.info("core dataSource inited.");
	
	DruidDataSource dataSource = new DruidDataSource();
	dataSource.setDriverClassName(propertyUtils.get("spring.datasource.driverClassNam"));
	dataSource.setInitialSize(propertyUtils.getInt("spring.datasource.initialSize"));
	dataSource.setMinIdle(propertyUtils.getInt("spring.datasource.minIdle"));
	dataSource.setMaxActive(propertyUtils.getInt("spring.datasource.maxActive"));
	
	dataSource.setUrl(propertyUtils.get("db.core.url"));
	dataSource.setUsername(propertyUtils.get("db.core.username"));
	dataSource.setPassword(propertyUtils.get("db.core.password"));
	
	return dataSource;
    }
    
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
	ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(); 
	
	SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
	sqlSessionFactory.setDataSource(dataSource);
	sqlSessionFactory.setMapperLocations(resourcePatternResolver.getResources("io/vilya/example/model/mapper/*.xml"));
	return sqlSessionFactory;
    }
    
    /**
     * 配置事务
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
	return new DataSourceTransactionManager(dataSource);
    }
    
    /**
     * 配置扫描mapper接口
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
	String basePackage = "io.vilya.example.model.dao";
	
	MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
	mapperScannerConfigurer.setBasePackage(basePackage);
	return mapperScannerConfigurer;
    }
    
    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
	PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new CustomizedPropertyPlaceholderConfigurer();
	propertyPlaceholderConfigurer.setLocations(getLocations());
	return propertyPlaceholderConfigurer;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
	// TODO Auto-generated method stub
	this.servletContext = servletContext;
    }
    
    
    /*-----private function-----*/
    private String getConfigPath(String configName) {
	String configHome = (String) servletContext.getAttribute("config.home");
	return configHome + File.separator + configName;
    }
    
    private String[] getConfigFiles() {
	String configs = (String) servletContext.getAttribute("configs");
	return configs.split(File.pathSeparator);
    }
    
    private Resource[] getLocations() {
	String[] configFiles = getConfigFiles();
	int len = configFiles.length;
	
	Resource[] locations = new Resource[len];
	Resource resource;
	for (int i = 0; i < len; i++) {
	    resource = new FileSystemResource(getConfigPath(configFiles[i]));
	    locations[i] = resource;
	}
	
	return locations;
    }
}



