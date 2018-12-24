package io.github.pleuvoir;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.pleuvoir.chapter07.AppConfig;
import io.github.pleuvoir.chapter07.Config7;
import io.github.pleuvoir.chapter07.location.AppInfoService;
import io.github.pleuvoir.chapter07.wrap.EnvironmentWrapper;

public class ChapterTest07 {

	@Test
	public void context() throws IOException {
		
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();
		app.register(Config7.class);
		app.getEnvironment().setActiveProfiles("dev");
		app.refresh();
		System.out.println(app.getBean(AppConfig.class));
		
		EnvironmentWrapper env = app.getBean(EnvironmentWrapper.class);
		System.out.println(env.getString("example.name"));
		System.out.println(env.getInteger("age", 26));
		System.out.println(env.getInteger("example.version"));
		System.out.println(env.getBoolean("example.not.exist", true));
		
		// 通过 location 读取
		AppInfoService appInfoService = app.getBean(AppInfoService.class);
		appInfoService.show();
		
		
		// 通过 PropertiesFactoryBean 
		System.out.println("===========通过 PropertiesFactoryBean==========");
		Properties pp = app.getBean("appProperties", Properties.class);
		
		pp.entrySet().forEach(k -> {
			System.err.println(k);
		});
		
		app.close();
	
	}

}
