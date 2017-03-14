package com.medical.modules.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.medical.common.web.BaseController;
import com.medical.modules.sys.entity.Backlog;
import com.medical.modules.sys.utils.UserUtils;
import com.medical.modules.work.entity.Calendar;
import com.medical.modules.work.entity.Interview;
import com.medical.modules.work.entity.Meeting;
import com.medical.modules.work.service.CalendarService;
import com.medical.modules.work.service.InterviewService;
import com.medical.modules.work.service.MeetingService;


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
	
	@RequestMapping(value = {"backlog", ""})
	public String backlog(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Backlog> list = Lists.newArrayList();
		interviewCount(list);
		calendarCount(list);
		meetingCount(list);
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
			in.setOffice(UserUtils.getUser().getOffice());
			in.setState("0");
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

}
