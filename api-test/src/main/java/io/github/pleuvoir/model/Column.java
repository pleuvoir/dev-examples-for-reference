package io.github.pleuvoir.model;


import io.github.pleuvoir.service.BuildService;
import lombok.Getter;

/**
 * 页面元素，使用建造者模式构建
 * @author pleuvoir
 * 
 */
public class Column {

	public Column(String name) {
		this.name = name;
	}
	
	/**
	 * 通过此方法构建页面元素
	 */
	public static Column of(String name) {
		return new Column(name);
	}
	
	/**
	 * 页面元素的类型，默认为输入框
	 */
	@Getter
	private ElementsType elementsType = ElementsType.INPUT;

	@Getter
	private String name;

	@Getter
	private String placeholder;

	@Getter
	private String remark;

	/**
	 * 该字段是否必须，默认是
	 */
	@Getter
	protected Boolean required = true;

	@Getter
	private Select select;

	@Getter
	private String value;


	public Column name(String name) {
		this.name = name;
		return this;
	}

	public Column placeholder(String placeholder) {
		this.placeholder = placeholder;
		return this;
	}

	public Column remark(String remark) {
		this.remark = remark;
		return this;
	}

	public Column required(Boolean required) {
		this.required = required;
		return this;
	}
	
	public Column value(String value) {
		this.value = value;
		return this;
	}

	
	public Column setElementsType(ElementsType elementsType) {
		this.elementsType = elementsType;
		return this;
	}

	public Column setSelect(BuildService<Select> service) {
		this.elementsType = ElementsType.SELECT;
		this.select = service.build();
		return this;
	}

	public Column setTextarea() {
		this.elementsType = ElementsType.TEXTAREA;
		return this;
	}

}
