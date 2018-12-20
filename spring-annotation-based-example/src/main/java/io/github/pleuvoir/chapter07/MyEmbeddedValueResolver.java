package io.github.pleuvoir.chapter07;

import java.util.Arrays;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * 可以使用其做 spel 属性解析
 * @author pleuvoir
 *
 */
@Component
public class MyEmbeddedValueResolver implements EmbeddedValueResolverAware, EnvironmentAware {

	private Environment environment;

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		String result = resolver.resolveStringValue("你好：${os.name}，计算：#{3*8}");
		System.out.println(result);
		// 这里不能直接拿到 profile
		System.out.println("当前 profile：" + Arrays.asList(environment.getActiveProfiles()));
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
