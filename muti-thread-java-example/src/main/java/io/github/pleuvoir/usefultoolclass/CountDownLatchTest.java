package io.github.pleuvoir.usefultoolclass;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 5个初始化线程， 6个扣除点
 * 
 * 初始化完成，业务线程和主线程一起执行		
 * 
 * <p> 作用：一个线程等待其他线程完成以后再工作，可以用于控制线程执行顺序
 * @author pleuvoir
 *
 */
public class CountDownLatchTest {

	static CountDownLatch countDownLatch = new CountDownLatch(6);
	
	public static void main(String[] args) throws InterruptedException {
	
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				countDownLatch.countDown();		// 扣 1 次
			}
		}).start();
		
		new BusThread().start();	// 启动业务线程开始等待扣除完成
		
		for (int i = 0; i <= 4; i++) {
			new InitThread().start();	// 扣 5 次
		}
		
		countDownLatch.await();
		
		System.out.println("主线程执行了。。。");
	}
	
	
	// 必须等我先执行完以后
	static class InitThread extends Thread{
		
		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(3);
				System.out.println("初始化线程开始执行扣减。。。");
				countDownLatch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 必须等上面的先执行
	static class BusThread extends Thread{
		@Override
		public void run() {
			try {
				System.out.println(this.getName() + "	业务线程	await before ...");
				countDownLatch.await();
				System.out.println(this.getName() + "	业务线程	await over...  执行业务代码");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
