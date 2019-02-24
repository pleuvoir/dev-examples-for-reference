package io.github.pleuvoir.order.listener;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import io.github.pleuvoir.order.utils.LoadBalance;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InitListener implements ServletContextListener {

    private  static final String BASE_SERVICES = "/services";
    private static final String  SERVICE_NAME="/products";
    private   ZooKeeper zooKeeper;

	private void init() {
		try {
			zooKeeper = new ZooKeeper("39.105.110.40:2181", 5000, (watchedEvent) -> {
				if (watchedEvent.getType() == Watcher.Event.EventType.NodeChildrenChanged
						&& watchedEvent.getPath().equals(BASE_SERVICES + SERVICE_NAME)) {
					System.out.println("监听到子节点变化。开始更新服务器信息。。。");
					updateServerList();
				}
			});
			updateServerList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 初始化所有服务节点
    private  void updateServerList() {
        List<String> newServerList = new ArrayList<String>();
        try {
            List<String> children = zooKeeper.getChildren(BASE_SERVICES  + SERVICE_NAME, true);
            for(String subNode:children) {
                byte[] data = zooKeeper.getData(BASE_SERVICES  + SERVICE_NAME + "/" + subNode, false, null);
                String host = new String(data, "utf-8");
                newServerList.add(host);
            }
            LoadBalance.SERVICE_LIST = newServerList;
            System.out.println("服务器信息更新完成，当前可用的服务："+ Arrays.asList(newServerList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
