package io.github.pleuvoir;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class SimpleFetch implements Fetch {

	@Setter
	@Getter
	private String name;

	private List<Step> steps = Lists.newArrayList();

	ExecutorService pool = Executors.newFixedThreadPool(1, new NameThreadFactoryImpl("async_fetch"));


	@Override
	public Object fetch() {
		return this.run();
	}

	@Override
	public Future fetchAsync() {
		Future<Object> f = pool.submit(this::run);
		return f;
	}

	public Object run() {

		long start = System.currentTimeMillis();

		Assert.notEmpty(steps, "流程不能为空");

		AtomicLong success = new AtomicLong(0);
		AtomicLong fail = new AtomicLong(0);


		log.info("开始执行任务<{}>，流程数={}", getName(), steps.size());


		List<Object> result = Lists.newArrayList();
		steps.parallelStream().forEach(s -> {
			log.info("任务<{}>，流程<{}>准备执行", getName(), s.getStepName());

			StepExecution execution = s.execute();

			log.info("任务<{}>，流程<{}>执行结束", getName(), s.getStepName());

			if (execution.isSuccess()) {
				success.incrementAndGet();
				result.add(execution.getResult());
			} else {
				fail.incrementAndGet();
			}
		});

		log.info("任务<{}>执行结束，成功流程={}，失败流程={}，耗时：{}ms", getName(), success.get(), fail.get(),
				System.currentTimeMillis() - start);

		return result;
	}

	public void addStepLast(Step step) {
		this.steps.add(step);
	}

}
