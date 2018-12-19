package io.github.pleuvoir;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.pleuvoir.chapter10.Config10;

public class ChapterTest10 {

	@Test
	public void context() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext("io.github.pleuvoir.chapter10");
		
		// 使用如下这种方式无效
	//	AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config10.class);

		String[] beanDefinitionNames = app.getBeanDefinitionNames();
		// 输出容器中所有 bean name
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
		app.close();
	}

}
