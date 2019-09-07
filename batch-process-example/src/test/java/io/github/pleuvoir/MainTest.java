package io.github.pleuvoir;

import com.alibaba.druid.support.json.JSONUtils;

import java.util.List;

public class MainTest {

	public static void main(String[] args) {

		Step step = Step.create()
				.stepName("获取订单表数据")
				.itemReader(new PaymentItemReader())
				.ignoreExcpetion(false)
				.build();
		StepExecution execute = step.execute();


		System.out.println(execute.getResult());

	}
}
