package io.github.pleuvoir.lock;

import java.util.concurrent.locks.ReentrantLock;

import io.github.pleuvoir.kit.ThreadUtil;

/**
 * 演示可重入锁（比如：递归调用，每次锁+1，然后挨着-1，没有实现可重入会死锁）
 * @author pleuvoir
 *
 */
public class ReentrantLockTest {

	private int count;
	
	ReentrantLock lock = new ReentrantLock(true);	// 公平锁
	
	public void increment(){
		System.out.println(ThreadUtil.print() + "开始冲刺。。");
		lock.lock();				// 谁先执行到锁前面，谁先拿到锁
		try {							//如果在时间上，先对锁进行获取的请求，一定先被满足，这个锁就是公平的，不满足，就是非公平的
			count++;
			System.out.println(ThreadUtil.print() + "count：" + count);
		} finally {
			lock.unlock();
		}
	}
	
	
	public static void main(String[] args) {
		ReentrantLockTest object = new ReentrantLockTest();
		for (int i = 0; i < 50; i++) {
			new Thread(() -> {
				object.increment();
			}).start();
		}
	}
	
}
