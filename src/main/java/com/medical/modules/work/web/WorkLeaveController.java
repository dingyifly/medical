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
import com.medical.common.utils.CommonUtil;
import com.medical.common.utils.StringUtils;
import com.medical.modules.sys.utils.DictUtils;
import com.medical.modules.work.entity.WorkLeave;
import com.medical.modules.work.service.WorkLeaveService;

/**
 * 请假管理Controller
 * @author dyf
 * @version 2017-03-02
 */
@Controller
@RequestMapping(value = "${adminPath}/work/workLeave")
public class WorkLeaveController extends BaseController {

	@Autowired
	private WorkLeaveService workLeaveService;
	
	@ModelAttribute
	public WorkLeave get(@RequestParam(required=false) String id) {
		WorkLeave entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = workLeaveService.get(id);
		}
		if (entity == null){
			entity = new WorkLeave();
		}
		return entity;
	}
	
	@RequiresPermissions("work:workLeave:view")
	@RequestMapping(value = {"list", ""})
	public String list(WorkLeave workLeave, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WorkLeave> page = workLeaveService.findPage(new Page<WorkLeave>(request, response), workLeave); 
		model.addAttribute("page", page);
		return "modules/work/workLeaveList";
	}

	@RequiresPermissions("work:workLeave:view")
	@RequestMapping(value = "form")
	public String form(WorkLeave workLeave, Model model) {
		model.addAttribute("workLeave", workLeave);
		return "modules/work/workLeaveForm";
	}
	
	@RequiresPermissions("work:workLeave:view")
	@RequestMapping(value = "view")
	public String view(WorkLeave workLeave, Model model) {
		model.addAttribute("workLeave", workLeave);
		return "modules/work/workLeaveInfo";
	}

	@RequiresPermissions("work:workLeave:edit")
	@RequestMapping(value = "save")
	public String save(WorkLeave workLeave, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, workLeave)){
			return form(workLeave, model);
		}
		if (CommonUtil.isEmpty(workLeave.getState())) {
			workLeave.setState("0");
		}
		workLeaveService.save(workLeave);
		addMessage(redirectAttributes, "保存请假成功");
		return "redirect:"+Global.getAdminPath()+"/work/workLeave/?repage";
	}
	
	@RequiresPermissions("work:workLeave:edit")
	@RequestMapping(value = "delete")
	public String delete(WorkLeave workLeave, RedirectAttributes redirectAttributes) {
		workLeaveService.delete(workLeave);
		addMessage(redirectAttributes, "删除请假成功");
		return "redirect:"+Global.getAdminPath()+"/work/workLeave/?repage";
	}

}