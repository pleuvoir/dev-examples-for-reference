package io.github.pleuvoir.chapter8;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.util.ErrorHandler;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

@Configuration
@ComponentScan("io.github.pleuvoir.chapter8")
public class Config8 {

	@Bean(name = AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
	public ApplicationEventMulticaster initApplicationEventMulticaster(BeanFactory beanFactory) {
		
		SimpleApplicationEventMulticaster applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
		
		// http://calvin1978.blogcn.com/articles/java-threadpool.html 线程池说明
		ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("事件处理线程-%d").build();
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
				200, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), threadFactory);
		
		//threadPoolExecutor.prestartAllCoreThreads();
		applicationEventMulticaster.setTaskExecutor(threadPoolExecutor);
		
		applicationEventMulticaster.setErrorHandler(new ErrorHandler() {
			
			@Override
			public void handleError(Throwable t) {
//				System.out.println("出错了");
//				t.printStackTrace();
			}
		});
		return applicationEventMulticaster;
	}
	
}
