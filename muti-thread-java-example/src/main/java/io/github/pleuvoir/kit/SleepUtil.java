package io.github.pleuvoir.kit;

import java.util.concurrent.TimeUnit;

public class SleepUtil {

	/**
	 * 休眠若干秒
	 * @param seconds	秒数
	 */
	public static void seconds(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 休眠若干毫秒
	 * @param ms	毫秒数
	 */
	public static void ms(long ms) {
		try {
			TimeUnit.MILLISECONDS.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
