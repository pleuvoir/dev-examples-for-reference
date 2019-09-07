package io.github.pleuvoir;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class RedpackItemReader implements ItemReader<List> {


	@Override
	public List read() {

		//from db
		ArrayList<Object> result = Lists.newArrayList();
		result.add("redpack-1");
		result.add("redpack-2");

		try {
			TimeUnit.SECONDS.sleep(8);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(ThreadLocalRandom.current().nextBoolean()){
			throw new RuntimeException("红包获取数据运行时异常");
		}
		return result;
	}
}
