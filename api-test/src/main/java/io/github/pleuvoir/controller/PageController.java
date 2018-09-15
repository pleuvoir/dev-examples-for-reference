package io.github.pleuvoir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class PageController extends BaseController{
	
	@RequestMapping("/")
	public ModelAndView welcome() {
		return new ModelAndView("page/index");
	}
	
	/**
	 * 公共页面
	 */
	@RequestMapping("{className}")
	public ModelAndView route(@PathVariable("className") String className, ModelMap model) {
		model.put("page", getPage(className));
		return new ModelAndView("page/page", model);
	}
	
}
