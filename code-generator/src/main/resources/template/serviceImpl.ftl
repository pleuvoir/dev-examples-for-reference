package cn.com.cicpay.leopard.service.impl;

import java.util.List;
import cn.com.cicpay.leopard.model.DataGrid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import cn.com.cicpay.leopard.dao.${entityName}Dao;
import cn.com.cicpay.leopard.exception.BusinessException;
import cn.com.cicpay.leopard.model.hbm.${entityName};
import cn.com.cicpay.leopard.service.${entityName}Service;

@Service("${entityName?uncap_first}Service")
@Transactional(propagation = Propagation.SUPPORTS)
public class ${entityName}ServiceImpl implements ${entityName}Service{
	
	@Autowired
	private ${entityName}Dao ${entityName?uncap_first}Dao;
	
	
	@Override
	public DataGrid getDataGrid(${entityName} ${entityName?uncap_first},Integer page,Integer rows) {
		long total = ${entityName?uncap_first}Dao.get${entityName}Count(${entityName?uncap_first});
		List<?> result = null;
		if (total > 0) {
			result = ${entityName?uncap_first}Dao.get${entityName}s(${entityName?uncap_first},page,rows);
		}
		return new DataGrid(result, total);
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void save(${entityName} ${entityName?uncap_first}) {
		if(!${entityName?uncap_first}Dao.save(${entityName?uncap_first})){
			throw new BusinessException("添加失败");
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void update(${entityName} ${entityName?uncap_first}Form) {
		${entityName} ${entityName?uncap_first} = ${entityName?uncap_first}Dao.get${entityName}By${primaryKey?cap_first}(${entityName?uncap_first}Form.get${primaryKey?cap_first}());
		if(${entityName?uncap_first} == null) throw new BusinessException("获取失败");
		//重新赋值
		<#list columnExtendList as columnExtend>
		<#if "${columnExtend.field}" !="id">
		<#if "${columnExtend.convertedType}" =="String">
		if(StringUtils.isNotBlank(${entityName?uncap_first}Form.get${columnExtend.field?cap_first}()))
			${entityName?uncap_first}.set${columnExtend.field?cap_first}(${entityName?uncap_first}Form.get${columnExtend.field?cap_first}());
			
		<#else>
		if(${entityName?uncap_first}Form.get${columnExtend.field?cap_first}()!= null )
			${entityName?uncap_first}.set${columnExtend.field?cap_first}(${entityName?uncap_first}Form.get${columnExtend.field?cap_first}());
			
		</#if> 
		</#if>
		</#list>
		//修改
		if(!${entityName?uncap_first}Dao.save(${entityName?uncap_first})){
			throw new BusinessException("修改失败");
		}
	}

	@Override
	public ${entityName} get${entityName}By${primaryKey?cap_first}(<#list columnExtendList as columnExtend><#if "${primaryKey}"=="${columnExtend.field}">${columnExtend.convertedType}</#if></#list> ${primaryKey}) {
		return ${entityName?uncap_first}Dao.get${entityName}By${primaryKey?cap_first}(${primaryKey});
	}
	
	

}
