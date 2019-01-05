package io.github.pleuvoir.chapter12;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyAppBootstrap extends AnnotationConfigApplicationContext {

	public MyAppBootstrap(Class<?>... annotatedClasses) {
		super(annotatedClasses);
	}

	@Override
	protected void initPropertySources() {
		System.out.println("增加必要属性 VAR，当启动参数没有时会抛出异常 。");
		getEnvironment().setRequiredProperties("VAR");
		
		// 这里容器还未初始化，不可采用注入的形式，可以通过 new 的形式加入
		//getEnvironment().getPropertySources().addLast();
	}

}
