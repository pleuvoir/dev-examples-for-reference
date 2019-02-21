package io.github.pleuvoir.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorHelper {

	private static class LoaderHelper {

		// ZooKeeper服务地址
		private static final String SERVER = "39.105.110.40:2181";

		// 会话超时时间
		private final static int SESSION_TIMEOUT = 30000;

		// 连接超时时间
		private final static int CONNECTION_TIMEOUT = 5000;

		static RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3); // 重试策略

		// 创建连接实例
		private static CuratorFramework CLIENT = null;

		static {
			System.out.println("初始化  curator -> zk 连接 ...");
			init();
			System.out.println("初始化  curator -> zk 已连接 ...");
		}

		static void init() {
			// 创建 CuratorFrameworkImpl实例
			CLIENT = CuratorFrameworkFactory.newClient(SERVER, SESSION_TIMEOUT, CONNECTION_TIMEOUT, retryPolicy);
			// 启动
			CLIENT.start();
		}
	}

	public static CuratorFramework getClient() {
		return LoaderHelper.CLIENT;
	}

}
