package io.github.pleuvoir.payload.support;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.reflections.Reflections;
import io.github.pleuvoir.payload.annotation.Controller;
import io.github.pleuvoir.payload.annotation.RequestMapping;

public class InitService {

	/**
	 * 保存 servlet 请求路径和  反射调用的方法 <br>
	 * Example： /github/password public void io.github.pleuvoir.controller.HelloWorldController.password()
	 */
	private static Map<String, Method> routers = new ConcurrentHashMap<>();
	
	
	public static void scan(String... packageNames) {
		
		// 扫描所有包
		for (String packageName : packageNames) {
			Reflections packageInfo = new Reflections(packageName);
			// 获取所有被 Controller 标注的类
			for (Class<?> clazz : packageInfo.getTypesAnnotatedWith(Controller.class)) {
				// 同时被 RequestMapping 标注的类
				if (clazz.isAnnotationPresent(RequestMapping.class)) {
					init(clazz);
				}
			}
		}
		print();
	}
	
	
	private static void init(Class<?> typeClazz) {
		for (Method method : typeClazz.getDeclaredMethods()) {
			if (method.isAnnotationPresent(RequestMapping.class)) {
				routers.put(
						repairPath(typeClazz.getAnnotation(RequestMapping.class).value())
						.concat(repairPath(method.getAnnotation(RequestMapping.class).value())), method);
			}
		}
	}
	
	
	public static Map<String, Method> getRouters() {
		return routers;
	}


	private static void print() {
		routers.entrySet().forEach(k->{
			System.out.println("已注册：" + k.getKey()   + " --> Method： " + k.getValue());
		});
		System.out.println("已注册：" + routers.size());
	}
	
	
	public static String repairPath(String path) {
		StringBuilder sb = new StringBuilder(path);
		if (!path.startsWith("/")) {
			sb.insert(0, "/");
		}
		if (path.endsWith("/")) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	
}
