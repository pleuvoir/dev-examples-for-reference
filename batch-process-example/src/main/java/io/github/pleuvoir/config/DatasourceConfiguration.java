package io.github.pleuvoir.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.github.pleuvoir.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@Configuration
@PropertySource("classpath:/application.properties")
public class DatasourceConfiguration {

	@Autowired
	private Environment environment;

	@Bean(name = "dataSource", destroyMethod = "close")
	public DruidDataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.druid.driver-class-name"));
		dataSource.setUrl(environment.getProperty("spring.datasource.druid.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.druid.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.druid.password"));
		return dataSource;
	}


	@Bean(name = Const.JDBC_TEMPLATE_NAME_MASTER)
	public JdbcTemplate master() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		jdbcTemplate.setFetchSize(Integer.MIN_VALUE); //流式处理
		log.info("master JdbcTemplate inited");
		return jdbcTemplate;
	}

	@Bean(name = Const.JDBC_TEMPLATE_NAME_SLAVE)
	public JdbcTemplate slave() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		jdbcTemplate.setFetchSize(Integer.MIN_VALUE); //流式处理
		log.info("slave JdbcTemplate inited");
		return jdbcTemplate;
	}
}
