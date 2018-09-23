package io.github.pleuvoir;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.pleuvoir.chapter7.Config7;
import io.github.pleuvoir.chapter7.conf.AppConfig;
import io.github.pleuvoir.chapter7.conf.EnvironmentWrapper;

public class ChapterTest7 {

	@Test
	public void context() {
		
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config7.class);
		System.out.println(app.getBean(AppConfig.class));
		
		EnvironmentWrapper env = app.getBean(EnvironmentWrapper.class);
		System.out.println(env.getString("example.name"));
		System.out.println(env.getInteger("age", 26));
		System.out.println(env.getInteger("example.version"));
		System.out.println(env.getBoolean("example.not.exist", true));
		app.close();
	
	}

}
