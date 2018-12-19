package io.github.pleuvoir;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.pleuvoir.chapter11.Config11;

public class ChapterTest11 {

	@Test
	public void context() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config11.class);
		for (String beanDefinitionName : app.getBeanDefinitionNames()) {
			System.out.println(beanDefinitionName);
		}
		// 容器中注册的 bean 是 config11 和 initMainConfig，但是 initMainConfig 这个类中@Bean 的没有被初始化
		app.close();
	}

}
