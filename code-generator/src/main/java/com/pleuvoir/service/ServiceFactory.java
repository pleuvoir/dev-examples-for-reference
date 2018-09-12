package com.pleuvoir.service;

import com.pleuvoir.service.impl.MysqlTableServiceImpl;
import com.pleuvoir.service.impl.OracleTableServiceImpl;
import com.pleuvoir.utils.Constant;

public class ServiceFactory {

	public static TableService getInstance(){
		if("oracle".equalsIgnoreCase(Constant.DB_TYPE)){
			return new OracleTableServiceImpl();
		} 
		if("mysql".equalsIgnoreCase(Constant.DB_TYPE)){
			return new MysqlTableServiceImpl();
		}
		throw new RuntimeException("不支持的数据库类型");
	}
	
	
}
