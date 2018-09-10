package io.github.pleuvoir.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnExtend extends Column {

	// 转换后为Java后的类型
	@JSONField(serialize = true)
	private String convertedType;

	// 驼峰格式属性名
	private String field;

}
