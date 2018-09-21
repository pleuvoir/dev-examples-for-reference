package io.github.pleuvoir.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.pleuvoir.chapter1.Config1;

public class ChapterTest1 {

	@Test
	public void context() {
		
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config1.class);
		
		System.out.println("spring 容器启动完成 。。。");
		String[] beanDefinitionNames = app.getBeanDefinitionNames();
		
		// 输出容器中所有 bean name
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
		
		// 测试延迟加载的 bean
		System.out.println("【准备获取延迟 bean lazynick】");
		app.getBean("lazynick");
		System.out.println("【获取延迟 bean lazynick 完成】");
		
		// 测试非单例的 bean
		Object nick1 = app.getBean("nick");
		Object nick2 = app.getBean("nick");
		System.out.println("两个 nick 实例相同吗？答：" + nick1.equals(nick2));
		
	}

}
