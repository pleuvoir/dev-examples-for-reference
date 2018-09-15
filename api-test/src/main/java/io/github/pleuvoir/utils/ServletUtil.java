package io.github.pleuvoir.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 获取servlet api <br>
 * 注意：方法的实现基于spring容器
 * @author pleuvoir
 * 
 */
public class ServletUtil {
	
	
	/**
	 * 取得request对象 {@link HttpServletRequest}
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	
	/**
	 * 取得session对象 {@link HttpSession}
	 * @return
	 */
	public static HttpSession getSession(){
		return getRequest().getSession();
	}
	
	
	/**
	 * session中是否存在值   true：存在  false：不存在
	 * @param key 参数key
	 * @return
	 */
	public static boolean existSessionAttr(String key){
		return getSession().getAttribute(key) != null;
	}
	
	
	/**
	 * session中是否存在值   true：不存在  false：存在
	 * @param key 参数key
	 * @return
	 */
	public static boolean notExistSessionAttr(String key){
		return getSession().getAttribute(key) == null;
	}
	
	
	/**
	 * 从session中取值 如果不存在则返回"" <br>
	 * 不建议使用该方法判断值是否在session中存在
	 * @param key 参数key
	 * @return
	 */
	public static String getSessionAttr(String key) {
		return notExistSessionAttr(key) ? 
				StringUtils.EMPTY : String.valueOf(getSession().getAttribute(key));
	}
	
	
	/**
	 * 如果session中不存在该key 则设置值 <br>
	 * 否则不做任何动作
	 * @param key
	 * @param value
	 */
	public static void setSessionAttrNX(String key,String value){
		if(notExistSessionAttr(key)) getSession().setAttribute(key, value);
	}
	
}
