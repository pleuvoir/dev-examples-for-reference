package io.github.pleuvoir.controller;

import java.lang.reflect.Method;
import java.util.Set;
import org.reflections.Reflections;
import io.github.pleuvoir.annotation.Controller;
import io.github.pleuvoir.annotation.RequestMapping;

@Controller
@RequestMapping(value = "github")
public class HelloWorldController {

	@RequestMapping(value = "/username")
	public void username(){
		
	}
	
	
	@RequestMapping(value = "/password")
	public void password(){
		
	}
	
	
	// https://blog.csdn.net/hfreeman2008/article/details/49027247
	public static void main(String[] args) {
		Reflections reflections = new Reflections("io.github.pleuvoir.controller");
		
		
	//	Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Controller.class);
		
		Set<Method> methodsAnnotatedWith = reflections.getMethodsAnnotatedWith(RequestMapping.class);
		for (Method method : methodsAnnotatedWith) {
			System.out.println(method.getName());
		}
		
//		for (Class<?> class1 : typesAnnotatedWith) {
//			
//			System.out.println(class1.getName());
//		}
		
	}
}
