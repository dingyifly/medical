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
import com.medical.common.utils.StringUtils;
import com.medical.common.web.BaseController;
import com.medical.modules.work.entity.Reagent;
import com.medical.modules.work.entity.ReagentRecord;
import com.medical.modules.work.service.ReagentService;

/**
 * 化学试剂管理Controller
 * @author dyf
 * @version 2017-03-02
 */
@Controller
@RequestMapping(value = "${adminPath}/work/reagent")
public class ReagentController extends BaseController {

	@Autowired
	private ReagentService reagentService;
	
	@ModelAttribute
	public Reagent get(@RequestParam(required=false) String id) {
		Reagent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = reagentService.get(id);
		}
		if (entity == null){
			entity = new Reagent();
		}
		return entity;
	}
	
	@RequiresPermissions("work:reagent:view")
	@RequestMapping(value = {"list", ""})
	public String list(Reagent reagent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Reagent> page = reagentService.findPage(new Page<Reagent>(request, response), reagent); 
		model.addAttribute("page", page);
		return "modules/work/reagentList";
	}

	@RequiresPermissions("work:reagent:view")
	@RequestMapping(value = "form")
	public String form(Reagent reagent, Model model) {
		model.addAttribute("reagent", reagent);
		return "modules/work/reagentForm";
	}
	
	@RequiresPermissions("work:reagent:view")
	@RequestMapping(value = "view")
	public String view(Reagent reagent, Model model) {
		model.addAttribute("reagent", reagent);
		return "modules/work/reagentInfo";
	}

	@RequiresPermissions("work:reagent:edit")
	@RequestMapping(value = "save")
	public String save(Reagent reagent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, reagent)){
			return form(reagent, model);
		}
		reagentService.save(reagent);
		addMessage(redirectAttributes, "保存化学试剂成功");
		return "redirect:"+Global.getAdminPath()+"/work/reagent/?repage";
	}
	
	@RequiresPermissions("work:reagent:edit")
	@RequestMapping(value = "delete")
	public String delete(Reagent reagent, RedirectAttributes redirectAttributes) {
		reagentService.delete(reagent);
		addMessage(redirectAttributes, "删除化学试剂成功");
		return "redirect:"+Global.getAdminPath()+"/work/reagent/?repage";
	}
	
	@RequiresPermissions("work:reagent:use")
	@RequestMapping(value = "toUse")
	public String toUse(Reagent reagent, ReagentRecord reagentRecord, Model model) {
		reagentRecord.setReagent(reagent);
		model.addAttribute("record", reagentRecord);
		return "modules/work/reagentUse";
	}
	
	@RequiresPermissions("work:reagent:use")
	@RequestMapping(value = "addRecord")
	public String addRecord(ReagentRecord reagentRecord, Model model, RedirectAttributes redirectAttributes) {
		if (StringUtils.isBlank(reagentRecord.getId())) {
			reagentRecord.setState("0");
		}
		reagentRecord.setUser(reagentRecord.getCurrentUser());
		reagentService.saveRecord(reagentRecord);
		addMessage(redirectAttributes, "添加记录成功");
		return "redirect:"+Global.getAdminPath()+"/work/reagent/?repage";
	}
	
	public ReagentRecord getRecord(ReagentRecord reagentRecord) {
		ReagentRecord entity = null;
		String id = reagentRecord.getId();
		if (StringUtils.isNotBlank(id)){
			entity = reagentService.getRecord(reagentRecord);
		}
		if (entity == null){
			entity = new ReagentRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("work:reagent:audit")
	@RequestMapping(value = "auditList")
	public String auditList(ReagentRecord reagentRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReagentRecord> page = reagentService.findAuditPage(new Page<ReagentRecord>(request, response), reagentRecord);
		model.addAttribute("page", page);
		model.addAttribute("reagentRecord", getRecord(reagentRecord));
		return "modules/work/reagentRecordAuditList";
	}
	
}