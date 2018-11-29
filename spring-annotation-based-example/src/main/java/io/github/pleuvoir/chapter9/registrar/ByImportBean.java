package io.github.pleuvoir.chapter9.registrar;

import org.springframework.context.annotation.Bean;

import io.github.pleuvoir.base.Cat;
import io.github.pleuvoir.base.Dog;
import io.github.pleuvoir.base.Tiger;

public class ByImportBean {

	private Tiger tiger;
	
	public Tiger getTiger() {
		return tiger;
	}

	public void setTiger(Tiger tiger) {
		this.tiger = tiger;
	}

	// 也会被初始化
	@Bean
	public Cat cat() {
		return new Cat();
	}

	@Bean
	public Dog dog() {
		return new Dog();
	}

	public void initByImportBeanDefinitionRegistrar() {
		System.out.println("1.动态注册  ByImportBean initByImportBeanDefinitionRegistrar() 方法被调用 ..");
	}

	public void initBeanFactoryPostProcessor() {
		System.out.println("2.动态注册  ByImportBean initBeanFactoryPostProcessor() 方法被调用 ..");
	}
	
	public void initBeanDefinitionRegistryPostProcessor() {
		System.out.println("3.动态注册  ByImportBean initBeanDefinitionRegistryPostProcessor() 方法被调用 ..");
	}

	@Override
	public String toString() {
		return "ByImportBean [tiger=" + tiger + "]";
	}
	
}
