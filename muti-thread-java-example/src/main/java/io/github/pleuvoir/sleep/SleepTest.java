package io.github.pleuvoir.sleep;

import java.util.concurrent.locks.ReentrantLock;

import io.github.pleuvoir.kit.SleepUtil;

/**
 * 演示 sleep 不会释放锁
 * @author pleuvoir
 *
 */
public class SleepTest {
	
	static Object o = new Object();
	
	static ReentrantLock lock = new ReentrantLock();
	
	public static class ExcuteThread extends Thread {
		@Override
		public void run() { 
//			synchronized (o) {
//				SleepUtil.seconds(1);
//				System.out.println(Thread.currentThread().getName() + " unpark ...");
//			}
//			
			lock.lock();
			try {
				SleepUtil.seconds(1);
				System.out.println(Thread.currentThread().getName() + " unpark ...");
			} finally {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new ExcuteThread().start();
		}
	//	SleepUtil.seconds(3);
	}
	
}
