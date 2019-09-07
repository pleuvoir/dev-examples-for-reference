package io.github.pleuvoir;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class PaymentItemReader implements ItemReader<List> {


	@Override
	public List read() {

		//from db
		ArrayList<Object> result = Lists.newArrayList();
		result.add("payment-1");
		result.add("payment-2");

		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if(ThreadLocalRandom.current().nextBoolean()){
			throw new RuntimeException("订单获取数据运行时异常");
		}
		return result;
	}
}
