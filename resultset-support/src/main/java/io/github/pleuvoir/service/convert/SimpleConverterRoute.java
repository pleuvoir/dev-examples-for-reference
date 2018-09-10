package io.github.pleuvoir.service.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.pleuvoir.config.AppConfig;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SimpleConverterRoute implements ConverterRoute {

	@Autowired
	private AppConfig appConfig;

	public ConverterService route(String driverClassName) {
		switch (driverClassName) {
		case "oracle.jdbc.driver.OracleDriver": {
			return new ConverterService() {

				@Override
				public String convert(String columnTypeName) {
					String type = "";
					switch (columnTypeName) {
					case "VARCHAR2":
						type = "String";
						break;
					case "TIMESTAMP":
						type = "LocalDateTime";
						break;
					case "DATE":
						type = "LocalDateTime";
						break;
					case "NUMBER":
						type = "BigDecimal";
						break;
					case "BLOB":
						type = "byte[]";
						break;
					default:
						type = "String";
						assert false;
					}
					log.info("列转换：{} -> {}", columnTypeName, type);
					return type;
				}

			};
		}
		case "com.mysql.jdbc.Driver": {
			return new ConverterService() {

				@Override
				public String convert(String columnTypeName) {
					throw new RuntimeException("期待你的实现");
				}
			};
		}
		default:
			throw new RuntimeException("不支持的数据库类型");
		}
	}

	
	@Override
	public ConverterService autoRoute() {
		return route(appConfig.getDriverClassName());
	}

}
