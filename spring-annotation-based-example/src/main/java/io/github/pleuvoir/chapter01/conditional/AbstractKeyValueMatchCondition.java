package io.github.pleuvoir.chapter01.conditional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 方便 spring 根据环境中 key，value 创建 bean
 * @author pleuvoir
 *
 */
public abstract class AbstractKeyValueMatchCondition implements KeyValueMatchCondition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

		String fieldKey = key();
		String fieldValue = value();
		
		if (StringUtils.isBlank(fieldKey) || StringUtils.isBlank(fieldValue)) {
			return false;
		}
		
		return StringUtils.equalsIgnoreCase(fieldValue, context.getEnvironment().getProperty(fieldKey));
	}

}
