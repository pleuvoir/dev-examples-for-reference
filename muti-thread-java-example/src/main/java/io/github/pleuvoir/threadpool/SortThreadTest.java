package io.github.pleuvoir.threadpool;

import java.util.concurrent.locks.ReentrantLock;

import io.github.pleuvoir.kit.SleepUtil;

/**
 * 使用公平锁实现 先到的先执行，顺序保证执行任务  <br>
 * 
 * 可用  {@link SingleThreadExecutorTest} 替换实现
 * @author pleuvoir
 *
 */
public class SortThreadTest {

	static GithubCommitService githubCommitService = new GithubCommitService();

	public static void main(String[] args) {
		for (int i = 1; i <= 110; i++) {
			new GithubCommitAsyncThread(githubCommitService, "我是" + i);
			SleepUtil.ms(1); 	// 休眠一下，让出cpu给上面创建的线程执行
		}
	}

	private static class GithubCommitAsyncThread extends Thread {
		private static final ReentrantLock fairlock = new ReentrantLock(true);	// 设置为公平锁，先到的先执行
		private GithubCommitService githubCommitService;
		private String content;

		public GithubCommitAsyncThread(GithubCommitService githubCommitService, String content) {
			this.githubCommitService = githubCommitService;
			this.content = content;
			start();
		}

		@Override
		public void run() {
			try {
				fairlock.lock();
				SleepUtil.ms(100);
				githubCommitService.commit(content);
			} finally {
				fairlock.unlock();
			}
		}
	}

	public static class GithubCommitService {
		void commit(String content) {
			System.out.println(content);
		};
	}

}
