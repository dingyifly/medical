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
import com.medical.modules.sys.utils.UserUtils;
import com.medical.modules.work.entity.Interview;
import com.medical.modules.work.service.InterviewService;

/**
 * 面试管理Controller
 * @author dyf
 * @version 2017-02-22
 */
@Controller
@RequestMapping(value = "${adminPath}/work/interview")
public class InterviewController extends BaseController {

	@Autowired
	private InterviewService interviewService;
	
	@ModelAttribute
	public Interview get(@RequestParam(required=false) String id) {
		Interview entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = interviewService.get(id);
		}
		if (entity == null){
			entity = new Interview();
		}
		return entity;
	}
	
	@RequiresPermissions("work:interview:view")
	@RequestMapping(value = {"list", ""})
	public String list(Interview interview, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (!UserUtils.hasRole(interview.getCurrentUser(), "personnel") && !interview.getCurrentUser().isAdmin()) {
			interview.setOffice(interview.getCurrentUser().getOffice());
		}
		Page<Interview> page = interviewService.findPage(new Page<Interview>(request, response), interview); 
		model.addAttribute("page", page);
		return "modules/work/interviewList";
	}

	@RequiresPermissions("work:interview:view")
	@RequestMapping(value = "form")
	public String form(Interview interview, Model model) {
		model.addAttribute("interview", interview);
		return "modules/work/interviewForm";
	}

	@RequiresPermissions("work:interview:edit")
	@RequestMapping(value = "save")
	public String save(Interview interview, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, interview)){
			return form(interview, model);
		}
		interviewService.save(interview);
		addMessage(redirectAttributes, "保存面试成功");
		return "redirect:"+Global.getAdminPath()+"/work/interview/?repage";
	}
	
	@RequiresPermissions("work:interview:edit")
	@RequestMapping(value = "delete")
	public String delete(Interview interview, RedirectAttributes redirectAttributes) {
		interviewService.delete(interview);
		addMessage(redirectAttributes, "删除面试成功");
		return "redirect:"+Global.getAdminPath()+"/work/interview/?repage";
	}

}