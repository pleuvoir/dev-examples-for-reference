package io.github.pleuvoir.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import lombok.Data;

@Data
@Configuration
@PropertySource(value = { "classpath:config.properties", "classpath:application.properties" })
public class AppConfig {

	@Value("${querySql}")
	private String querySql;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

}
