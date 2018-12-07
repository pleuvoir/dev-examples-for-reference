package io.github.pleuvoir.chapter10.service;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import io.github.pleuvoir.base.User;

@Service
@Order(1)
public class Service1 {

	public Service1() {
		System.out.println("构造Service1");
	}
	
	@Bean
	public User pleuvoir1() {
		return new User("pleuvoir1", 18);
	}
}
