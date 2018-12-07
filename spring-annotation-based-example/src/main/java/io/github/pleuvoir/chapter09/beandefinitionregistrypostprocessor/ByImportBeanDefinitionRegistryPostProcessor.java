package io.github.pleuvoir.chapter09.beandefinitionregistrypostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import io.github.pleuvoir.chapter09.registrar.ByImportBean;

/**
 * 这个接口是 {#BeanFactoryPostProcessor} 的扩展
 * @author pleuvoir
 *
 */
public class ByImportBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		 // left intentionally blank
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

		BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(ByImportBean.class);
		
		// 这里修改初始化方法，会发现走的是  ByImportBeanFactoryPostProcessor 注册时设置的，也就是说，这个注册器是先于 BeanFactoryPostProcessor 被调用的，所以会被覆盖
		definition.setInitMethodName("initBeanDefinitionRegistryPostProcessor");
		
		registry.registerBeanDefinition(ByImportBean.class.getName(), definition.getBeanDefinition());
		
		System.out.println("3.ByImportBeanDefinitionRegistryPostProcessor 动态注册完成，bean -> " + ByImportBean.class.getName());
	}

}
