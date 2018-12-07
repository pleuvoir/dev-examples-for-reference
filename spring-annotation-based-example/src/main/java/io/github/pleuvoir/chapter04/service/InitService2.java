package io.github.pleuvoir.chapter04.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class InitService2 {

	public InitService2() {
		System.out.println("InitService2 构造方法被调用");
	}

	@PostConstruct
	public void init() {
		System.out.println("执行 InitService2 init()");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("执行 InitService2 destroy()");
	}
}
