package io.github.pleuvoir.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Column {

	/**列名 */
	private String columnName;

	/** 数据库字段类型  */
	@JSONField(serialize=false)
	private String columnTypeName;

	/** 对应的java类型  */
	@JSONField(serialize=false)
	private String columnClassName;

	/** 在数据库中类型的最大字符个数  */
	@JSONField(serialize=false)
	private int columnDisplaySize;

	/** 类型的精确度(类型的长度)  */
	@JSONField(serialize=false)
	private int precision;

	/**小数点后的位数  */
	@JSONField(serialize=false)
	private int scale;

	/** 是否可为空 true:可为空  */
	@JSONField(serialize=false)
	private String isNullable;

	
}
