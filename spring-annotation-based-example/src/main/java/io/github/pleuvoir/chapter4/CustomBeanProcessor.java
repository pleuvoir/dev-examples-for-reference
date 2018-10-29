package io.github.pleuvoir.chapter4;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import io.github.pleuvoir.chapter4.service.InitService3;

@Component
public class CustomBeanProcessor implements BeanPostProcessor {

	/**
	 * 该方法会在 init-method 之后调用 <br>
	 * 可以在此处对某些 bean 做增强处理
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("CustomBeanProcessor postProcessAfterInitialization invoke beanName：["+ beanName+ "]");
		if (bean instanceof InitService3) {
			InitService3 service = (InitService3) bean;
			service.unpark();
		}
		return bean;
	}

	/**
	 * 该方法会在 init-method 之前调用
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("CustomBeanProcessor postProcessBeforeInitialization invoke beanName：["+ beanName+ "]");
		return bean;
	}

}
