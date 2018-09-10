package cn.com.cicpay.leopard.service;
import cn.com.cicpay.leopard.model.DataGrid;
import java.util.List;
import cn.com.cicpay.leopard.model.hbm.${entityName};

public interface ${entityName}Service {

	public DataGrid getDataGrid(${entityName} ${entityName?uncap_first}, Integer page, Integer rows); 

	public void save(${entityName} ${entityName?uncap_first});

	public void update(${entityName} ${entityName?uncap_first}Form);

	public ${entityName} get${entityName}By${primaryKey?cap_first}(<#list columnExtendList as columnExtend><#if "${primaryKey}"=="${columnExtend.field}">${columnExtend.convertedType}</#if></#list> ${primaryKey});
	

}