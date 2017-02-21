package com.medical.modules.oa.web;

import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.medical.common.web.BaseController;
import com.medical.modules.oa.entity.Leave;
import com.medical.modules.oa.service.VacateService;

@Controller
@RequestMapping(value = "${adminPath}/oa/vacate")
public class VacateController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected VacateService vacateService;
	
	@Autowired
	protected RuntimeService runtimeService;
	
	@Autowired
	protected TaskService taskService;
	
	/**
	 * 启动请假流程
	 * @param leave	
	 */
	@RequiresPermissions("oa:leave:edit")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(Leave leave, RedirectAttributes redirectAttributes) {
		try {
			Map<String, Object> variables = Maps.newHashMap();
			vacateService.save(leave, variables);
			addMessage(redirectAttributes, "流程已启动，流程ID：" + leave.getProcessInstanceId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("启动请假流程失败：", e);
			addMessage(redirectAttributes, "系统内部错误！");
		}
		return "redirect:" + adminPath + "/oa/leave/form";
	}

}
