package io.github.pleuvoir.synchronize;

import io.github.pleuvoir.kit.SleepUtil;

public class SynchronizeTest {

	
	public static class ClassLockThread implements Runnable{
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " 类锁进来了。");
			classLock();
		}
	}
	
	
	public static class ObjectLockThread implements Runnable{
		
		SynchronizeTest obj;
		
		public ObjectLockThread(SynchronizeTest obj) {
			this.obj = obj;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " 对象锁进来了。");
			obj.objectLock();
		}
	}
	
	
	/**
	 * 类锁
	 */
	public static synchronized void classLock(){
		SleepUtil.seconds(2);
		System.out.println(Thread.currentThread().getName() + " classLock  go ... ");
		SleepUtil.seconds(2);
		System.out.println(Thread.currentThread().getName() + " classLock end ... ");
	}
	
	/**
	 * 对象锁
	 */
	public synchronized void objectLock(){
		SleepUtil.seconds(2);
		System.out.println(Thread.currentThread().getName() + " objectLock go ... ");
		SleepUtil.seconds(2);
		System.out.println(Thread.currentThread().getName() + " objectLock end ... ");
	}
	
	
	
	public static void main(String[] args) {
		
		// 类锁 ，锁的是每个类的的Class对象，每个类的的Class对象在一个虚拟机中只有一个，所以类锁也只有一个。 调用的 synchronized 静态方法
		ClassLockThread classLockThread1 = new ClassLockThread();
		ClassLockThread classLockThread2 = new ClassLockThread();
		
		new Thread(classLockThread1).start();
		new Thread(classLockThread2).start();
		
		
		// 对象锁，锁的是类的对象实例。必须锁同一个对象 线程才会一个一个执行 调用的 synchronized 非静态方法
		
		SynchronizeTest synchronizeTest1 = new SynchronizeTest();
		SynchronizeTest synchronizeTest2 = new SynchronizeTest();
		ObjectLockThread objectLockThread1 = new ObjectLockThread(synchronizeTest1);  
		ObjectLockThread objectLockThread2 = new ObjectLockThread(synchronizeTest1);
		
		new Thread(objectLockThread1).start();
		new Thread(objectLockThread2).start();
	}
	
	
}
