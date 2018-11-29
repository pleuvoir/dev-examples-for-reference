package io.github.pleuvoir;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.pleuvoir.chapter9.Config9;
import io.github.pleuvoir.chapter9.registrar.ByImportBean;

public class ChapterTest9 {

	@Test
	public void context() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config9.class);

		String[] beanDefinitionNames = app.getBeanDefinitionNames();
		// 输出容器中所有 bean name
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}

		System.out.println(app.getBean(ByImportBean.class));
		app.close();
	}

}
