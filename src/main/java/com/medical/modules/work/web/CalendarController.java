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
import com.medical.modules.work.entity.Calendar;
import com.medical.modules.work.service.CalendarService;

/**
 * 日程管理Controller
 * @author dyf
 * @version 2017-02-22
 */
@Controller
@RequestMapping(value = "${adminPath}/work/calendar")
public class CalendarController extends BaseController {

	@Autowired
	private CalendarService calendarService;
	
	@ModelAttribute
	public Calendar get(@RequestParam(required=false) String id) {
		Calendar entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = calendarService.get(id);
		}
		if (entity == null){
			entity = new Calendar();
		}
		return entity;
	}
	
	@RequiresPermissions("work:calendar:view")
	@RequestMapping(value = {"list", ""})
	public String list(Calendar calendar, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Calendar> page = calendarService.findPage(new Page<Calendar>(request, response), calendar); 
		model.addAttribute("page", page);
		return "modules/work/calendarList";
	}

	@RequiresPermissions("work:calendar:view")
	@RequestMapping(value = "form")
	public String form(Calendar calendar, Model model) {
		model.addAttribute("calendar", calendar);
		return "modules/work/calendarForm";
	}

	@RequiresPermissions("work:calendar:edit")
	@RequestMapping(value = "save")
	public String save(Calendar calendar, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, calendar)){
			return form(calendar, model);
		}
		calendarService.save(calendar);
		addMessage(redirectAttributes, "保存日程成功");
		return "redirect:"+Global.getAdminPath()+"/work/calendar/?repage";
	}
	
	@RequiresPermissions("work:calendar:edit")
	@RequestMapping(value = "delete")
	public String delete(Calendar calendar, RedirectAttributes redirectAttributes) {
		calendarService.delete(calendar);
		addMessage(redirectAttributes, "删除日程成功");
		return "redirect:"+Global.getAdminPath()+"/work/calendar/?repage";
	}

}