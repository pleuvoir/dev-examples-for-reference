package io.github.pleuvoir.chapter7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.github.pleuvoir.chapter7.location.AppInfoService;
import io.github.pleuvoir.chapter7.location.DefaultAppInfoService;
import io.github.pleuvoir.chapter7.wrap.EnvironmentWrapper;

@Configuration
@Import({ EnvironmentWrapper.class })
@ComponentScan("io.github.pleuvoir.chapter7")
public class Config7 {

	@Autowired
	private EnvironmentWrapper environmentWrapper;

	@Bean
	public AppInfoService appInfoService() {
		AppInfoService appInfoService = new DefaultAppInfoService();
		// appInfoService.setLocation("classpath:dev.properties");
		appInfoService.setLocation(environmentWrapper.getString("appInfoService.location", "classpath:dev.properties"));
		return appInfoService;
	}
}
