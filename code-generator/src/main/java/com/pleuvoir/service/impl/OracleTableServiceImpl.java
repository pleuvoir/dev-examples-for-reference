package com.pleuvoir.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import com.pleuvoir.bean.ColumnExtend;
import com.pleuvoir.service.AbstractTableService;
import com.pleuvoir.utils.BaseUtils;
import com.pleuvoir.utils.Constant;

public class OracleTableServiceImpl extends AbstractTableService {
	

	@Override
	public String getPrimaryKey(){
		String primaryKey = null;
		try {
			Class.forName(Constant.DB_DRIVER);
			Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);
			//获取主键名     要将表名转为大写才能正确取出主键来
			DatabaseMetaData dbmd=  conn.getMetaData();   
			ResultSet rs = dbmd.getPrimaryKeys(null,null,Constant.TABLE_NAME.toUpperCase());   
			while(rs.next()) {
				primaryKey = rs.getString(4);
			}
			// 关闭声明
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// 关闭链接对象
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return primaryKey;
	}
	
	
	
	@Override
	public List<ColumnExtend> getColumnExtends() {
		List<ColumnExtend> ColumnExtendList = new LinkedList<ColumnExtend>();
		try {
			Class.forName(Constant.DB_DRIVER);
			Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + Constant.TABLE_NAME);
			ResultSetMetaData metaData = ps.getMetaData();
			int count = metaData.getColumnCount();
			for (int i = 1; i <= count; i++) {
				ColumnExtend columnExtend = new ColumnExtend();
				columnExtend.setColumnName(metaData.getColumnName(i));
				columnExtend.setColumnClassName(metaData.getColumnClassName(i));
				columnExtend.setColumnTypeName(metaData.getColumnTypeName(i));
				columnExtend.setIsNullable(metaData.isNullable(i)== 1?"true":"false");
				columnExtend.setPrecision(metaData.getPrecision(i));
				columnExtend.setScale(metaData.getPrecision(i));
				columnExtend.setColumnDisplaySize(metaData.getColumnDisplaySize(i));
				columnExtend.setConvertedType(getConvertedType(metaData.getColumnTypeName(i)));  //转换后的类型
				columnExtend.setField(BaseUtils.toCamelCase(metaData.getColumnName(i)));     //字段名
				ColumnExtendList.add(columnExtend);
			}
			// 关闭声明
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// 关闭链接对象
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ColumnExtendList;
	}



	
	
	@Override
	public String getConvertedType(String columnTypeName) {
		String type = "";
		switch (columnTypeName) {
		case "VARCHAR2":
			type = "String";
			break;
		case "TIMESTAMP":
			type = "Date";
			break;
		case "NUMBER":
			type = "BigDecimal";
			break;
		case "BLOB":
			type = "byte[]";
			break;	
		default:
			type = "String";
			break;
		}
		return type;
	}
	
	
	

}
