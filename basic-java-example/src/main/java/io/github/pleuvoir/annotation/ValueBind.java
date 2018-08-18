package io.github.pleuvoir.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueBind {

	enum FieldType {
		STRING, INTEGER, BOOLEAN;
		
		public String value(){
			return this.toString().toLowerCase();
		}
	}

	String value() default "";

	FieldType type();
}
