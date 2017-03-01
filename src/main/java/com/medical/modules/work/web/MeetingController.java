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
import com.medical.modules.work.entity.Meeting;
import com.medical.modules.work.service.MeetingService;

/**
 * 会议管理Controller
 * @author dyf
 * @version 2017-02-28
 */
@Controller
@RequestMapping(value = "${adminPath}/work/meeting")
public class MeetingController extends BaseController {

	@Autowired
	private MeetingService meetingService;
	
	@ModelAttribute
	public Meeting get(@RequestParam(required=false) String id) {
		Meeting entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = meetingService.get(id);
		}
		if (entity == null){
			entity = new Meeting();
		}
		return entity;
	}
	
	@RequiresPermissions("work:meeting:view")
	@RequestMapping(value = {"list", ""})
	public String list(Meeting meeting, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Meeting> page = meetingService.findPage(new Page<Meeting>(request, response), meeting); 
		model.addAttribute("page", page);
		return "modules/work/meetingList";
	}

	@RequiresPermissions("work:meeting:view")
	@RequestMapping(value = "form")
	public String form(Meeting meeting, Model model) {
		model.addAttribute("meeting", meeting);
		return "modules/work/meetingForm";
	}
	
	@RequiresPermissions("work:meeting:view")
	@RequestMapping(value = "view")
	public String view(Meeting meeting, Model model) {
		model.addAttribute("meeting", meeting);
		return "modules/work/meetingInfo";
	}

	@RequiresPermissions("work:meeting:edit")
	@RequestMapping(value = "save")
	public String save(Meeting meeting, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, meeting)){
			return form(meeting, model);
		}
		meetingService.save(meeting);
		addMessage(redirectAttributes, "保存会议成功");
		return "redirect:"+Global.getAdminPath()+"/work/meeting/?repage";
	}
	
	@RequiresPermissions("work:meeting:edit")
	@RequestMapping(value = "delete")
	public String delete(Meeting meeting, RedirectAttributes redirectAttributes) {
		meetingService.delete(meeting);
		addMessage(redirectAttributes, "删除会议成功");
		return "redirect:"+Global.getAdminPath()+"/work/meeting/?repage";
	}

}