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
import com.medical.common.utils.CommonUtil;
import com.medical.common.utils.StringUtils;
import com.medical.common.web.BaseController;
import com.medical.modules.work.entity.Reagent;
import com.medical.modules.work.entity.ReagentRecord;
import com.medical.modules.work.service.ReagentRecordService;

/**
 * 化学试剂管理Controller
 * @author dyf
 * @version 2017-03-02
 */
@Controller
@RequestMapping(value = "${adminPath}/work/reagentRecord")
public class ReagentRecordController extends BaseController {

	@Autowired
	private ReagentRecordService reagentRecordService;
	
	@ModelAttribute
	public ReagentRecord get(@RequestParam(required=false) String id) {
		ReagentRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = reagentRecordService.get(id);
		}
		if (entity == null){
			entity = new ReagentRecord();
		}
		return entity;
	}
	
	@RequiresPermissions(value = {"work:reagent:use", "work:reagent:view"}, logical=Logical.OR)
	@RequestMapping(value = {"list", ""})
	public String list(ReagentRecord record, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReagentRecord> page = reagentRecordService.findPage(new Page<ReagentRecord>(request, response), record); 
		model.addAttribute("page", page);
		return "modules/work/reagentRecordList";
	}

	@RequiresPermissions("work:reagent:use")
	@RequestMapping(value = "form")
	public String form(ReagentRecord record, Model model) {
		model.addAttribute("reagentRecord", record);
		return "modules/work/reagentForm";
	}
	
	@RequiresPermissions("work:reagent:use")
	@RequestMapping(value = "view")
	public String view(ReagentRecord record, Model model) {
		model.addAttribute("reagentRecord", record);
		return "modules/work/reagentRecordInfo";
	}

	@RequiresPermissions("work:reagent:use")
	@RequestMapping(value = "save")
	public String save(ReagentRecord record, Model model, RedirectAttributes redirectAttributes) {
		/*if (!beanValidator(model, record)){
			return form(record, model);
		}*/
		if (CommonUtil.isEmpty(record.getState())) {
			record.setState("0");
			record.setUser(record.getCurrentUser());
		}
		reagentRecordService.save(record);
		addMessage(redirectAttributes, "保存记录成功");
		return "redirect:"+Global.getAdminPath()+"/work/reagent/?repage";
	}
	
	@RequiresPermissions("work:reagent:edit")
	@RequestMapping(value = "delete")
	public String delete(ReagentRecord record, RedirectAttributes redirectAttributes) {
		reagentRecordService.delete(record);
		addMessage(redirectAttributes, "删除记录成功");
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
		reagentRecordService.save(reagentRecord);
		addMessage(redirectAttributes, "添加记录成功");
		return "redirect:"+Global.getAdminPath()+"/work/reagent/?repage";
	}
	
	@RequiresPermissions("work:reagent:audit")
	@RequestMapping(value = "auditList")
	public String auditList(ReagentRecord reagentRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReagentRecord> page = reagentRecordService.findAuditPage(new Page<ReagentRecord>(request, response), reagentRecord);
		model.addAttribute("page", page);
		return "modules/work/reagentRecordAuditList";
	}
	
	@RequiresPermissions("work:reagent:use")
	@RequestMapping(value = "toAudit")
	public String toAudit(ReagentRecord reagentRecord, Model model) {
		model.addAttribute("reagentRecord", reagentRecord);
		return "modules/work/reagentAudit";
	}
	
	@RequiresPermissions("work:workLeave:audit")
	@RequestMapping(value = "audit")
	public String audit(ReagentRecord reagentRecord, Model model, RedirectAttributes redirectAttributes) {
		reagentRecordService.save(reagentRecord);
		addMessage(redirectAttributes, "审核记录成功");
		return "redirect:"+Global.getAdminPath()+"/work/reagentRecord/auditList?repage";
	}
	
}