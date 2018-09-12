
package com.pleuvoir.bean;

import java.util.ArrayList;
import java.util.List;

import com.pleuvoir.utils.Constant;

public class FreemakerParamsMap{
	
	private List<ColumnExtend> ColumnExtendList = new ArrayList<ColumnExtend>();
	
	private String entityName = Constant.ENTITY_NAME;
	
	private String tableName  = Constant.TABLE_NAME;
	
	private String querycolumns = Constant.QUERY_COLUMN;
	
	private String viewcolumns = Constant.VIEW_COLUMN;
	
	//主键名
	private String primaryKey;
	

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<ColumnExtend> getColumnExtendList() {
		return ColumnExtendList;
	}

	public void setColumnExtendList(List<ColumnExtend> columnExtendList) {
		ColumnExtendList = columnExtendList;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getQuerycolumns() {
		return querycolumns;
	}

	public void setQuerycolumns(String querycolumns) {
		this.querycolumns = querycolumns;
	}

	public String getViewcolumns() {
		return viewcolumns;
	}

	public void setViewcolumns(String viewcolumns) {
		this.viewcolumns = viewcolumns;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	
	
}
