package io.github.pleuvoir.chapter13;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.annotation.PostConstruct;

/**
 * 这个类的作用是启动后执行一段sql，即使报错也不会影响启动
 */
@Configuration
@PropertySource("classpath:/batch-hsql.properties") //将属性加载到environment中，如果修饰的是类也可以和类映射，参考07
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