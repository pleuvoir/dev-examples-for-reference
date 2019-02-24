package io.github.pleuvoir.product.zk;

import java.net.InetAddress;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ServiceRegister implements InitializingBean{
	
	@Value("${zk.hostandport}")
	private String zkHostAndPort;
	@Value("${services}")
	private String services;
	@Value("${server.port}")
	private int serverPort;

	public void register(String address, int port) {
		try {
			String path = this.services;
			ZooKeeper zooKeeper = new ZooKeeper(zkHostAndPort, 5000, (watchedEvent) -> {
			});
			Stat exists = zooKeeper.exists(path, false);
			// 先判断服务根路径是否存在
			if (exists == null) {
				zooKeeper.create("/services", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
			String server_path = address + ":" + port;
			zooKeeper.create(path + "/child", server_path.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
			System.out.println("产品服务注册成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("注册中心：" + zkHostAndPort);
		String hostAddress = InetAddress.getLocalHost().getHostAddress();
		int port = this.serverPort;
		System.out.println("服务请求地址：" + hostAddress.concat(":").concat(String.valueOf(port)));
		this.register(hostAddress, port);
	}
}
