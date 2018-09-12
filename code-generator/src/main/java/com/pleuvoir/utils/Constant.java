package com.pleuvoir.utils;

import com.pleuvoir.utils.PropReadUtils;

public class Constant {
	
	public static final String SEPARATOR_COMMA = ",";

	public static final String DB_TYPE = PropReadUtils.getProperString("db.type");
	
	public static final String DB_DRIVER = PropReadUtils.getProperString("db.driver");
	
	public static final String DB_URL = PropReadUtils.getProperString("db.url");
	
	public static final String DB_USER = PropReadUtils.getProperString("db.user");
	
	public static final String DB_PASSWORD = PropReadUtils.getProperString("db.password");
	
	public static final String TABLE_NAME = PropReadUtils.getProperString("tablename");
	
	/** 实体类名  没有则默认表名转驼峰*/
	public static final String ENTITY_NAME = PropReadUtils.getProperString("entityname",BaseUtils.toCapitalizeCamelCase(TABLE_NAME));
	
	/** 前台列表页查询字段 */
	public static final String QUERY_COLUMN = PropReadUtils.getProperString("querycolumns","");
	
	/** 前台列表页显示字段*/
	public static final String VIEW_COLUMN = PropReadUtils.getProperString("viewcolumns","");
	
	/** 生成代码文件夹位置*/
	public static final String TARGET_FILE_PATH = "code/";

	/** ftl模板文件夹位置*/
	public static final String TEMPLATE_SOURCE_PATH = "template/";

	
}
