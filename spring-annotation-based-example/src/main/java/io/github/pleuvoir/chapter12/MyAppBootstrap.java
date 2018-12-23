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
	}

}
