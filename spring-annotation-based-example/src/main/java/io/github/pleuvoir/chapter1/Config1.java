package io.github.pleuvoir.chapter1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.type.AnnotatedTypeMetadata;

import io.github.pleuvoir.base.User;
import io.github.pleuvoir.chapter1.conditional.RedisCondition;

@Configuration  // 代表这是一个配置类，类似于以前的 spring.xml
@PropertySource(value = {"classpath:dev.properties"})
public class Config1 {

	
	@Bean // bean name 默认为方法名
	public User pleuvoir() {
		return new User("pleuvoir", 18);
	}

	@Bean(name = "i am tom") // 如果指定了bean name，则以此为准
	public User tom() {
		return new User("tom", 26);
	}

	@Scope("prototype")	// 非单例  这里的值只有 prototype 或者 singleton，大小写必须正确
	@Bean
	public User nick() {
		return new User("nick", 26);
	}
	
	@Lazy		// 会进行延迟加载，启动时会注册登记个名字，但不会实例化，只有真正使用时才会创建实例
	@Bean		
	public User lazynick() {
		System.out.println("一个延迟的 lazynick 被创建了 ，我被实例化了。");
		return new User("lazynick", 23);
	}
	
	
	// 满足条件才创建
	@Conditional(value = { WindowsCondition.class })
	@Bean(name = "windows pika")
	public User pika() {
		System.out.println("我 windows pika 只有在 windows 下会被创建   。");
		return new User("windows pika", 666);
	}
	
	/**
	 * 限定条件下创建 bean
	 */
	private static class WindowsCondition implements Condition{
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			String osName = context.getEnvironment().getProperty("os.name");
			System.out.println("当前操作系统： " + osName);
			return osName.contains("Windows");	// true 即创建
		}
	}
	
	
	// 当配置文件redis.type=lettuce
	@Conditional(RedisCondition.class)
	@Bean(name = "propertyCondtion go")
	public User propertyCondtion() {
		System.out.println("我 propertyCondtion 只有在redis.type=lettuce下会被创建   。");
		return new User("windows pika", 999);
	}
	
}
