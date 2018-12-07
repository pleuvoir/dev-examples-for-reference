package io.github.pleuvoir.chapter11;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 这是我项目的主配置类
@Configuration
public class Config11 {

	// 引入别的配置类，但是这个类 MainConfig 中被@Bean标记的方法没有初始化
	@Bean
	public MainConfig initMainConfig() {
		MainConfig mainConfig = new MainConfig();
		mainConfig.setName("James");
		return mainConfig;
	}
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config11.class);
		for (String beanDefinitionName : app.getBeanDefinitionNames()) {
			System.out.println(beanDefinitionName);
		}
		// 容器中注册的 bean 是 config11 和 initMainConfig，但是 initMainConfig 这个类中
		app.close();
	}
}
