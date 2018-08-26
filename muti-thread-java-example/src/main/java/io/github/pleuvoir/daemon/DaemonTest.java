package io.github.pleuvoir.daemon;

import java.util.concurrent.TimeUnit;

import io.github.pleuvoir.kit.SleepUtil;


public class DaemonTest {

	public static void main(String[] args) {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println("停不下来");
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						System.out.println("我是 finally ...");
					}
				}
			}
		});

		// 和主线程共死，finally不能保证一定执行
		thread.setDaemon(true);
		thread.start();

		SleepUtil.seconds(1);
	}
}
