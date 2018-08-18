package io.github.pleuvoir.container;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import io.github.pleuvoir.annotation.Inject;

/**
 * IOC容器的简单实现
 * @author pleuvoir
 *
 */
public class SimpleContainer extends AbstractContainer {

	@Override
	public void init() {
		//[1] 遍历所有容器中已注册的对象
		Iterator<Entry<String, Object>> iterator = qualifiedNameRegistedBeanHolder.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
			//[2] 开始注入bean
			Object bean = entry.getValue();
			//[3] 获取bean的所有字段
			Field[] fields = bean.getClass().getDeclaredFields();
			//[4] 检查所有字段是否被我们自定义的注入注解标记
			for (Field field : fields) {
				Inject inject = field.getAnnotation(Inject.class);
				//[5] 如果该字段被标记
				if(inject != null) {
					//[6] 获取需要注入的bean name
					// 要注入的字段需要注入的对象实例
			        Object targetObj = null;
					String needInjectBeanName = inject.name();
					// 该值默认为空，如果它不为空，我们则根据这个自定义的bean name从容器中取
					//[7.1] 处理声明了bean name的情况
					if (!needInjectBeanName.equals("")) {
						// 获取到需要注入对象的全限定类名，注意：如果没有先注册到容器中那么是无法取到的
						String className = customBeanNameQualifiedNameHolder.get(needInjectBeanName);
						// 得到该对象
						targetObj = qualifiedNameRegistedBeanHolder.get(className);
					}
					//[7.2] 处理未声明bean name的情况，此种情况根据注入对象的类类型进行处理
					else {
						// 默认是Class.class，这种情况也就是在字段上声明了一个光秃秃的注解，没有任何参数。那么应该将这个字段注册到IOC容器中
						if (inject.value() == Class.class) {
							targetObj = this.registerBean(field.getType());
						} 
						// 如果指定了类类型
						else {
							// 指定装配的类
							// 检查容器中是否该bean 是否已经注册过，注册过的话直接将需要注入的值进行替换，没有的话则注册到容器中并返回对象实例
							targetObj = this.getBean(inject.value());
							if (null == targetObj) {
								targetObj = this.registerBean(inject.value());
							}
						}
					}
					//[8] 重新设置值
					try {
						// 强制使用反射替换值，替换完成后重新设置原来的访问属性
						boolean accessible = field.isAccessible();
						field.setAccessible(true);
						// 将这个字段重新设置值
						field.set(bean, targetObj);
						field.setAccessible(accessible);
					} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
						// ignored
					}
				}
			}
		}
	}

}
