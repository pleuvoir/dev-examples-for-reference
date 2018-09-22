package io.github.pleuvoir.chapter6.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Override
	public String doService(String param) {
		System.out.println("执行目标方法，入参： " + param);
		if (ThreadLocalRandom.current().nextBoolean()) {
			Integer.parseInt(param); // 尝试转为 int 制造异常
		}
		return "返回了" + param;
	}
}
