package io.github.pleuvoir.chapter4.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitService3 implements InitializingBean, DisposableBean {

	public InitService3() {
		System.out.println("InitService3 构造方法被调用");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("执行 InitService3 destroy()");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("执行 InitService3 afterPropertiesSet()，此方法类似于 init");
	}

	public void unpark(){
		System.out.println("开车。。。");
	}

}
