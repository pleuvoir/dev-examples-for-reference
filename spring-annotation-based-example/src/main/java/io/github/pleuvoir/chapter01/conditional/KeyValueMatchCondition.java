package io.github.pleuvoir.chapter01.conditional;

import org.springframework.context.annotation.Condition;

public interface KeyValueMatchCondition extends Condition {

	String key();

	String value();

}
