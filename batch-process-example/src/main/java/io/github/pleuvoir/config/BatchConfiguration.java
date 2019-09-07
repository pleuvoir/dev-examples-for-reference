package io.github.pleuvoir.config;

import io.github.pleuvoir.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfiguration {


	@Bean
	public RedpackItemReader redpackItemReader() {
		return new RedpackItemReader();
	}

	@Bean
	public PaymentItemReader paymentItemReader() {
		return new PaymentItemReader();
	}

	@Bean
	public Step paymentItemReaderStep() {
		return Step.create()
				.stepName("获取订单表数据")
				.itemReader(paymentItemReader())
				.ignoreExcpetion(true)
				.build();
	}

	@Bean
	public Step redpackItemReadeStep() {
		return Step.create()
				.stepName("获取红包表数据")
				.itemReader(redpackItemReader())
				.ignoreExcpetion(true)
				.build();
	}

	@Bean
	public Fetch fetch() {
		SimpleFetch simpleFetch = new SimpleFetch();
		simpleFetch.setName("获取对账数据");
		simpleFetch.addStepLast(paymentItemReaderStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
//		simpleFetch.addStepLast(redpackItemReadeStep());
//		simpleFetch.addStepLast(redpackItemReadeStep());
		return simpleFetch;
	}
}
