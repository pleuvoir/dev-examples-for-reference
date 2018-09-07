package io.github.pleuvoir.volatileunsafe;

/**
 * 不是线程安全的，尽量不要使用此做同步
 * @author pleuvoir
 *
 */
public class VolatileUnsafe {

	private volatile static Integer val = 1; // 任何线程操作 volatile 修饰的变量会及时的刷新到主内存，保证了可见性，但保证不了原子操作
												// 适合于只有一个线程写，多个线程读的场景，因为它只能确保可见性。

	static ThreadLocal<Integer> threadVal = new ThreadLocal<Integer>() {

		@Override
		protected Integer initialValue() {
			return 1;
		}
	};

	public static void main(String[] args) {

		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					String name = Thread.currentThread().getName();
					System.out.println(name + " val：" + val++);
					Integer integer = threadVal.get();
					threadVal.set(integer +1);
					System.out.println(name + " integer：" + threadVal.get());
				}
			}).start();
		}
	}

}
