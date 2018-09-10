package io.github.pleuvoir.sleep;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import io.github.pleuvoir.kit.SleepUtil;

/**
 * 有序的执行线程
 * @author pleuvoir
 *
 */
public class SortExcuteThreadTest {

	
	static List<Integer> data = Collections.synchronizedList(new LinkedList<Integer>());
	
	private static class GithubCommitAsyncThread extends Thread {
		private Integer content;

		public GithubCommitAsyncThread(Integer content) {
			this.content = content;
			start();
		}

		@Override
		public void run() { 	
			//SleepUtil.ms(10);		//自从加了你这段代码以后 顺序就不能保证了
				System.out.println(Thread.currentThread().getName() + "  === 内容：" + content);
				data.add(content);
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 2000; i++) {
			new GithubCommitAsyncThread(i);
			SleepUtil.ms(1);
		}
		
		SleepUtil.seconds(6);
		for (int i = 0; i <= data.size(); i++) {
			if ((i + 1) < data.size() && data.get(i + 1) < data.get(i)) {
				System.out.println("后一个：" + data.get(i + 1) + " 上一个：" + data.get(i));
			}
		}
		
		System.out.println("over///");
	}
	
}
