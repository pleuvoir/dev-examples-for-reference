package io.github.pleuvoir.chapter10.service;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import io.github.pleuvoir.base.User;

@Service
@Order(3)
public class Service4 {

	public Service4() {
		System.out.println("构造Service4");
	}
	
	@Bean
	public User pleuvoir4() {
		return new User("pleuvoir3", 18);
	}
}
