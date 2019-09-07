package io.github.pleuvoir.springbatch.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@PropertySource("classpath:/batch-hsql.properties")
public class DataSourceConfiguration {

	@Autowired
	private Environment environment;

	@Autowired
	private ResourceLoader resourceLoader;

	@PostConstruct
	protected void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(resourceLoader.getResource(environment.getProperty("init.schema.script")));
		populator.setContinueOnError(true); //存在也不会报错
		DatabasePopulatorUtils.execute(populator, dataSource());
	}

	@Bean(destroyMethod = "close")
	public DruidDataSource dataSource() {
		System.out.println(environment.getProperty("test"));
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.druid.driver-class-name"));
		dataSource.setUrl(environment.getProperty("spring.datasource.druid.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.druid.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.druid.password"));
		return dataSource;
	}

}