package io.github.pleuvoir.condition;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.github.pleuvoir.kit.ThreadUtil;

public class User {

	private int age;

	private String name;

	public User(int age, String name) {
		this.age = age;
		this.name = name;
	}

	private Lock lock = new ReentrantLock();
	private Condition ageCond = lock.newCondition();
	private Condition nameCond = lock.newCondition();
	
	
	public void changeName(){
		lock.lock();
		try {
			this.name = "bill";
			if (new Random().nextBoolean()) {
				nameCond.signalAll(); // 通知所有持有该 Condition 的线程
				System.out.println("通知所有持有 Name Condition 的线程");
			} else {
				nameCond.signal(); // 通知另一个持有该 Condition 的线程
				System.out.println("通知一个持有 Name Condition 的线程");
			}
		} finally {
			lock.unlock();
		}
	}
	
	public void changeAge(){
		lock.lock();
		try {
			this.age = 19;
			if(new Random().nextBoolean()){
				ageCond.signalAll();	// 通知所有持有该 Condition 的线程
				System.out.println("通知所有持有 Age Condition 的线程");
			}else{
				ageCond.signal();	// 通知另一个持有该 Condition 的线程
				System.out.println("通知一个持有 Age Condition 的线程");
			}
		} finally {
			lock.unlock();
		}
	}
	
	public void waitChangeAge() {
		System.out.println(ThreadUtil.print() + "进入【waitChangeAge】当前年龄：" + age);
		lock.lock();
		try {
			while (age < 18) {
				try {
					System.out.println(ThreadUtil.print() + "当前年龄：" + age);
					ageCond.await();
					System.out.println("waitChangeAge thread[" + Thread.currentThread().getId() + "] 被唤醒了。。。" + " 当前年龄：" + age);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} finally {
			lock.unlock();
		}
		System.out.println("到此为止。。");
	}
	
	
	public void waitChangeName() {
		System.out.println(ThreadUtil.print() + "进入【waitChangeName】当前姓名：" + name);
		lock.lock();
		try {
			while (name.equals("pleuvoir")) {
				try {
					System.out.println(ThreadUtil.print() + "当前姓名：" + name);
					nameCond.await();
					System.out.println("waitChangeName thread[" + Thread.currentThread().getId() + "] 被唤醒了。。。" + "当前姓名：" + name);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} finally {
			lock.unlock();
		}
		System.out.println("到此为止。。");
	}

}
