package io.github.pleuvoir.condition;

import io.github.pleuvoir.kit.SleepUtil;

public class Client {

	public static void main(String[] args) {
		User user = new User(0, "pleuvoir");
		
		// 检查年龄的线程，不满足一直等待
		for (int i = 0; i < 2; i++) {
			Thread thread = new Thread(() -> {
				user.waitChangeAge();
			});
			thread.start();
		}
		
		// 检查姓名的线程，不满足一直等待
		for (int i = 0; i < 2; i++) {
			Thread thread = new Thread(() -> {
				user.waitChangeName();
			});
			thread.start();
		}
		
		SleepUtil.seconds(1);  // 保证监听线程先启动
		user.changeAge();
		user.changeName();
	}
}
