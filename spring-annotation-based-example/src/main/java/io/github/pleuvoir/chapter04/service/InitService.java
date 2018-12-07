package io.github.pleuvoir.chapter04.service;

public class InitService {

	public InitService() {
		System.out.println("InitService 构造方法被调用");
	}

	public void init() {
		System.out.println("执行 InitService init()");
	}

	public void destroy() {
		System.out.println("执行 InitService destroy()");
	}
}
