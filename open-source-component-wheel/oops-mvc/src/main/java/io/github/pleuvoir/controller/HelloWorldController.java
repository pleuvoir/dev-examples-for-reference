package io.github.pleuvoir.controller;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import io.github.pleuvoir.payload.annotation.Controller;
import io.github.pleuvoir.payload.annotation.ReponseBody;
import io.github.pleuvoir.payload.annotation.RequestMapping;

@Controller
@RequestMapping(value = "github")
public class HelloWorldController {

	@RequestMapping(value = "/username")
	public void username() {
		System.out.println("进来了，username：pleuvoir");
	}

	@RequestMapping(value = "/password")
	public @ReponseBody String password(HttpServletRequest req) {
		
		StringBuffer sb = new StringBuffer("?");
		Enumeration<String> parameterNames = req.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = (String) parameterNames.nextElement();
			sb.append("&").append(paramName).append("=").append(req.getParameter(paramName));
		}
		if (sb.length() > 1) {
			sb.deleteCharAt(sb.indexOf("&"));
		}
		
		return "password：神奇的密码 " + sb.toString();
	}

}
