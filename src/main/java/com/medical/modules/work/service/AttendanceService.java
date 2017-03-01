/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.common.persistence.Page;
import com.medical.common.service.CrudService;
import com.medical.modules.work.entity.Attendance;
import com.medical.modules.work.dao.AttendanceDao;

/**
 * 考勤表Service
 * @author dyf
 * @version 2017-02-22
 */
@Service
@Transactional(readOnly = true)
public class AttendanceService extends CrudService<AttendanceDao, Attendance> {

	public Attendance get(String id) {
		return super.get(id);
	}
	
	public List<Attendance> findList(Attendance attendance) {
		return super.findList(attendance);
	}
	
	public Page<Attendance> findPage(Page<Attendance> page, Attendance attendance) {
		return super.findPage(page, attendance);
	}
	
	@Transactional(readOnly = false)
	public void save(Attendance attendance) {
		super.save(attendance);
	}
	
	@Transactional(readOnly = false)
	public void delete(Attendance attendance) {
		super.delete(attendance);
	}
	
}