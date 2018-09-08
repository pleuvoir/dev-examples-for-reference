package io.github.pleuvoir.reflect;

import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射工具类
 * @author pleuvoir
 * 
 */
@SuppressWarnings("unchecked")
public class ReflectUtil {

	private static final Logger logger = LoggerFactory.getLogger(ReflectUtil.class);
	
	/**
	 * 方法调用  适合无参函数调用
	 * @param className  类名：精确到包名
	 * @param methodName 方法名
	 * @return
	 */
	public static <T> T invoke(String className,String methodName){
		try {
			Class<?> clazz = Class.forName(className);
			Method method = clazz.getMethod(methodName);
			return (T) method.invoke(clazz.newInstance());
		} catch (Exception e) {
			logger.error("invoke error：{} ", e);
		}
		throw new IllegalStateException("reflect invoke error");
	}
	
	
	/**
	 * 方法调用  适合有参数的方法调用 使用时传入参数即可
	 * @param className    类名：精确到包名
	 * @param methodName   方法名
	 * @param args         参数
	 * @return
	 */
	public static <T> T invoke(String className,String methodName,Object... args){
		try {
			Class<?> clazz = Class.forName(className);
			Method method = clazz.getDeclaredMethod(methodName, convertToClazzs(args));
			return (T) method.invoke(clazz.newInstance(),args);
		} catch (Exception e) {
			logger.error("invoke error：{} ", e);
		}
		throw new IllegalStateException("reflect invoke error");
	}
	
	
	/**
	 * 根据参数 顺序返回对应的Class数组 <br>
	 * 例如  "James Gosling",45   则返回 java.lang.String, java.lang.Integer
	 * @param args
	 * @return
	 */
	private static Class<?>[] convertToClazzs(Object... args){
		Class<?>[] classTypes = new Class<?>[args.length];
		for (int i = 0, length = args.length; i < length; i++) {
			classTypes[i] = args[i].getClass();
		}
		return classTypes;
	}
	
	
}