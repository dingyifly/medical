/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.medical.common.persistence.Page;
import com.medical.common.service.CrudService;
import com.medical.common.utils.CommonUtil;
import com.medical.modules.sys.dao.UserDao;
import com.medical.modules.sys.entity.User;
import com.medical.modules.work.dao.MeetingDao;
import com.medical.modules.work.entity.Meeting;

import freemarker.template.utility.StringUtil;

/**
 * 会议管理Service
 * @author dyf
 * @version 2017-02-28
 */
@Service
@Transactional(readOnly = true)
public class MeetingService extends CrudService<MeetingDao, Meeting> {
	
	@Autowired
	private UserDao userDao;

	public Meeting get(String id) {
		Meeting meeting = super.get(id);
		if (CommonUtil.notEmpty(meeting)) {
			/*List<User> userList = Lists.newArrayList();
			String[] ids = StringUtil.split(meeting.getParticipant(), ',');
			for (String userId : ids) {
				userList.add(userDao.get(userId));
			}
			meeting.setParticipantList(userList);*/
			meeting.setParticipantNames(userDao.idsToNames(meeting.getParticipant()));
		}
		return meeting;
	}
	
	public List<Meeting> findList(Meeting meeting) {
		return super.findList(meeting);
	}
	
	public Page<Meeting> findPage(Page<Meeting> page, Meeting meeting) {
		return super.findPage(page, meeting);
	}
	
	@Transactional(readOnly = false)
	public void save(Meeting meeting) {
		super.save(meeting);
	}
	
	@Transactional(readOnly = false)
	public void delete(Meeting meeting) {
		super.delete(meeting);
	}
	
}