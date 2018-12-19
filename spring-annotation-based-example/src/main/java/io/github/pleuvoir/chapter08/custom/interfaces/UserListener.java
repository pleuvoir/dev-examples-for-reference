package io.github.pleuvoir.chapter08.custom.interfaces;

import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;

import io.github.pleuvoir.base.User;

public class UserListener implements ApplicationListener<PayloadApplicationEvent<User>> {

	@Override
	public void onApplicationEvent(PayloadApplicationEvent<User> event) {
		System.out.println(Thread.currentThread().getName() + " interface User 接收到事件：" + event.getPayload().getName());
	}

}
