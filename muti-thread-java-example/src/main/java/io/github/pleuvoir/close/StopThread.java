package io.github.pleuvoir.close;

import io.github.pleuvoir.kit.SleepUtil;

public class StopThread {

	public static void main(String[] args) throws InterruptedException {
		Thread stopThread = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("running...");

			}
		});
		
		stopThread.start();
		SleepUtil.seconds(1);
		stopThread.stop();  // 该方法类似于直接结束任务管理进程，会导致线程可能未完成资源的释放
	}
}
