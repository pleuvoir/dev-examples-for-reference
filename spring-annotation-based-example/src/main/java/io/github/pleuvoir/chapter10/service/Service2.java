package io.github.pleuvoir.chapter10.service;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import io.github.pleuvoir.base.User;

@Service
@Order(2)
public class Service2 {

	public Service2() {
		System.out.println("构造Service2");
	}
	
	@Bean
	public User pleuvoir2() {
		return new User("pleuvoir2", 18);
	}
}
