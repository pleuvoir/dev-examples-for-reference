package io.github.pleuvoir.wrapper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * {@link org.springframework.context.ApplicationContext} 包装类，方便获取容器中的Bean
 * <p>注意：需要在容器中注册后方可使用 
 * @author pleuvoir
 */
@Component
public class ApplicationContextWrap implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		ApplicationContextWrap.applicationContext = applicationContext;
	}

	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		return (T) applicationContext.getBean(beanName);
	}

}
