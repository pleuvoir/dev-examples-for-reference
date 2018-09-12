package io.github.pleuvoir.service.convert;

public interface ConverterRoute {

	ConverterService route(String driverClassName);
	
	ConverterService autoRoute();
}
