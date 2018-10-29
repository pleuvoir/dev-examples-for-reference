package io.github.pleuvoir;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.pleuvoir.chapter7.AppConfig;
import io.github.pleuvoir.chapter7.Config7;
import io.github.pleuvoir.chapter7.location.AppInfoService;
import io.github.pleuvoir.chapter7.wrap.EnvironmentWrapper;

public class ChapterTest7 {

	@Test
	public void context() throws IOException {
		
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config7.class);
		System.out.println(app.getBean(AppConfig.class));
		
		EnvironmentWrapper env = app.getBean(EnvironmentWrapper.class);
		System.out.println(env.getString("example.name"));
		System.out.println(env.getInteger("age", 26));
		System.out.println(env.getInteger("example.version"));
		System.out.println(env.getBoolean("example.not.exist", true));
		
		// 通过 location 读取
		AppInfoService appInfoService = app.getBean(AppInfoService.class);
		appInfoService.show();
		app.close();
	
	}

}
