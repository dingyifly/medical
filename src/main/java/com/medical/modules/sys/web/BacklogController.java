package com.medical.modules.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.medical.common.web.BaseController;
import com.medical.modules.sys.entity.Backlog;
import com.medical.modules.sys.security.SecuritySelfUtils;
import com.medical.modules.sys.utils.UserUtils;
import com.medical.modules.work.entity.Calendar;
import com.medical.modules.work.entity.Interview;
import com.medical.modules.work.entity.Meeting;
import com.medical.modules.work.entity.ReagentRecord;
import com.medical.modules.work.entity.WorkLeave;
import com.medical.modules.work.service.CalendarService;
import com.medical.modules.work.service.InterviewService;
import com.medical.modules.work.service.MeetingService;
import com.medical.modules.work.service.ReagentRecordService;
import com.medical.modules.work.service.WorkLeaveService;


/**
 * 待办Controller
 * @author dyf
 * @version 2017-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/backlog")
public class BacklogController extends BaseController {
	
	@Autowired
	InterviewService interviewService;
	@Autowired
	CalendarService calendarService;
	@Autowired
	MeetingService meetingService;
	@Autowired
	ReagentRecordService reagentRecordService;
	@Autowired
	WorkLeaveService workLeaveService;
	
	@RequestMapping(value = {"backlog", ""})
	public String backlog(HttpServletRequest request, HttpServletResponse response, Model model) {
		if ("1".equals(UserUtils.getUser().getId())) return "modules/sys/backlogList";
		List<Backlog> list = Lists.newArrayList();
//		SecurityUtils.getSubject().isPermitted("");
		if (SecuritySelfUtils.hasAnyPermission("work:interview:handle,work:interview:edit")) {
			interviewCount(list);
		}
		calendarCount(list);
		meetingCount(list);
		if (SecuritySelfUtils.hasAnyPermission("work:reagent:audit")) {
			reagentCount(list);
		}
		leaveCount(list);
		model.addAttribute("backlogList", list);
		return "modules/sys/backlogList";
	}

	/**
	 * 待办面试
	 * @param list
	 */
	private void interviewCount(List<Backlog> list) {
		if (UserUtils.hasRole(UserUtils.getUser(), "dmanager")) {
			Interview in = new Interview();
			if (!UserUtils.hasRole(UserUtils.getUser(), "hr-manager")) {
				in.setOffice(UserUtils.getUser().getOffice());
				in.setState("0");
			} else {
				in.setState("1");
			}
			int num = interviewService.getToDoCount(in);
			if (num != 0) {
				Backlog l = new Backlog(num, "待面试", "work/interview/");
				list.add(l);
			}
		}
	}
	
	/**
	 * 待办日程
	 * @param list
	 */
	private void calendarCount(List<Backlog> list) {
		int num = calendarService.todoCount(new Calendar());
		if (num != 0) {
			Backlog l = new Backlog(num, "待办日程", "work/calendar/");
			list.add(l);
		}
	}
	
	/**
	 * 待办会议
	 * @param list
	 */
	private void meetingCount(List<Backlog> list) {
		int num = meetingService.todoCount(new Meeting());
		if (num != 0) {
			Backlog l = new Backlog(num, "待办会议", "work/meeting/");
			list.add(l);
		}
	}
	
	/**
	 * 化学试剂审核
	 * @param list
	 */
	private void reagentCount(List<Backlog> list) {
		int num = reagentRecordService.todoCount(new ReagentRecord());
		if (num != 0) {
			Backlog l = new Backlog(num, "试剂使用审核", "work/meeting/");
			list.add(l);
		}
	}
	
	/**
	 * 请假待审核
	 * @param list
	 */
	private void leaveCount(List<Backlog> list) {
		int num = workLeaveService.todoCount(new WorkLeave());
		if (num != 0) {
			Backlog l = new Backlog(num, "请假审核", "work/workLeave/auditList");
			list.add(l);
		}
	}

}
