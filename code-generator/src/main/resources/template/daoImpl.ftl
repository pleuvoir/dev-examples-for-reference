package cn.com.cicpay.leopard.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cn.com.cicpay.leopard.model.SqlResultRowHandler;
import cn.com.cicpay.leopard.dao.BasicDao;
import cn.com.cicpay.leopard.dao.BasicSqlDao;
import cn.com.cicpay.leopard.dao.${entityName}Dao;
import cn.com.cicpay.leopard.model.QParams;
import cn.com.cicpay.leopard.model.hbm.${entityName};



@Repository("${entityName?uncap_first}Dao")
public class ${entityName}DaoImpl implements ${entityName}Dao{
	
	@Autowired
	private BasicDao hqlDao;
	@Autowired
	private BasicSqlDao sqlDao;
	
	private QParams getParams(String hql,${entityName} ${entityName?uncap_first}){
		QParams q = QParams.newInstanceUseMapParams(hql);
		<#list columnExtendList as columnExtend>
		<#list "${querycolumns}"?split(",") as s>
		<#if "${s?lower_case}" == "${columnExtend.columnName?lower_case}">
		<#if "${columnExtend.convertedType}" =="String">
		if(StringUtils.isNotBlank(${entityName?uncap_first}.get${'${columnExtend.field}'?cap_first}()))
			q.addCondition("${s?lower_case} = :${columnExtend.field}","${columnExtend.field}",StringUtils.trim(${entityName?uncap_first}.get${'${columnExtend.field}'?cap_first}()));	
		<#else>
		if(${entityName?uncap_first}.get${'${columnExtend.field}'?cap_first}()!= null)
			q.addCondition("${s?lower_case} = :${columnExtend.field}","${columnExtend.field}",${entityName?uncap_first}.get${'${columnExtend.field}'?cap_first}());	
		</#if>	
		</#if>
		</#list>
		</#list>
		return q;
	}

	@Override
	public List<${entityName}> get${entityName}s(${entityName} ${entityName?uncap_first},Integer page, Integer rows) {
		String hql = "from ${entityName}";
		QParams q = getParams(hql,${entityName?uncap_first});
		//q.setOrderBy("createdTime desc");
		return hqlDao.find(q.getQuery(), q.getMapParams(), page, rows);
	}

	@Override
	public int get${entityName}Count(${entityName} ${entityName?uncap_first}) {
		String hql = "select count(1) from ${entityName}";
		QParams q = getParams(hql,${entityName?uncap_first});
		return (int) hqlDao.count(q.getQuery(),q.getMapParams());
	}

	@Override
	public boolean save(Object object) {
		return hqlDao.save(object) != null;
	}

	@Override
	public ${entityName} get${entityName}By${primaryKey?cap_first}(<#list columnExtendList as columnExtend><#if "${primaryKey}"=="${columnExtend.field}">${columnExtend.convertedType}</#if></#list> ${primaryKey}) {
		String hql = "from ${entityName} where ${primaryKey} = ?0";
		return (${entityName})hqlDao.get(hql, new Object[]{${primaryKey}});
	}

}
