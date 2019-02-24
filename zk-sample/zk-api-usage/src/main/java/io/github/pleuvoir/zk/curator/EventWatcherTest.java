package io.github.pleuvoir.zk.curator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.zookeeper.CreateMode;


/**
 * 和原生API类似的监听没有在此举例，因为不能重复只能生效一次 <br>
 * Cache 的三种实现：
 * <ol>
 * 		<li>Path Cache：监视一个路径下1）孩子结点的创建、2）删除，3）以及结点数据的更新。产生的事件会传递给注册的PathChildrenCacheListener。</li>
 * 		<li>Node Cache：监视一个结点的创建、更新、删除，并将结点的数据缓存在本地。</li>
 * 		<li>Tree Cache：Path Cache和Node Cache的“合体”，监视路径下的创建、更新、删除事件，并缓存路径下所有孩子结点的数据。</li>
 * </ol>
 * @author pleuvoir
 *
 */
@SuppressWarnings("unused")
public class EventWatcherTest {

	static CuratorFramework client = CuratorHelper.getClient();
	
	
	public static void main(String[] args) throws Exception {
		
		// 监听父节点同时可监听子节点
		//pathChildrenCacheTest();
		
		// 监听某个节点
		//nodeCacheTest();
		
		// 监听父节点以及父节点下的所有节点
		TreeCacheTest();
	}
	
	
	/*
	 * 能监听所有的子节点 且是无限监听的模式 但是指定目录下孙子节点不再监听
	 */
	
	private static void pathChildrenCacheTest() throws Exception {

		PathChildrenCacheListener listener = new PathChildrenCacheListener() {

			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				ChildData childData = event.getData();
				String dataString = new String(childData.getData());
				switch (event.getType()) {
				case CHILD_ADDED:
					System.out.println("CHILD_ADDED : " + childData.getPath() + "  数据:" + dataString);
					break;
				case CHILD_REMOVED:
					System.out.println("CHILD_REMOVED : " + childData.getPath() + "  数据:" + dataString);
					break;
				case CHILD_UPDATED:
					System.out.println("CHILD_UPDATED : " + childData.getPath() + "  数据:" + dataString);
					break;
				case INITIALIZED:
					System.out.println("INITIALIZED : " + childData.getPath() + "  数据:" + dataString);
					break;
				default:
					break;
				}
			}
		};

		PathChildrenCache childrenCache = new PathChildrenCache(client, "/test", true);
		childrenCache.getListenable().addListener(listener);
		System.out.println("Register zk watcher successfully!");
		childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

		// 创建一个节点
		client.create().orSetData().withMode(CreateMode.PERSISTENT).forPath("/test", "test".getBytes());
		client.create().orSetData().withMode(CreateMode.PERSISTENT).forPath("/test/node01", "node01".getBytes());
		Thread.sleep(1000);
		client.create().orSetData().withMode(CreateMode.EPHEMERAL).forPath("/test/node02", "node02".getBytes());
		Thread.sleep(1000);
		client.create().orSetData().withMode(CreateMode.EPHEMERAL).forPath("/test/node02", "node02修改".getBytes());
		Thread.sleep(1000);
		client.delete().forPath("/test/node02");
		
		
		// 孙子节点不会触发监听
		client.create().orSetData().withMode(CreateMode.EPHEMERAL).forPath("/test/node01/node01child", "node01child".getBytes());
		
		Thread.sleep(10000);
		childrenCache.close();
	}
	
	// 只监听某一个节点的变化
	private static void nodeCacheTest() throws Exception{
        //设置节点的cache
        final NodeCache nodeCache = new NodeCache(client, "/test", false);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
				System.out.println("the test node is change and result is :");
				System.out.println("path : " + nodeCache.getCurrentData().getPath());
				System.out.println("data : " + new String(nodeCache.getCurrentData().getData()));
				System.out.println("stat : " + nodeCache.getCurrentData().getStat());
            }
        });
        nodeCache.start();

        client.create().orSetData().withMode(CreateMode.PERSISTENT).forPath("/test","test".getBytes());
        Thread.sleep(1000);
        client.create().orSetData().withMode(CreateMode.PERSISTENT).forPath("/test","test1".getBytes());
        Thread.sleep(10000);
        nodeCache.close();
	}
	
	// 无限监听 包括所有子节点
	private static void TreeCacheTest() throws Exception {
		// 设置节点的cache
		TreeCache treeCache = new TreeCache(client, "/test");
		// 设置监听器和处理过程
		treeCache.getListenable().addListener(new TreeCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
				ChildData data = event.getData();
				if (data != null) {
					switch (event.getType()) {
					case NODE_ADDED:
						System.out.println("NODE_ADDED : " + data.getPath() + "  数据:" + new String(data.getData()));
						break;
					case NODE_REMOVED:
						System.out.println("NODE_REMOVED : " + data.getPath() + "  数据:" + new String(data.getData()));
						break;
					case NODE_UPDATED:
						System.out.println("NODE_UPDATED : " + data.getPath() + "  数据:" + new String(data.getData()));
						break;
					default:
						break;
					}
				} else {
					System.out.println("data is null : " + event.getType());
				}
			}
		});
		// 开始监听
		treeCache.start();

		// 创建一个节点
		client.create().orSetData().withMode(CreateMode.PERSISTENT).forPath("/test", "test".getBytes());
		Thread.sleep(1000);
		
		client.create().orSetData().withMode(CreateMode.EPHEMERAL).forPath("/test/node01", "node01".getBytes());
		Thread.sleep(1000);
		
		client.create().orSetData().withMode(CreateMode.EPHEMERAL).forPath("/test/node01", "node011".getBytes());
		Thread.sleep(1000);
		
		client.create().orSetData().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
				.forPath("/test/node02/node02_2", "node02_2".getBytes());

		Thread.sleep(10000);
		treeCache.close();
	}
}
