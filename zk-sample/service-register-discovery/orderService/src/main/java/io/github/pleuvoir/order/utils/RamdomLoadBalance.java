package io.github.pleuvoir.order.utils;

import java.util.Random;

import org.springframework.util.CollectionUtils;

public class RamdomLoadBalance extends LoadBalance {
	
	@Override
	public String choseServiceHost() {
		String result = "";
		if (!CollectionUtils.isEmpty(SERVICE_LIST)) {
			int index = new Random().nextInt(SERVICE_LIST.size());
			result = SERVICE_LIST.get(index);
		}
		System.out.println("负载均衡到： " + result);
		return result;
	}
}
