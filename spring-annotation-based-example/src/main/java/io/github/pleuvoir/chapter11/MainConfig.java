package io.github.pleuvoir.chapter11;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.pleuvoir.base.User;

// 这是一个别人提供的类，我现在需要加入到我的项目中，目的是使用 User。
@Configuration
public class MainConfig {

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	@Bean
	public User initUser() {
		System.out.println("我想让内部类被初始化..并且依赖于 name 的值");
		return new User(this.name, 18);
	}

}
