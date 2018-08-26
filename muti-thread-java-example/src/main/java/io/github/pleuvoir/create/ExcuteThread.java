package io.github.pleuvoir.create;

public class ExcuteThread extends Thread {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}

	public static void main(String[] args) {

		/**
		 * 直接 start 和 run 的区别
		 * start 会告诉虚拟机这是一个线程，然后虚拟机会告知操作系统分配资源
		 * run	  则是作为对象普通方法的调用，并不会成为一个线程
		 * run 方法就是普通对象的普通方法，只有调用了start()后，Java才会将线程对象和操作系统中实际的线程进行映射，再来执行run方法。
		 */
		
		ExcuteThread excuteThread = new ExcuteThread();
		excuteThread.setName("excuteThread-x");
		excuteThread.start();	// excuteThread-x
		excuteThread.run();		// main
	}
}
