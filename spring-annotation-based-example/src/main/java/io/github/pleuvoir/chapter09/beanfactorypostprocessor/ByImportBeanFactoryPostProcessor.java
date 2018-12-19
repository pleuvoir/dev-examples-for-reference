package io.github.pleuvoir.chapter09.beanfactorypostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import io.github.pleuvoir.base.Tiger;
import io.github.pleuvoir.chapter09.registrar.ByImportBean;

/**
 * 演示 spring 动态注册 bean
 * 
 * <ol>	优点：
 * 		<li>如果同时使用了 ImportBeanDefinitionRegistrar 和  BeanFactoryPostProcessor、BeanDefinitionRegistryPostProcessor 三种方式，那么本类获胜，将会覆盖其他两种注册的信息</li>
 * </ol>
 * 
 * <ol>	使用方式：
 * 		<li>项目配置类中 <code>@Import(ByImportBeanFactoryPostProcessor.class)</code> 只要将处理器加入容器即可</li>
 * </ol>
 * @author pleuvoir
 *
 */
public class ByImportBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		// 将接口转为具体的实现类（目前有两个实现类，其中一个是 DefaultListableBeanFactory ，另一个是 DefaultListableBeanFactory 的子类，所以直接转没问题）
		// 这个实现类 又实现了 BeanDefinitionRegistry 接口，使得我们可以注册 beanDefinition

		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
		
		//BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(ByImportBean.class);

		// 使用 supply 接口，更为灵活
		BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(ByImportBean.class, () -> {
			
			ByImportBean byImportBean = new ByImportBean();
			// 可以拿到其他已经注册到容器中的 bean
			Tiger tiger = beanFactory.getBean(Tiger.class);
			byImportBean.setTiger(tiger);
			return byImportBean;
		});

		// 这里修改 初始化方法，会发现走的是我们这里设置的，而不是 ImportBeanDefinitionRegistrar 注册时设置的
		definition.setInitMethodName("initBeanFactoryPostProcessor");

		defaultListableBeanFactory.registerBeanDefinition(ByImportBean.class.getName(), definition.getBeanDefinition());

		System.out.println("2.ByImportBeanFactoryPostProcessor 动态注册完成，bean -> " + ByImportBean.class.getName());
	}

}
