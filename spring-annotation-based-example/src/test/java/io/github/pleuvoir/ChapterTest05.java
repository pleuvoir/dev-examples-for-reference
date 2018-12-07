package io.github.pleuvoir;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.pleuvoir.chapter05.Config5;
import io.github.pleuvoir.chapter05.controller.UserController;

public class ChapterTest05 {

	@Test
	public void context() {
		
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config5.class);
		
		System.out.println("容器初始化完成。。。");
		
		UserController userController = app.getBean(UserController.class);
		userController.handle();
		app.close();
	
	}

}
