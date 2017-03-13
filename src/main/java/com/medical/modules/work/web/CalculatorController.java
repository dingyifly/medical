/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medical.common.config.Global;
import com.medical.common.persistence.Page;
import com.medical.common.utils.StringUtils;
import com.medical.common.web.BaseController;
import com.medical.modules.work.entity.Calculator;
import com.medical.modules.work.entity.CalculatorItem;
import com.medical.modules.work.service.CalculatorService;

/**
 * 物料项目Controller
 * @author dyf
 * @version 2017-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/work/calculator")
public class CalculatorController extends BaseController {

	@Autowired
	private CalculatorService calculatorService;
	
	@ModelAttribute
	public Calculator get(@RequestParam(required=false) String id) {
		Calculator entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = calculatorService.get(id);
		}
		if (entity == null){
			entity = new Calculator();
		}
		return entity;
	}
	
	@RequiresPermissions("work:calculator:view")
	@RequestMapping(value = {"list", ""})
	public String list(Calculator calculator, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Calculator> page = calculatorService.findPage(new Page<Calculator>(request, response), calculator); 
		model.addAttribute("page", page);
		return "modules/work/calculatorList";
	}

	@RequiresPermissions("work:calculator:view")
	@RequestMapping(value = "form")
	public String form(Calculator calculator, Model model) {
		model.addAttribute("calculator", calculator);
		return "modules/work/calculatorForm";
	}
	
	@RequiresPermissions("work:calculator:view")
	@RequestMapping(value = "view")
	public String view(Calculator calculator, Model model) {
		model.addAttribute("calculator", calculator);
		return "modules/work/calculatorInfo";
	}

	@RequiresPermissions("work:calculator:edit")
	@RequestMapping(value = "save")
	public String save(Calculator calculator, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, calculator)){
			return form(calculator, model);
		}
		calculatorService.save(calculator);
		addMessage(redirectAttributes, "保存物料项目成功");
		return "redirect:"+Global.getAdminPath()+"/work/calculator/?repage";
	}
	
	@RequiresPermissions("work:calculator:edit")
	@RequestMapping(value = "delete")
	public String delete(Calculator calculator, RedirectAttributes redirectAttributes) {
		calculatorService.delete(calculator);
		addMessage(redirectAttributes, "删除物料项目成功");
		return "redirect:"+Global.getAdminPath()+"/work/calculator/?repage";
	}
	
	@RequiresPermissions("work:calculator:view")
	@RequestMapping(value = "getItems")
	@ResponseBody
	public List<CalculatorItem> getItems(Calculator calculator, Model model) {
//		model.addAttribute("calculator", calculator);
		return calculator.getCalculatorItemList();
	}
	
	@RequestMapping(value = "counter")
	public String counter(Model model) {
		List<Calculator> list = calculatorService.findList(new Calculator());
		model.addAttribute("list", list);
		return "modules/work/counter";
	}

}