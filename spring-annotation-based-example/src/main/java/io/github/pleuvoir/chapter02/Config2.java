package io.github.pleuvoir.chapter02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import io.github.pleuvoir.base.User;

@Configuration

//会将此包下所有基于 @Component 注解子类标记的类全部注册，譬如  @Service @Controller 等
//@ComponentScan("io.github.pleuvoir.chapter02") 

// 会将此包下所有被  @Controller 注解标记的类全部注册，注意此时 useDefaultFilters 必须 为 false，否则无效
//@ComponentScan(value = "io.github.pleuvoir.chapter02", includeFilters = {@Filter(type = FilterType.ANNOTATION, classes = { Controller.class }) }, useDefaultFilters = false)

//也可以自己定义一个过滤器类，制定自己的过滤规则（这里的自定义规则：如果类名包含 `user` 则注册）
@ComponentScan(value = "io.github.pleuvoir.chapter02", includeFilters = {@Filter(type = FilterType.CUSTOM, classes = { CustomTypeFilter.class }) }, useDefaultFilters = false)

// 会将此包下所有不是被   @Controller 注解标记的类全部注入，注意此时 useDefaultFilters 必须 为 true，否则无效
//@ComponentScan(value = "io.github.pleuvoir.chapter02", excludeFilters = {@Filter(type = FilterType.ANNOTATION, classes = { Controller.class }) }, useDefaultFilters = true)


public class Config2 {

	@Bean
	public User pleuvoir() {
		return new User("pleuvoir", 18);
	}

}
