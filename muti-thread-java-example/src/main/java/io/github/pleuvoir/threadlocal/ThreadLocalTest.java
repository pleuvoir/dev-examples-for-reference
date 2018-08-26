package io.github.pleuvoir.threadlocal;

public class ThreadLocalTest {

	/**
	 * ThreadLocal作为线程的拷贝 尽量不要存储大对象 threadLocal.remove()可以清除，但是一般不调用
	 * 此处可以理解为ConcurrentHashMap<Thread,Integr> 	每个线程都会获取到自己的值
	 */
	 static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {

		@Override
		protected Integer initialValue() {
			return 1;
		}

	};
	
	static Integer intVal = 1;

	private static class excuteThread extends Thread {

		private Integer i;

		public excuteThread(Integer i) {
			super();
			this.i = i;
		}

		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			Integer threadLocalVal = threadLocal.get();
			System.out.println(name + " running，threadLocalVal：" + threadLocalVal);
			System.out.println(name + " running，intVal：" + intVal);
			// 重新赋值
			threadLocalVal = threadLocalVal + i;
			intVal = intVal + i;
			threadLocal.set(threadLocalVal);
			System.out.println(name + " new threadLocalVal：" + threadLocal.get());
			System.out.println(name + " new intVal：" + intVal);
		}
	}

	public static void main(String[] args) {

		for (int i = 0; i < 3; i++) {
			new excuteThread(i).start();
		}
	}

}
