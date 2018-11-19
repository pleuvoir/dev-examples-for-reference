package io.github.pleuvoir.chapter8.custom.annotations;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	/**
	 * 不要使用相同的参数类型，否则会一起执行，除非你想这么做
	 */
	
	@EventListener
	public void sendToAddress(String address) {
		System.out.println("sendToAddress 发送邮件 -> " + address);
	}
	
	@EventListener
	public void sendToAnotherAddress(String anotherAddress) {
		System.out.println("sendToAnotherAddress 发送邮件 -> " + anotherAddress);
	}
}
