package io.github.pleuvoir.chapter10.service;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import io.github.pleuvoir.base.User;

@Service
@Order(4)
public class Service3 {

	public Service3() {
		System.out.println("构造Service3");
	}

	@Bean
	public User pleuvoir3() {
		return new User("pleuvoir3", 18);
	}
}
