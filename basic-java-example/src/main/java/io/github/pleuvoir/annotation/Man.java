package io.github.pleuvoir.annotation;

import io.github.pleuvoir.annotation.ValueBind.FieldType;

@Component(value = "io.github.pleuvoir.annotation.Man")
public class Man extends Person{

	/**
	 * 标记字段
	 */
	@ValueBind(type = FieldType.STRING, value = "pleuvoir")
	private String name;
	
	/**
	 * 标记字段
	 */
	@ValueBind(type = FieldType.INTEGER, value = "26")
	private String age;
	
	/**
	 * 标记方法
	 */
	@ValueBind(type = FieldType.BOOLEAN, value = "true")
	public void initMale(){}
	
}
