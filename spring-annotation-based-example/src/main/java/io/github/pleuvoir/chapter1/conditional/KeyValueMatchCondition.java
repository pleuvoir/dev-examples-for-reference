package io.github.pleuvoir.chapter1.conditional;

import org.springframework.context.annotation.Condition;

public interface KeyValueMatchCondition extends Condition {

	String key();

	String value();

}
