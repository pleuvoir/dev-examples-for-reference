package io.github.pleuvoir.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.pleuvoir.chapter2.Config2;

public class ChapterTest2 {

	@Test
	public void context() {
		
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config2.class);
		
		String[] beanDefinitionNames = app.getBeanDefinitionNames();
		
		// 输出容器中所有 bean name
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
		
	}

}
