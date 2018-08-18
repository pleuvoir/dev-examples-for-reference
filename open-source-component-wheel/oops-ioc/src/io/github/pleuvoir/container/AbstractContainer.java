package io.github.pleuvoir.container;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public abstract class AbstractContainer implements Container {
	
	/**
	 * 声明一个map，用于保存所有的bean对象   格式为xxx.JDPayService=xxx.JDPayService@7b3300e5
	 */
	protected Map<String, Object> qualifiedNameRegistedBeanHolder;
	
	/**
	 * 声明一个map，用于保存bean自定义名称与全限定类名的对应关系
	 */
	protected Map<String,String> customBeanNameQualifiedNameHolder;
	
	public AbstractContainer() {
		this.qualifiedNameRegistedBeanHolder 	= new ConcurrentHashMap<>();
		this.customBeanNameQualifiedNameHolder = new ConcurrentHashMap<>();
	}

	@Override
	public <T> T getBean(Class<T> clazz) {
		String name = clazz.getName();
		Object object = qualifiedNameRegistedBeanHolder.get(name);
		return object == null ? null : (T) object;
	}

	@Override
	public <T> T getBeanByName(String name) {
		//获取全限定类名
		String qualifiedName = customBeanNameQualifiedNameHolder.get(name);
		Object object = qualifiedNameRegistedBeanHolder.get(qualifiedName);
		return object == null ? null : (T) object;
	}

	@Override
	public Object registerBean(Object bean) {
		//获取全限定类名
		String name = bean.getClass().getName();
		qualifiedNameRegistedBeanHolder.put(name, bean);
		//这种注册方式因为没有传入自定义的名称，那么默认以对象的全限定类名作为自定义的bean name
		customBeanNameQualifiedNameHolder.put(name, name);
		return bean;
	}

	@Override
	public Object registerBean(Class<?> clazz) {
		//获取全限定类名
		String className = clazz.getName();
		//反射生成对象
		Object obj = null;
		try {
			obj = Class.forName(className).newInstance();
		} catch (Exception e) {
			// ignored
		}
		qualifiedNameRegistedBeanHolder.put(className, obj);
		//这种注册方式因为没有传入自定义的名称，那么默认以对象的全限定类名作为自定义的bean name
		customBeanNameQualifiedNameHolder.put(className, className);
		return obj;
	}

	@Override
	public Object registerBean(String name, Object bean) {
		//获取全限定类名
		String className = bean.getClass().getName();
		customBeanNameQualifiedNameHolder.put(name, className);
		qualifiedNameRegistedBeanHolder.put(className, bean);
		return bean;
	}

	//这两个基本没什么用
	@Override
	public void remove(Class<?> clazz) {
		//获取全限定类名
		String className = clazz.getName();
		qualifiedNameRegistedBeanHolder.remove(className);
		//如果注册的时候是以 #registerBean(Class<?>) 这种方式注册的，那么这里移除关系也是合理的
		customBeanNameQualifiedNameHolder.remove(className);
	}

	//这两个基本没什么用
	@Override
	public void removeByName(String name) {
		//先获取全限定类名
		String className = customBeanNameQualifiedNameHolder.get(name);
		qualifiedNameRegistedBeanHolder.remove(className);
		customBeanNameQualifiedNameHolder.remove(name);
	}

	@Override
	public Set<String> getBeanNames() {
		return customBeanNameQualifiedNameHolder.keySet();
	}

}
