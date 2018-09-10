package cn.com.cicpay.leopard.dao;
import java.util.List;

import cn.com.cicpay.leopard.model.hbm.${entityName};


public interface ${entityName}Dao {


	/**
	 * 获取全部并分页
	 * @param ${entityName?uncap_first}
	 * @param page
	 * @param rows
	 * @return
	 */
	List<${entityName}> get${entityName}s(${entityName} ${entityName?uncap_first},Integer page, Integer rows);

	/**
	 * 获取总数
	 * @param ${entityName?uncap_first}
	 * @return
	 */
	int get${entityName}Count(${entityName} ${entityName?uncap_first});
	
	/**保存*/
	boolean save(Object object);
	
	/**
	 * 根据${primaryKey}获取
	 * @param id
	 * @return
	 */					
	${entityName} get${entityName}By${primaryKey?cap_first}(<#list columnExtendList as columnExtend><#if "${primaryKey}"=="${columnExtend.field}">${columnExtend.convertedType}</#if></#list> ${primaryKey});
	
}
