package io.github.pleuvoir.chapter9;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("io.github.pleuvoir.chapter9")
public class Config9 {

	@Value(value = "${example.name}")
	private String name;


	@Bean
	public Test appConfig() throws IOException {
		Test test = new Test();
		test.setLocation("classpath:dev.properties");
		return test;
	}


}
