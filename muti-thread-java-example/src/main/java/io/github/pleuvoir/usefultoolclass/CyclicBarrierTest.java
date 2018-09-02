package io.github.pleuvoir.usefultoolclass;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import io.github.pleuvoir.dbpool.helper.SleepTools;

/**
 * 
 * 让一组线程到达某个屏障，然后一起开放，可以用来测试并行？
 * @author pleuvoir
 *
 */
public class CyclicBarrierTest {
	
	static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

	public static void main(String[] args) {
		
		for(int i = 0;i<5;i++){
			new ExcuteThread().start();
		}
	}
	
	
	static class ExcuteThread extends Thread{

		@Override
		public void run() {
			try {
				if(new Random().nextBoolean()){
					SleepTools.second(3);
					System.out.println(this.getName() + " 运气不好，休息 2 秒");
				}
				
				System.out.println(this.getName() + " 到达屏障前" );
				cyclicBarrier.await();
				
				System.out.println(this.getName() + "准备休息 3 秒，然后一起出发");
				SleepTools.second(3);
				System.out.println(this.getName() + " 到达位置");
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
