package com.pleuvoir.service;

import java.util.ArrayList;
import java.util.List;

import com.pleuvoir.bean.FreemakerParamsMap;
import com.pleuvoir.utils.BaseUtils;
import com.pleuvoir.utils.Constant;

public abstract class AbstractTableService implements TableService {


	@Override
	public FreemakerParamsMap getFreemakerParams(){
		FreemakerParamsMap paramsMap = new FreemakerParamsMap();
		paramsMap.setColumnExtendList(getColumnExtends());
		paramsMap.setPrimaryKey(BaseUtils.toCamelCase(this.getPrimaryKey()));
		paramsMap.setQuerycolumns(this.getActuallyQueryField());
		paramsMap.setViewcolumns(this.getActuallyViewField());
		return paramsMap;
	}
	
	
	
	@Override
	public List<String> getColumnNameList(){
		List<String> columnNames = new ArrayList<>();
		this.getColumnExtends().forEach(column -> columnNames.add(column.getColumnName()));
		return columnNames;
	}

	
	@Override
	public String getActuallyQueryField(){
		StringBuilder sb = new StringBuilder();
		if(BaseUtils.isBlank(Constant.QUERY_COLUMN)){
			return "";
		}
		String[] split = Constant.QUERY_COLUMN.split(Constant.SEPARATOR_COMMA);
		for (String string : split) {
			for (String columnName : getColumnNameList()) {
				if(string.equalsIgnoreCase(columnName)){
				    sb.append(columnName).append(Constant.SEPARATOR_COMMA);
					break;
				}
			}
		}
		String result = String.valueOf(sb).toLowerCase();
		if(BaseUtils.isBlank(result)){
			return "";
		}
		return result.substring(0, result.lastIndexOf(","));
	}

	
	@Override
	public String getActuallyViewField(){
		StringBuilder sb = new StringBuilder();
		if(BaseUtils.isBlank(Constant.VIEW_COLUMN)){
			return BaseUtils.appendBySeparator(getColumnNameList(), Constant.SEPARATOR_COMMA);
		}
		String[] split = Constant.VIEW_COLUMN.split(Constant.SEPARATOR_COMMA);
		for (String string : split) {
			for (String columnName : getColumnNameList()) {
				if(string.equalsIgnoreCase(columnName)){
					sb = sb.append(columnName).append(Constant.SEPARATOR_COMMA);
					break;
				}
			}
		}
		String result = String.valueOf(sb).toLowerCase();
		if(BaseUtils.isBlank(result)){
			return "";
		}
		return result.substring(0, result.lastIndexOf(","));
	}

	

}
