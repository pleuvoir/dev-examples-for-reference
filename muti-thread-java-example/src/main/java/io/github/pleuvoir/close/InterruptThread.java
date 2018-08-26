package io.github.pleuvoir.close;

import java.util.concurrent.TimeUnit;

import io.github.pleuvoir.kit.SleepUtil;

public class InterruptThread {

	public static void main(String[] args) {
		
		Thread interruptThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					try {
						System.out.println("我不是中断状态，我在疯狂输出。。。");
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
						// 这种情况会把 标志为重新改为 false ，线程又被激活 所以需要再次中断
						Thread.currentThread().interrupt();
						
						// 是否中断状态，注意：此方法会重新将标记为 置为 false
						System.out.println(Thread.interrupted());
					}
				}
				System.out.println("run over...");
			}
		});

		interruptThread.start();
		SleepUtil.ms(1);  // 如果这里不休息一下，则直接run over...
		interruptThread.interrupt();	// 将线程标志位改为 true ，如果遇到了(sleep 等)阻塞方法会抛出 InterruptedException
		
	}
}
