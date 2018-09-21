package io.github.pleuvoir.chapter5.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import io.github.pleuvoir.chapter5.service.UnAvailableService;
import io.github.pleuvoir.chapter5.service.UserService;

@Controller
public class UserController {
	
	@Autowired 
	@Qualifier("unNomalUserService")	// 如果有多种实现，则可以通过此方式指定 bean 的名称，选择性注入。
	private UserService unNomalUserService;
	
	@Autowired		// 当不指定名称时默认选择被 primary 标记的实现类
	private UserService primaryuserService;
	
	private UserService userServiceBySetMethod;
	
	
	private UserService userServiceByConstructor;
	
	@Autowired
	public void setUserServiceBySetMethod(UserService userServiceBySetMethod) {
		this.userServiceBySetMethod = userServiceBySetMethod;
	}

	
	// 当 有默认构造方法时， spring 注入会优先走无参构造
	public UserController() {
		System.out.println("无参 UserController 构造。");
	}
	
	@Autowired 	// 使用注解标记后，则 spring 会走此构造来初始化，注意：正常情况下 不需要注解 spring 也是可以正常注入的
	public UserController(UserService userServiceByConstructor, 
						@Autowired(required = false) UnAvailableService UnAvailableService) { // 使用 false 不存在也不会报错
		System.out.println("有参 UserController 构造。");
		this.userServiceByConstructor = userServiceByConstructor;
	}
	
	
	public void handle() {
		userServiceByConstructor.tellMe("我是通过 有参构造方法 注入的  user(" + ThreadLocalRandom.current().nextInt(6) + ")");
		userServiceBySetMethod.tellMe("我是通过 set 方法注入的  user(" + ThreadLocalRandom.current().nextInt(6) + ")");
		primaryuserService.tellMe("我是通过 @Primary 注入的user(" + ThreadLocalRandom.current().nextInt(6) + ")");
		unNomalUserService.tellMe("我是通过 @Qualifier 注入的user(" + ThreadLocalRandom.current().nextInt(6) + ")");
	}
	
}
