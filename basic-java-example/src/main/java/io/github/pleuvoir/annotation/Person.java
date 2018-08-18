package io.github.pleuvoir.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Person {

	public void show() throws ClassNotFoundException {
		// 检查是否被Component注解标记
		if (this.getClass().isAnnotationPresent(Component.class)) {
			// 获取注解的值
			String qualifiedClassName = this.getClass().getAnnotation(Component.class).value();
			System.out.println("全限定类名：" + qualifiedClassName);
			// 实例化该类
			Class<?> clazz = Class.forName(qualifiedClassName);
			// 遍历所有字段
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(ValueBind.class)) {
					// 取得字段上的标记
					ValueBind valueBind = field.getAnnotation(ValueBind.class);
					String value = valueBind.value();
					String type = valueBind.type().value();
					System.out.println("当前字段为：" + field.getName() + "，类型：" + type + "，值：" + value);
				}
			}
			// 遍历所有方法
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				if(method.isAnnotationPresent(ValueBind.class)){
					ValueBind valueBind = method.getAnnotation(ValueBind.class);
					String value = valueBind.value();
					String type = valueBind.type().value();
					System.out.println("当前方法为：" + method.getName() + "，类型：" + type + "，值：" + value);
				}
			}
		} else {
			System.out.println(this.getClass() + " 类未被Component注解标记，忽略");
		}
	}

}
