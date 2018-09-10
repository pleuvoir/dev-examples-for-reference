
package com.pleuvoir;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.pleuvoir.bean.FreemakerParamsMap;
import com.pleuvoir.service.ServiceFactory;
import com.pleuvoir.service.TableService;
import com.pleuvoir.utils.Constant;
import com.pleuvoir.utils.FreemakerUtils;

public class Launcher {

	
	public static void main(String[] args) {
		GenerateHelp.run();
	}
	
	
	
	public static class GenerateHelp {

		private static  Logger logger = LoggerFactory.getLogger(GenerateHelp.class);
		public static void run(){
			try {
				long start = System.currentTimeMillis();
				TableService instance = ServiceFactory.getInstance();
				FreemakerParamsMap freemakerParams = instance.getFreemakerParams();
				logger.info("db.url:{}",Constant.DB_URL);
				logger.info("db.user:{}",Constant.DB_USER);
				logger.info("db.password:{}",Constant.DB_PASSWORD);
				logger.info("当前表为:{}",freemakerParams.getTableName());
				logger.info("实体类名为:{}",freemakerParams.getEntityName());
				logger.info("当前输入的查询条件为:{}",Constant.QUERY_COLUMN);
				logger.info("实际生成的查询条件为:{}",instance.getActuallyQueryField());
				logger.info("当前输入的列表页显示字段为:{}",Constant.VIEW_COLUMN);
				logger.info("实际生成的列表页显示字段为:{}",instance.getActuallyViewField());
				logger.debug(JSON.toJSONString(freemakerParams));
				FreemakerUtils.printFile("entity", freemakerParams);
				FreemakerUtils.printFile("dao", freemakerParams);
				FreemakerUtils.printFile("daoImpl", freemakerParams);
				FreemakerUtils.printFile("service", freemakerParams);
				FreemakerUtils.printFile("serviceImpl", freemakerParams);
				FreemakerUtils.printFile("controller", freemakerParams);
				FreemakerUtils.printFile("list", freemakerParams);
				long end = System.currentTimeMillis();
				logger.info("finished in {} ms",String.valueOf(end-start));
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
	}

}
