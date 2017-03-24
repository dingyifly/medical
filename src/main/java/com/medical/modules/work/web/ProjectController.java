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
import com.medical.modules.work.entity.Project;
import com.medical.modules.work.service.ProjectService;

/**
 * 项目管理Controller
 * @author dyf
 * @version 2017-03-24
 */
@Controller
@RequestMapping(value = "${adminPath}/work/project")
public class ProjectController extends BaseController {

	@Autowired
	private ProjectService projectService;
	
	@ModelAttribute
	public Project get(@RequestParam(required=false) String id) {
		Project entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = projectService.get(id);
		}
		if (entity == null){
			entity = new Project();
		}
		return entity;
	}
	
	@RequiresPermissions("work:project:view")
	@RequestMapping(value = {"list", ""})
	public String list(Project project, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Project> page = projectService.findPage(new Page<Project>(request, response), project); 
		model.addAttribute("page", page);
		return "modules/work/projectList";
	}

	@RequiresPermissions("work:project:view")
	@RequestMapping(value = "form")
	public String form(Project project, Model model) {
		model.addAttribute("project", project);
		return "modules/work/projectForm";
	}
	
	@RequiresPermissions("work:project:view")
	@RequestMapping(value = "view")
	public String view(Project project, Model model) {
		model.addAttribute("project", project);
		return "modules/work/projectInfo";
	}

	@RequiresPermissions("work:project:edit")
	@RequestMapping(value = "save")
	public String save(Project project, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, project)){
			return form(project, model);
		}
		projectService.save(project);
		addMessage(redirectAttributes, "保存项目成功");
		return "redirect:"+Global.getAdminPath()+"/work/project/?repage";
	}
	
	@RequiresPermissions("work:project:edit")
	@RequestMapping(value = "delete")
	public String delete(Project project, RedirectAttributes redirectAttributes) {
		projectService.delete(project);
		addMessage(redirectAttributes, "删除项目成功");
		return "redirect:"+Global.getAdminPath()+"/work/project/?repage";
	}

}