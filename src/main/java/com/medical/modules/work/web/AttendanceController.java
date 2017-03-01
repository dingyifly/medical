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
import com.medical.modules.work.entity.Attendance;
import com.medical.modules.work.service.AttendanceService;

/**
 * 考勤表Controller
 * @author dyf
 * @version 2017-02-22
 */
@Controller
@RequestMapping(value = "${adminPath}/work/attendance")
public class AttendanceController extends BaseController {

	@Autowired
	private AttendanceService attendanceService;
	
	@ModelAttribute
	public Attendance get(@RequestParam(required=false) String id) {
		Attendance entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = attendanceService.get(id);
		}
		if (entity == null){
			entity = new Attendance();
		}
		return entity;
	}
	
	@RequiresPermissions("work:attendance:view")
	@RequestMapping(value = {"list", ""})
	public String list(Attendance attendance, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Attendance> page = attendanceService.findPage(new Page<Attendance>(request, response), attendance); 
		model.addAttribute("page", page);
		return "modules/work/attendanceList";
	}

	@RequiresPermissions("work:attendance:view")
	@RequestMapping(value = "form")
	public String form(Attendance attendance, Model model) {
		model.addAttribute("attendance", attendance);
		return "modules/work/attendanceForm";
	}

	@RequiresPermissions("work:attendance:edit")
	@RequestMapping(value = "save")
	public String save(Attendance attendance, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, attendance)){
			return form(attendance, model);
		}
		attendanceService.save(attendance);
		addMessage(redirectAttributes, "保存考勤表成功");
		return "redirect:"+Global.getAdminPath()+"/work/attendance/?repage";
	}
	
	@RequiresPermissions("work:attendance:edit")
	@RequestMapping(value = "delete")
	public String delete(Attendance attendance, RedirectAttributes redirectAttributes) {
		attendanceService.delete(attendance);
		addMessage(redirectAttributes, "删除考勤表成功");
		return "redirect:"+Global.getAdminPath()+"/work/attendance/?repage";
	}

}