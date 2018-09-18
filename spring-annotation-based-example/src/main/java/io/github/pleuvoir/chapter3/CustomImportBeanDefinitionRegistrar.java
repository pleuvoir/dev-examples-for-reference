package io.github.pleuvoir.chapter3;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import io.github.pleuvoir.base.Pig;

public class CustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
	
		// 如果容器中 有 这个实例 ， 那么我就注册一个 pig 进去
		if(registry.containsBeanDefinition("io.github.pleuvoir.base.Cat")){
			RootBeanDefinition beanDefinition = new RootBeanDefinition(Pig.class);
			registry.registerBeanDefinition("pig", beanDefinition);
		}
		
	}

}
