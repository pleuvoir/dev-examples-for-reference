package io.github.pleuvoir.zk.curator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class TestCurator {

	public static final String PARENT_NODE = "/leaderByCurator";

	public static final String CHILD_NODE = "/leaderByCurator/child";

	public static final String PARENT_SEQ_NODE = "/leaderSeqByCurator";

	public static final String SUPER_CHILD_NODE = "/1/2/3/4/5/6/7";

	public static final String ASYNC_LISTENER_NODE = "/999";

	public static final String ASYNC_CALLBACK_NODE = "/107";

	public static void main(String[] args) throws Exception {

		CuratorFramework client = CuratorHelper.getClient();

		// 创建监听器（异步回调使用）
		client.getCuratorListenable().addListener(new CuratorListener() {
			@Override
			public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
				System.out.println("接收到事件：" + event);
			}
		});

		// 删除节点，如果有必要也删除子节点
		client.delete().deletingChildrenIfNeeded().forPath(PARENT_NODE);
		// 创建永久节点
		client.create().forPath(PARENT_NODE, PARENT_NODE.getBytes());
		// 创建临时子节点
		client.create().withMode(CreateMode.EPHEMERAL).forPath(CHILD_NODE, CHILD_NODE.getBytes());
		// 创建临时有序节点
		client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(PARENT_SEQ_NODE, PARENT_SEQ_NODE.getBytes());

		Stat stat = client.checkExists().forPath(PARENT_NODE);
		System.out.println("PARENT_NODE 是否存在： " + (stat != null ? true : false));

		// 直接创建子节点，没有父节点也一并创建
		client.create().orSetData().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(SUPER_CHILD_NODE,
				SUPER_CHILD_NODE.getBytes());

		System.out.println("SUPER_CHILD_NODE 是否存在： " + (stat != null ? true : false));

		// 异步修改数据 inBackground() 触发异步
		client.setData().inBackground().forPath(ASYNC_LISTENER_NODE, (ASYNC_LISTENER_NODE + 1).getBytes());

		// 异步创建节点
		client.create().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
			@Override
			public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
				System.out.println("callback " + event);
			}
		}).forPath(ASYNC_CALLBACK_NODE);

		// 事务模式
		// 定义几个基本操作
		
		String argsToUse = "/".concat(String.valueOf(ThreadLocalRandom.current().nextDouble()));
		CuratorOp createOp = client.transactionOp().create().forPath(argsToUse, argsToUse.getBytes());

		CuratorOp setDataOp = client.transactionOp().setData().forPath(argsToUse, "other data".getBytes());

		CuratorOp deleteOp = client.transactionOp().delete().forPath(argsToUse);

		// 事务执行结果
		List<CuratorTransactionResult> results = client.transaction().forOperations(createOp, setDataOp, deleteOp);

		// 遍历输出结果
		results.forEach(r -> {
			System.out.println("执行结果是： " + r.getForPath() + " --" + r.getType());
		});

		System.in.read();
	}
}
