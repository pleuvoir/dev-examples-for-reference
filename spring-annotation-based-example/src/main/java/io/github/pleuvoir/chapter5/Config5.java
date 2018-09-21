package io.github.pleuvoir.chapter5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import io.github.pleuvoir.chapter5.service.NomalUserService;
import io.github.pleuvoir.chapter5.service.UnNomalUserService;
import io.github.pleuvoir.chapter5.service.UserService;

@Configuration
@ComponentScan("io.github.pleuvoir.chapter5")
public class Config5 {

	@Primary
	@Bean
	public UserService nomalUserService(){
		return new NomalUserService();
	}
	
	@Bean
	public UserService unNomalUserService(){
		return new UnNomalUserService();
	}
	
}
