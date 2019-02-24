package io.github.pleuvoir.order.utils;

import java.util.List;

/**
 * 负载均衡
 * @author pleuvoir
 *
 */
public abstract class LoadBalance {

	// 服务端 ip+port
	public volatile static List<String> SERVICE_LIST;

	public abstract String choseServiceHost();

}
