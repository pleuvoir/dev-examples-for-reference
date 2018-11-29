package io.github.pleuvoir.chapter9.registrar;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 演示 spring 动态注册 bean
 * 
 * <ol>	优点：
 * 		<li>可以配合注解使用</li>
 * 		<li>如果注册的这个 bean 也是一个配置类，即该类中包含 被 {@Bean} 标记的方法，这些方法也会执行、会被初始化，这些方法里面如果还有 {@Bean} 将不会递归初始化</li>
 * </ol>
 * 
 * <ol>	使用方式：
 * 		<li>项目配置类中 <code>@Import(ByImportBeanDefinitionRegistrar.class)</code></li>
 * 		<li>或者配合注解使用  <code>@EnableByImportBeanDefinitionRegistrar</code></li>
 * </ol>
 * @author pleuvoir
 *
 */
public class ByImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		// importingClassMetadata 可以取到注解的相关信息，显然可以在注解中定义自己需要的参数来配合动态注册

		// 定义 bean 信息的帮助类多种，选择一种喜欢的
		// RootBeanDefinition beanDefinition = new RootBeanDefinition(ByImportBean.class);

		BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(ByImportBean.class);

		definition.setInitMethodName("initByImportBeanDefinitionRegistrar");
		
		registry.registerBeanDefinition(ByImportBean.class.getName(), definition.getBeanDefinition());
		
		System.out.println("1.ByImportBeanDefinitionRegistrar 动态注册完成，bean -> " + ByImportBean.class.getName());
	}

}
