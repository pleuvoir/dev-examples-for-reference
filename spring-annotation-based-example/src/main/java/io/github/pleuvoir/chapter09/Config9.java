package io.github.pleuvoir.chapter09;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.github.pleuvoir.base.Tiger;
import io.github.pleuvoir.chapter09.beandefinitionregistrypostprocessor.ByImportBeanDefinitionRegistryPostProcessor;
import io.github.pleuvoir.chapter09.beanfactorypostprocessor.ByImportBeanFactoryPostProcessor;
import io.github.pleuvoir.chapter09.registrar.ByImportBeanDefinitionRegistrar;
import io.github.pleuvoir.chapter09.registrar.EnableByImportBeanDefinitionRegistrar;

@SuppressWarnings("all")

// 1. ## 演示 ImportBeanDefinitionRegistrar 方式动态注册 bean，两种方式任选其一，一起使用只会有一个生效
@Import(ByImportBeanDefinitionRegistrar.class)
//@EnableByImportBeanDefinitionRegistrar


// 2. ## 演示 BeanFactoryPostProcessor 方式注册，如果和上面的冲突，这种的会覆盖上面的
//@Import(ByImportBeanFactoryPostProcessor.class)


// 3. ## 演示 ByImportBeanDefinitionRegistryPostProcessor 方式注册，这三种中 BeanFactoryPostProcessor 是会覆盖其他两种的
//@Import(value = {ByImportBeanFactoryPostProcessor.class, ByImportBeanDefinitionRegistryPostProcessor.class})


@Configuration
public class Config9 {

	@Bean
	public Tiger Tiger() {
		return new Tiger("hello, we meet again.");
	}
	
}
