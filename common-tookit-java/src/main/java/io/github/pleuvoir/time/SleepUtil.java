package io.github.pleuvoir.time;

import java.util.concurrent.TimeUnit;

public class SleepUtil {

	/**
	 * 程序运行的太快，决定休息几秒
	 * @param seconds
	 */
	public static void sleep(Integer seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds.longValue());
		} catch (InterruptedException e) {
			// 恢复中断状态
			Thread.currentThread().interrupt();
			// https://www.ibm.com/developerworks/cn/java/j-jtp05236.html
		}
	}
	
	/**
	 * 程序运行的太快，决定休息几秒
	 * @param seconds
	 */
	public static void sleep(Long seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
