package io.github.pleuvoir;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.pleuvoir.chapter3.Config3;

public class ChapterTest3 {

	@Test
	public void context() {
		
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config3.class);
		
		String[] beanDefinitionNames = app.getBeanDefinitionNames();
		
		// 输出容器中所有 bean name
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
		
		// 使用 factory 注册时 ，bean name 为注册时 CustomFactoryBean 的全限定类名，但是取出来的对象是 自己自定义的泛型实现类
		Object bean = app.getBean("io.github.pleuvoir.chapter3.CustomFactoryBean");
		System.out.println(bean);	// result： io.github.pleuvoir.base.Fish@6ab7a896
		
		// 前面加个 & 可以拿到原始的
		Object bean2 = app.getBean("&io.github.pleuvoir.chapter3.CustomFactoryBean");
		System.out.println(bean2);	// result： io.github.pleuvoir.chapter3.CustomFactoryBean@6ab7a896
	}

}
