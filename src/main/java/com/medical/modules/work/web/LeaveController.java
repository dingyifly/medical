/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.medical.common.web.BaseController;
import com.medical.common.utils.StringUtils;
import com.medical.modules.work.entity.Leave;
import com.medical.modules.work.service.LeaveService;

/**
 * 请假管理Controller
 * @author dyf
 * @version 2017-03-01
 */
@Controller
@RequestMapping(value = "${adminPath}/work/leave")
public class LeaveController extends BaseController {

	@Autowired
	private LeaveService leaveService;
	
	@ModelAttribute
	public Leave get(@RequestParam(required=false) String id) {
		Leave entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = leaveService.get(id);
		}
		if (entity == null){
			entity = new Leave();
		}
		return entity;
	}
	
	@RequiresPermissions("work:leave:view")
	@RequestMapping(value = {"list", ""})
	public String list(Leave leave, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Leave> page = leaveService.findPage(new Page<Leave>(request, response), leave); 
		model.addAttribute("page", page);
		return "modules/work/leaveList";
	}

	@RequiresPermissions("work:leave:view")
	@RequestMapping(value = "form")
	public String form(Leave leave, Model model) {
		model.addAttribute("leave", leave);
		return "modules/work/leaveForm";
	}
	
	@RequiresPermissions("work:leave:view")
	@RequestMapping(value = "view")
	public String view(Leave leave, Model model) {
		model.addAttribute("leave", leave);
		return "modules/work/leaveInfo";
	}

	@RequiresPermissions("work:leave:edit")
	@RequestMapping(value = "save")
	public String save(Leave leave, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, leave)){
			return form(leave, model);
		}
		leaveService.save(leave);
		addMessage(redirectAttributes, "保存请假成功");
		return "redirect:"+Global.getAdminPath()+"/work/leave/?repage";
	}
	
	@RequiresPermissions("work:leave:edit")
	@RequestMapping(value = "delete")
	public String delete(Leave leave, RedirectAttributes redirectAttributes) {
		leaveService.delete(leave);
		addMessage(redirectAttributes, "删除请假成功");
		return "redirect:"+Global.getAdminPath()+"/work/leave/?repage";
	}

}