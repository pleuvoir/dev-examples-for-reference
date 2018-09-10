package cn.com.cicpay.leopard.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import cn.com.cicpay.leopard.model.hbm.${entityName};
import cn.com.cicpay.leopard.service.${entityName}Service;
import cn.com.cicpay.leopard.exception.BusinessException;
import cn.com.cicpay.leopard.model.Message;
import cn.com.cicpay.smart.pos.permission.helper.CheckPermission;

@Controller
@RequestMapping("${entityName?uncap_first}")
@CheckPermission("${entityName?uncap_first}")
public class ${entityName}Controller {
	
	@Autowired
	private ${entityName}Service ${entityName?uncap_first}Service;
	

	@RequestMapping("list")
	@CheckPermission("list")
	public ModelAndView list(){
		ModelAndView v = new ModelAndView("main","_view","${entityName?uncap_first}/list");
		return v;
	}
	
	
	@RequestMapping("/query")
	@CheckPermission("list")
	public ModelAndView query(${entityName} ${entityName?uncap_first},Integer page,Integer rows){
		ModelAndView view = list();
		view.addObject("datagrid", ${entityName?uncap_first}Service.getDataGrid(${entityName?uncap_first}, page, rows));
		return view;		
	}
	
	
	@RequestMapping("add")
	@CheckPermission("add")
	public ModelAndView add(){
		return new ModelAndView("main","_view","${entityName?uncap_first}/add");
	}
	
	
	@RequestMapping("save")
	@CheckPermission("add")
	public String save(${entityName} ${entityName?uncap_first},HttpServletRequest request,RedirectAttributes redirectAttributes){
		String result = InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/${entityName?uncap_first}/list";
		try {
			${entityName?uncap_first}Service.save(${entityName?uncap_first});
			redirectAttributes.addFlashAttribute(Message.SUCCESS, "添加成功");
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute(Message.ERROR, e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("edit")
	@CheckPermission("update")
	public ModelAndView edit(<#list columnExtendList as columnExtend><#if "${primaryKey}"=="${columnExtend.field}">${columnExtend.convertedType}</#if></#list> ${primaryKey}){
		ModelAndView view =new ModelAndView("main","_view","${entityName?uncap_first}/edit");
		view.addObject("${entityName?uncap_first}", ${entityName?uncap_first}Service.get${entityName}By${primaryKey?cap_first}(${primaryKey}));
		return view;
	}
	
	
	@RequestMapping("update")
	@CheckPermission("update")
	public String update(${entityName} ${entityName?uncap_first}Form,RedirectAttributes redirectAttributes){
		String result = InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/${entityName?uncap_first}/list";
		try {
			${entityName?uncap_first}Service.update(${entityName?uncap_first}Form);
			redirectAttributes.addFlashAttribute(Message.SUCCESS, "修改成功");
		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute(Message.ERROR, "修改失败");
		}
		return result;
	}
	
	
	@RequestMapping("detail")
	@CheckPermission("list")
	public ModelAndView detail(<#list columnExtendList as columnExtend><#if "${primaryKey}"=="${columnExtend.field}">${columnExtend.convertedType}</#if></#list> ${primaryKey}){
		ModelAndView view =new ModelAndView("main","_view","${entityName?uncap_first}/detail");
		view.addObject("detail", ${entityName?uncap_first}Service.get${entityName}By${primaryKey?cap_first}(${primaryKey}));
		return view;
	}
}
