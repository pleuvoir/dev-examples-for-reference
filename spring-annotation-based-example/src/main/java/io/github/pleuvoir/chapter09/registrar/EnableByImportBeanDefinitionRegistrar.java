package io.github.pleuvoir.chapter09.registrar;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ByImportBeanDefinitionRegistrar.class)
public @interface EnableByImportBeanDefinitionRegistrar {


	String name() default "我是注解的值";

}
