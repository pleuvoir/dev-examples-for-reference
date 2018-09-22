package io.github.pleuvoir.chapter6.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

	
	// 如果正常  	 【around before】 -> 【aop before】 -> 执行目标方法 -> 【around after】 -> 【aop after】-> 【aop afterReturning】
	
	// 如果有异常	 【around before】 -> 【aop before】 -> 执行目标方法 -> 【aop after】-> 【aop afterThrowing】
	
	
	// 定义切入点，简化代码冗余
	@Pointcut("execution(public * io.github.pleuvoir.chapter6.service.*.*(..))")
	public void pointCut(){}
	
	//@before代表在目标方法执行前切入, 并指定在哪个方法前切入
	@Before("pointCut()")
	public void before(JoinPoint joinPoint){
		System.out.println("【aop before】  " + joinPoint.getSignature().getName() + "方法运行，参数：" + Arrays.asList(joinPoint.getArgs()));
	}
	
	@After("pointCut()")
	public void after(JoinPoint joinPoint){
		System.out.println("【aop after】  " + joinPoint.getSignature().getName() + "方法结束!");
	}
	
	@AfterReturning(value = "pointCut()", returning = "result")
	public void afterReturning(Object result) {
		System.out.println("【aop afterReturning】  " + "方法正常返回，运行结果：[" + result + "]");
	}
	
	@AfterThrowing(value="pointCut()",throwing="exception")
	public void afterThrowing(Exception exception){
		System.out.println("【aop afterThrowing】  " + "运行异常，异常信息：[" + exception + "]");
	}
	
	// 环绕通知，正常情况下 上面 4个 方法已经足够使用
	@Around("pointCut()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("【around before】  执行目标方法之前");
		Object obj = proceedingJoinPoint.proceed();// 相当于手动调用业务方法
		System.out.println("【around after】    执行目标方法之后");
		return obj;
	}
}
