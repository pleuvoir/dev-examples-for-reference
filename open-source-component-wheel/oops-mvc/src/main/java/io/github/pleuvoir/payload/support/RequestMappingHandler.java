package io.github.pleuvoir.payload.support;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.github.pleuvoir.payload.OopsException;
import io.github.pleuvoir.payload.annotation.ReponseBody;

public class RequestMappingHandler {

	public static void handler(String requestPath, HttpServletRequest req, HttpServletResponse rsp) {
		String repairedPath = InitService.repairPath(req.getServletPath());
		System.out.println("修复后路径：" + repairedPath);

		Set<Entry<String, Method>> routers = InitService.getRouters().entrySet();

		routers.forEach(m -> {

			if (m.getKey().equals(repairedPath)) {
				try {
					doHandler(m.getValue(), req, rsp);
				} catch (Throwable e) {
					e.printStackTrace(System.err);
					throw new OopsException("oops exception ", e);
				}
			}
		});

	}
	
	
	private static void doHandler(Method m,HttpServletRequest req, HttpServletResponse rsp) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		if (m.isAnnotationPresent(ReponseBody.class)) {
			Object resultVal = simpleInvoke(m, req, rsp);
			rsp.setCharacterEncoding("UTF-8");
			rsp.setHeader("Content-type", "text/html;charset=UTF-8");
			rsp.getWriter().println(resultVal);
		} else {
			simpleInvoke(m, req, rsp);
		}
	}

	
	/**
	 * 方法调用时进行了三种情况的尝试 ，其他情况不予考虑 <p>
	 * 
	 * 注意：此处的思路是利用反射调用参数类型不匹配时会抛出 IllegalArgumentException 异常，所以可以用于简单处理形参类型不匹配的情况
	 * 对于调用后业务代码出现的异常不予捕获，而是直接抛出，如果在这里 throw e 会丢失原异常堆栈
	 * 此处的实现不严谨，原因是因为业务代码也可能会抛出 IllegalArgumentException 因而会造成反射的多次调用，因而更为合理的做法是判断参数的类型进行处理
	 * 
	 * <ol>
	 * 	<li>无参 </li>
	 * 	<li>HttpServletRequest req</li>
	 * 	<li>HttpServletRequest req, HttpServletResponse rsp</li>
	 * </ol>
	 * 
	 * @return
	 */
	private static Object simpleInvoke(Method m, HttpServletRequest req, HttpServletResponse rsp)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {
		Object resultVal = null;
		try {
			resultVal = m.invoke(m.getDeclaringClass().newInstance());
		} catch (IllegalArgumentException e) {
			try {
				resultVal = m.invoke(m.getDeclaringClass().newInstance(), req);
			} catch (IllegalArgumentException ex) {
				resultVal = m.invoke(m.getDeclaringClass().newInstance(), req, rsp);
			}
		}
		return resultVal;
	}

}
