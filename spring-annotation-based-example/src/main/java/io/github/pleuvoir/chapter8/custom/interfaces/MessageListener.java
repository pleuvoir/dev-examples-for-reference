package io.github.pleuvoir.chapter8.custom.interfaces;

import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;

import io.github.pleuvoir.base.Message;

public class MessageListener implements ApplicationListener<PayloadApplicationEvent<Message>> {

	@Override
	public void onApplicationEvent(PayloadApplicationEvent<Message> event) {
		System.out.println("接收到事件：" + event.getPayload().getMessage());
	}

}
