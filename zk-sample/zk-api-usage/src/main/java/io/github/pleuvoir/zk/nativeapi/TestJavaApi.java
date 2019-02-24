package io.github.pleuvoir.zk.nativeapi;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;

/**
 * ZK 基本 操作
 * 
 * <p>
 * <li>注意：原生 api 中 watcher 只能用一次，所以操作之前必须要打开 。修改前先查一次，删除前先看看存不存在 ，顺便开启 watcher=true<li>
 * 
 * <li>临时节点下不能创建子节点<li>
 * </p>
 * 
 * @author pleuvoir
 *
 */
public class TestJavaApi {

	public static final String PARENT_NODE = "/leader";

	public static final String CHILD_NODE = "/leader/child";
	
	public static final String PARENT_SEQ_NODE = "/leaderSeq";

	static CountDownLatch latch;

	static ZkHelper instance = ZkHelper.INSTANCE;
	
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

		clear();
		
		// 创建一个永久节点
		instance.create(PARENT_NODE, PARENT_NODE, CreateMode.PERSISTENT);
		// 创建一个临时子节点，注意，临时节点下不能创建子节点
		instance.create(CHILD_NODE, CHILD_NODE, CreateMode.EPHEMERAL);

		// 创建临时有序节点
		instance.create(PARENT_SEQ_NODE, PARENT_SEQ_NODE, CreateMode.EPHEMERAL_SEQUENTIAL);
//
		ZkHelper.INSTANCE.update(PARENT_NODE, "1");

		ZkHelper.INSTANCE.remove(CHILD_NODE);

	//	instance.remove(CHILD_NODE);
		System.in.read();

	}

	
	public static void clear() {
		instance.remove(CHILD_NODE);
		instance.remove(PARENT_NODE);
	}
}
