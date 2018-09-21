package io.github.pleuvoir.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.pleuvoir.chapter5.Config5;
import io.github.pleuvoir.chapter5.controller.UserController;

public class ChapterTest5 {

	@Test
	public void context() {
		
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config5.class);
		
		System.out.println("容器初始化完成。。。");
		
		UserController userController = app.getBean(UserController.class);
		userController.handle();
		app.close();
	
	}

}
