package io.github.pleuvoir.config;

import io.github.pleuvoir.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfiguration {

	@Bean
	public Step paymentItemReaderStep() {
		return Step.create()
				.stepName("获取订单表数据")
				.itemReader(new PaymentItemReader())
				.ignoreExcpetion(true)
				.build();
	}

	@Bean
	public Step redpackItemReadeStep() {
		return Step.create()
				.stepName("获取红包表数据")
				.itemReader(new RedpackItemReader())
				.ignoreExcpetion(true)
				.build();
	}

	@Bean
	public Fetch fetch() {
		SimpleFetch simpleFetch = new SimpleFetch();
		simpleFetch.setName("获取对账数据");
		simpleFetch.addStepLast(paymentItemReaderStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(paymentItemReaderStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(paymentItemReaderStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		simpleFetch.addStepLast(redpackItemReadeStep());
		return simpleFetch;
	}
}
