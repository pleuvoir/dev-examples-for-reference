package io.github.pleuvoir.chapter8.custom.annotations;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import io.github.pleuvoir.base.Man;
import io.github.pleuvoir.base.User;

@Service
public class EmailService {

	/**
	 * 不要使用相同的参数类型，否则会一起执行，除非你想这么做
	 */

	@EventListener
	public void doHandle(String address) {
		System.out.println(Thread.currentThread().getName() + " 发送邮件 -> " + address);
	}

	@EventListener
	public void doHandle2(String anotherAddress) {
		System.out.println(Thread.currentThread().getName() + " 发送邮件 -> " + anotherAddress);
	}

	@EventListener
	public void doHandle(User user) {
		System.out.println(Thread.currentThread().getName() +  " User 发送邮件 ..." );
	}

	@EventListener
	public void doHandle(Man man) {
		System.out.println(Thread.currentThread().getName() +  " Man 发送邮件 ..." );
	}
}

