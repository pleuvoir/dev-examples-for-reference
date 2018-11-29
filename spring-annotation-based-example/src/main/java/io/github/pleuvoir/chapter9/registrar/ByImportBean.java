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

	// 只有 ImportBeanDefinitionRegistrar 这种方式， 内部 bean 才会被初始化
	@Bean
	public Cat cat() {
		System.out.println("动态注入bean 内部 @Bean 被初始化 .. cat");
		return new Cat();
	}

	@Bean
	public Dog dog() {
		System.out.println("动态注入bean 内部 @Bean 被初始化 .. dog");
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
