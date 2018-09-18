package io.github.pleuvoir.chapter3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.github.pleuvoir.base.Cat;
import io.github.pleuvoir.base.Dog;
import io.github.pleuvoir.base.User;

@Configuration

// @Import(value = { Dog.class, Cat.class })	// 这种方式和 @bean 没什么区别 bean name 是全限定类名  io.github.pleuvoir.base.Dog

// @Import(value = { CustomImportSelector.class }) // 是一个接口，返回需要导入到容器的组件的全限定类名数组

// @Import(value = { CustomImportBeanDefinitionRegistrar.class, Cat.class })	// 可以手动添加组件到 IOC 容器

@Import(value = { CustomFactoryBean.class })	// 使用 Spring 提供的 FactoryBean 进行注册

public class Config3 {
	
	@Bean // bean name 默认为方法名
	public User pleuvoir() {
		return new User("pleuvoir", 18);
	}

	
}
