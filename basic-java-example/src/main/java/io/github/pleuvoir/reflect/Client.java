package io.github.pleuvoir.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import lombok.SneakyThrows;

/**
 * 反射的简单示例
 * @author pleuvoir
 *
 */
public class Client {

	@SneakyThrows
	public static void main(String[] args) {
		// 生成对象
		Class<?> clazz = Class.forName("io.github.pleuvoir.reflect.Person");
		Person person = (Person) clazz.newInstance();
		// 不直接设置属性值，而是通过反射设置
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			System.out.println("属性：" + field);
			if(field.getName().equals("name")){
				field.set(person, "pleuvoir");
			}else if(field.getName().equals("age")){
				field.set(person, 26);
			}
		}
		System.out.println(person.toString());
		// 反射执行方法设置值
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println("方法：" + method.getName());
			switch (method.getName()) {
			case "setName": {
				method.invoke(person, "pleuvoir set again");
				break;
			}
			default:
				break;
			}
		}
		System.out.println(person.toString());
	}
}
