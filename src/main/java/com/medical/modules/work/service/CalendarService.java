/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.common.persistence.Page;
import com.medical.common.service.CrudService;
import com.medical.modules.work.entity.Calendar;
import com.medical.modules.work.dao.CalendarDao;

/**
 * 日程管理Service
 * @author dyf
 * @version 2017-02-22
 */
@Service
@Transactional(readOnly = true)
public class CalendarService extends CrudService<CalendarDao, Calendar> {

	public Calendar get(String id) {
		return super.get(id);
	}
	
	public List<Calendar> findList(Calendar calendar) {
		return super.findList(calendar);
	}
	
	public Page<Calendar> findPage(Page<Calendar> page, Calendar calendar) {
		return super.findPage(page, calendar);
	}
	
	@Transactional(readOnly = false)
	public void save(Calendar calendar) {
		super.save(calendar);
	}
	
	@Transactional(readOnly = false)
	public void delete(Calendar calendar) {
		super.delete(calendar);
	}
	
	public int todoCount(Calendar calendar) {
		return  dao.todoCount(calendar);
	}
	
}