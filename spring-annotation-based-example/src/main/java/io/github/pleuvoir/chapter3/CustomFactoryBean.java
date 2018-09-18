package io.github.pleuvoir.chapter3;

import org.springframework.beans.factory.FactoryBean;

import io.github.pleuvoir.base.Fish;

public class CustomFactoryBean implements FactoryBean<Fish> {

	@Override
	public Fish getObject() throws Exception {
		return new Fish();
	}

	@Override
	public Class<?> getObjectType() {
		return Fish.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
