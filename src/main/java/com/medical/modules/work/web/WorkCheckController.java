/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medical.common.config.Global;
import com.medical.common.persistence.Page;
import com.medical.common.utils.StringUtils;
import com.medical.common.web.BaseController;
import com.medical.modules.work.entity.WorkCheck;
import com.medical.modules.work.service.WorkCheckService;

/**
 * 检测申请Controller
 * @author dyf
 * @version 2017-03-11
 */
@Controller
@RequestMapping(value = "${adminPath}/work/workCheck")
public class WorkCheckController extends BaseController {

	@Autowired
	private WorkCheckService workCheckService;
	
	@ModelAttribute
	public WorkCheck get(@RequestParam(required=false) String id) {
		WorkCheck entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = workCheckService.get(id);
		}
		if (entity == null){
			entity = new WorkCheck();
		}
		return entity;
	}
	
	@RequiresPermissions("work:workCheck:view")
	@RequestMapping(value = {"list", ""})
	public String list(WorkCheck workCheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WorkCheck> page = workCheckService.findPage(new Page<WorkCheck>(request, response), workCheck); 
		model.addAttribute("page", page);
		return "modules/work/workCheckList";
	}

	@RequiresPermissions("work:workCheck:view")
	@RequestMapping(value = "form")
	public String form(WorkCheck workCheck, Model model) {
		model.addAttribute("workCheck", workCheck);
		return "modules/work/workCheckForm";
	}
	
	@RequiresPermissions("work:workCheck:view")
	@RequestMapping(value = "view")
	public String view(WorkCheck workCheck, Model model) {
		model.addAttribute("workCheck", workCheck);
		return "modules/work/workCheckInfo";
	}

	@RequiresPermissions(value = {"work:workCheck:add", "work:workCheck:edit"}, logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(WorkCheck workCheck, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, workCheck)){
			return form(workCheck, model);
		}
		workCheckService.save(workCheck);
		addMessage(redirectAttributes, "保存检测申请成功");
		return "redirect:"+Global.getAdminPath()+"/work/workCheck/?repage";
	}
	
	@RequiresPermissions("work:workCheck:edit")
	@RequestMapping(value = "delete")
	public String delete(WorkCheck workCheck, RedirectAttributes redirectAttributes) {
		workCheckService.delete(workCheck);
		addMessage(redirectAttributes, "删除检测申请成功");
		return "redirect:"+Global.getAdminPath()+"/work/workCheck/?repage";
	}
	
	@RequestMapping(value = "deal")
	public String deal(WorkCheck workCheck, Model model) {
		model.addAttribute("workCheck", workCheck);
		return "modules/work/workCheckDeal";
	}

}