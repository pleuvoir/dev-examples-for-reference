package io.github.pleuvoir.chapter07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;

import io.github.pleuvoir.chapter07.location.AppInfoService;
import io.github.pleuvoir.chapter07.location.DefaultAppInfoService;
import io.github.pleuvoir.chapter07.wrap.EnvironmentWrapper;

@Configuration
@Import({ EnvironmentWrapper.class })
@ComponentScan("io.github.pleuvoir.chapter07")
public class Config7 {

	@Autowired
	private EnvironmentWrapper environmentWrapper;
	
	@Value("classpath:/config.properties")
	private Resource configFile;
	
	@Bean
	public AppInfoService appInfoService() {
		System.out.println("文件加载了:" + configFile);
		AppInfoService appInfoService = new DefaultAppInfoService();
		// appInfoService.setLocation("classpath:dev.properties");
		appInfoService.setLocation(environmentWrapper.getString("appInfoService.location", "classpath:dev.properties"));
		return appInfoService;
	}
}
