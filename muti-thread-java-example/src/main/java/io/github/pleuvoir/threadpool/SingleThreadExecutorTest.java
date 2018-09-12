package io.github.pleuvoir.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.pleuvoir.kit.SleepUtil;
import io.github.pleuvoir.kit.ThreadUtil;

/**
 * 创建单个线程，需要顺序保证执行任务，不会有多个线程活动，使用了无界队列
 * @author pleuvoir
 *
 */
public class SingleThreadExecutorTest {
	
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService scheduledExecutor = Executors.newSingleThreadExecutor();
		for (int i = 1; i <= 5; i++) {
			Commiter commiter = new Commiter(i);
			scheduledExecutor.execute(commiter);
		}
		System.out.println("shutdown before");		
		scheduledExecutor.shutdown();
		System.out.println("shutdown over");
	}

	private static class Commiter implements Runnable {

		private int count;

		public Commiter(int count) {
			this.count = count;
		}

		@Override
		public void run() {
			System.out.println("当前是第" + this.count + "个创建的线程 |" + 
					ThreadUtil.print() + "进入时间。。" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			SleepUtil.seconds(2);
			System.out.println(
					ThreadUtil.print() + "开始提交。。" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			SleepUtil.seconds(1);
			System.out.println(
					ThreadUtil.print() + "提交完成。。" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		}
	}

}
