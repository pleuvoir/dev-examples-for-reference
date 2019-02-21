package io.github.pleuvoir.zk.nativeapi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZkHelper {

	public static final ZkHelper INSTANCE = new ZkHelper();

	ZooKeeper zooKeeper = null;

	public static final String SERVER_HOST = "39.105.110.40:2181";

	// 会话超时时间
	private static final int SESSION_TIMEOUT = 30_000;
	
	public ZkHelper() {
		try {
			initConnection();
			System.out.println("初始化连接。。");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public ZooKeeper initConnection() throws IOException, InterruptedException {

		CountDownLatch latch = new CountDownLatch(1);

		zooKeeper = new ZooKeeper(SERVER_HOST, SESSION_TIMEOUT, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				System.out.println("【接收到" + event.getType() + "事件】：" + event);
				if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
					latch.countDown();
				}
			}
		});

		latch.await();
		System.out.println(zooKeeper.getState());

		return zooKeeper;
	}

	public void create(String path, String data, CreateMode createMode) {
		try {
			// 为了有事件
			this.zooKeeper.exists(path, true);
			this.zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
			System.err.println("创建节点失败！");
		}
	}

	public void update(String path, String data) {
		// 为了有事件
		try {
			this.zooKeeper.exists(path, true);
			this.zooKeeper.setData(path, data.getBytes(), -1);
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
			System.err.println("修改节点失败！");
		}
	}

	public void remove(String path) {
		// 为了有事件
		try {
			Stat exists = this.zooKeeper.exists(path, true);
			if (exists != null) {
				this.zooKeeper.delete(path, -1);
			}else{
				System.out.println("节点不存在忽略删除：" + path);
			}
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
			System.err.println("删除节点失败！");
		}
	}

	public String get(String path) {
		// 为了有事件
		try {
			this.zooKeeper.exists(path, true);
			byte[] data = this.zooKeeper.getData(path, true, null);
			return new String(data, "UTF-8");
		} catch (KeeperException | InterruptedException | UnsupportedEncodingException e) {
			e.printStackTrace();
			System.err.println("获取节点失败！");
			throw new RuntimeException(e);
		}
	}
	
	public boolean exist(String path) {
		try {
			Stat stat = this.zooKeeper.exists(path, false);
			return stat != null;
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}
}
