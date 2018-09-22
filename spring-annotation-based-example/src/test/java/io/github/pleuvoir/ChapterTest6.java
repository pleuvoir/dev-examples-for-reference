package io.github.pleuvoir;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.pleuvoir.chapter6.Config6;
import io.github.pleuvoir.chapter6.service.BusinessService;

public class ChapterTest6 {

	@Test
	public void context() {
		
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config6.class);
		
		System.out.println("容器初始化完成。。。");
		
		BusinessService service = app.getBean(BusinessService.class);
		service.doService("我是参数");
		app.close();
	
	}

}
