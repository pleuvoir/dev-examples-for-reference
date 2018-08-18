package io.github.pleuvoir.service;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "oops-mvc", urlPatterns = "/", loadOnStartup = 1, 
		initParams = {
			@WebInitParam(name = "username", value = "pleuvoir"), 
			@WebInitParam(name = "password", value = "70B12FDBBEDC574F753778B659F12BC1") 
			})
public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = -7523435686772603607L;
	
	

	@Override
	public void init(ServletConfig config) throws ServletException {
	
		
		super.init(config);
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(req.getAttribute("test"));
		super.doGet(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

	
}
