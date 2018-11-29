package io.github.pleuvoir.base;

import org.springframework.context.annotation.Bean;

public class Dog {

	@Bean
	public Fish Fish() {
		return new Fish();
	}
}
