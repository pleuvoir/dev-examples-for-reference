package io.github.pleuvoir.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.pleuvoir.utils.HttpClientUtils;
import io.github.pleuvoir.utils.Md5;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("utils")
public class UtilsController {
	
	@RequestMapping("/reset")
	public @ResponseBody String reset(String mid,String key,String env,String endPoint,HttpSession session){
		session.setAttribute("mid", mid);
		session.setAttribute("key", key);
		session.setAttribute("environment", env);
		session.setAttribute("endPoint", endPoint);
		return StringUtils.EMPTY;
	}

	@RequestMapping("/getSign")
	public @ResponseBody Map<String,String> md5(HttpServletRequest request) {
		String key = request.getParameter("key");
		List<String> sortList = new ArrayList<>();
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			String value = request.getParameter(name);
			if(StringUtils.equals("sign", name) ||StringUtils.equals("key", name) || StringUtils.isBlank(value)){
				continue;
			}
			sortList.add(name + "=" + value);
		}
		Collections.sort(sortList);
		String requestDemo = String.join("&", sortList);
		sortList.add(key);
		String preSign = String.join("&", sortList);
		String genSign = Md5.utf8(preSign);
		Map<String, String> map = new HashMap<>();
		//生成的签名
		map.put("genSign", genSign);
		//请求示例
		map.put("requestDemo", requestDemo + "&sign=" + genSign);
		return map;
	}
	
	@RequestMapping(value = "/redirectPost")
	public void redirectPost(HttpServletRequest req, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> params = new HashMap<String, String>();
		Enumeration<String> names = req.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			if ("requestURL".equals(name) || "key".equals(name)) {
				continue;
			}
			String value = req.getParameter(name);
			params.put(name, value);
		}
		String url = req.getParameter("requestURL").trim();
		String result = null;
		try {
			if (!url.contains("http")) {
				response.getWriter().write("无效的请求地址，请确认！");
				return;
			}
			result = HttpClientUtils.doPost(url, params);
			response.getWriter().write(result);
		} catch (IOException e) {
			log.error("http request error:{}", e);
		}
	}
	
}
