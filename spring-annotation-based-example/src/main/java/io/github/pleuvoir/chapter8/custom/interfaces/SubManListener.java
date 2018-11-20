package io.github.pleuvoir.chapter8.custom.interfaces;

import org.springframework.context.PayloadApplicationEvent;

import io.github.pleuvoir.base.Man;

public class SubManListener extends ManListener {

	@Override
	public void onApplicationEvent(PayloadApplicationEvent<Man> event) {
		System.out.println(Thread.currentThread().getName() + " interface SubMan 接收到事件：" + event.getPayload().getName());
	}

}
