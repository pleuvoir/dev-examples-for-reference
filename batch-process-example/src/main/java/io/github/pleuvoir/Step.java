package io.github.pleuvoir;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder(builderMethodName = "create")
@Data
public class Step {

	private String stepName;

	private boolean ignoreExcpetion = false;

	private ItemReader<? extends Object> itemReader;

	public StepExecution execute() {
		StepExecution stepExecution = new StepExecution();
		stepExecution.setSuccess(false);

		StepExecution read = null;
		try {

			Object o = itemReader.read();
			stepExecution.setSuccess(true);
			stepExecution.setResult(o);

		} catch (Throwable e) {
			if (ignoreExcpetion) {
				log.warn("流程<{}>执行异常，忽略异常继续执行。", getStepName(), e);
			} else {
				log.error("流程<{}>执行异常，停止执行。", getStepName(), e);
				throw e;
			}
		}

		return stepExecution;
	}

}
