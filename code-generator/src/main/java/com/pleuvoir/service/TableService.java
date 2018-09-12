package com.pleuvoir.service;

import java.sql.SQLException;
import java.util.List;

import com.pleuvoir.bean.ColumnExtend;
import com.pleuvoir.bean.FreemakerParamsMap;

public interface TableService {
	
	/**
	 * 获取主键名称
	 * @return
	 */
	public abstract String getPrimaryKey();
	
	
	/**
	 * 取得列扩展信息
	 * @return
	 */
	public abstract List<ColumnExtend> getColumnExtends();
	
	
	/**
	 * 取得表中所有列名集合
	 * @return
	 */
	public abstract List<String> getColumnNameList();

	
	/**
	 * 数据库字段对应的java类型
	 * @param columnTypeName
	 * @return
	 */
	public abstract String getConvertedType(String columnTypeName);
	
	/**
	 * 实际显示的查询字段
	 * @return
	 * @throws SQLException
	 */
	public abstract String getActuallyQueryField();
	
	/**
	 * 实际显示的列表页字段 没输入则显示全部列
	 * @return
	 * @throws SQLException
	 */
	public abstract String getActuallyViewField();
	
		
	/**
	 * freemaker需要的参数
	 * @return
	 */
	public abstract FreemakerParamsMap getFreemakerParams();


}
