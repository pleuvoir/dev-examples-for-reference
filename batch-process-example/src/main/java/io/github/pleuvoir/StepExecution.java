package io.github.pleuvoir;

import lombok.Data;

@Data
public class StepExecution {

	private boolean success = false;

	private Object result;
}
