package io.github.pleuvoir.chapter8.custom.interfaces;

import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;

import io.github.pleuvoir.base.Man;

public class ManListener implements ApplicationListener<PayloadApplicationEvent<Man>> {

	@Override
	public void onApplicationEvent(PayloadApplicationEvent<Man> event) {
		System.out.println(Thread.currentThread().getName() + " interface Man 接收到事件：" + event.getPayload().getName());
		
		System.out.println(1/0);
	}

}
