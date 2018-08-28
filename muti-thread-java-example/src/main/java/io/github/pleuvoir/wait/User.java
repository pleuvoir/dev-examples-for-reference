package io.github.pleuvoir.wait;

import io.github.pleuvoir.kit.SleepUtil;

public class User {

	private String name;
	
	private Integer age;

	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public synchronized void changeName() {
		this.name = "pleuvoir";
		notifyAll();
	}
	
	public synchronized void changeAge() {
		this.age = 26;
		notifyAll();
	}
	
	
	public synchronized  void waitChangeName() {
		while (!"pleuvoir".equals(this.name)) {
			try {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName + " waitChangeName before wait");
				wait();
				System.out.println(threadName + " waitChangeName after wait");
				SleepUtil.seconds(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		System.out.println("waitChangeName notified ... over ");
	}
	
	public synchronized void waitChangeAge() {
		while (26 != this.age) {
			try {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName + " waitChangeAge before wait");
				wait();
				System.out.println(threadName + " waitChangeAge after wait");
				SleepUtil.seconds(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		System.out.println("waitChangeAge notified ... over ");
	}
	
	
	
	public static class waitChangeNameThread implements Runnable{

		private User user;
		
		public waitChangeNameThread(User user) {
			super();
			this.user = user;
		}

		@Override
		public void run() {
			user.waitChangeName();
		}
	}
	
	
	public static class waitChangeAgeThread implements Runnable{

		private User user;
		
		public waitChangeAgeThread(User user) {
			super();
			this.user = user;
		}

		@Override
		public void run() {
			user.waitChangeAge();
		}
	}
	
	
	
	public static void main(String[] args) {
		
		User user = new User("fw", 20);
		new Thread(new waitChangeNameThread(user)).start();
		new Thread(new waitChangeAgeThread(user)).start();
		
		SleepUtil.seconds(1);
		user.changeName();
		
		user.changeAge();
		
		
	}
	
	
	
}
