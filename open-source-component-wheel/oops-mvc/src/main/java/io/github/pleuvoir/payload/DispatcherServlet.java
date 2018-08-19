package io.github.pleuvoir.payload;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.github.pleuvoir.payload.support.InitService;
import io.github.pleuvoir.payload.support.RequestMappingHandler;

@WebServlet(name = "oops-mvc", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = -7523435686772603607L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		InitService.scan("io.github.pleuvoir.controller");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {

		System.out.println("项目路径：" + req.getRequestURI() + " 请求路径：" + req.getServletPath());

		Enumeration<String> parameterNames = req.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = (String) parameterNames.nextElement();
			System.out.println("param：" + paramName + " value：" + req.getParameter(paramName));
		}

		RequestMappingHandler.handler(req.getServletPath(), req, rsp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}
