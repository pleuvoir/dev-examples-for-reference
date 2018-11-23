package io.github.pleuvoir.chapter1.conditional;

public class RedisCondition extends AbstractKeyValueMatchCondition {

	@Override
	public String key() {
		return "redis.type";
	}

	@Override
	public String value() {
		return "lettuce";
	}


}
