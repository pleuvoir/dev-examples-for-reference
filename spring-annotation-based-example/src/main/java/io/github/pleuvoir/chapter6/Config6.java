package io.github.pleuvoir.chapter6;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy	// 生成 AnnotationAwareAspectJAutoProxyCreator 代理器，做后置处理
@ComponentScan("io.github.pleuvoir.chapter6")
public class Config6 {

}
