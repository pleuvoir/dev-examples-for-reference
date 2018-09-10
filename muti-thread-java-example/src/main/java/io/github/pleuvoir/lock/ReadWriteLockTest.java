package io.github.pleuvoir.lock;

import io.github.pleuvoir.kit.ThreadUtil;

/**
 * 演示读写锁
 * @author pleuvoir
 *
 */
public class ReadWriteLockTest {


	public static void main(String[] args) {

		User user = new User(18);

		UserService service = new UserServiceReadWriteLock(user);		  // new UserServiceReadWriteLock(user) 

		for (int i = 0; i < 50; i++) { // 同步读 50 次
			new Thread(() -> {
				long start = System.currentTimeMillis();
				service.get();
				System.out.println(ThreadUtil.print() + "读取耗时：" + (System.currentTimeMillis() - start) + "ms");
			}).start();
		}

		new Thread(() -> { // 同步写 1 次
			long start = System.currentTimeMillis();
			service.update();
			System.out.println(ThreadUtil.print() + " ---- 写耗时：" + (System.currentTimeMillis() - start) + "ms");
		}).start();
	}

}
