/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.common.persistence.Page;
import com.medical.common.service.CrudService;
import com.medical.modules.work.entity.Leave;
import com.medical.modules.work.dao.LeaveDao;

/**
 * 请假管理Service
 * @author dyf
 * @version 2017-03-01
 */
@Service
@Transactional(readOnly = true)
public class LeaveService extends CrudService<LeaveDao, Leave> {

	public Leave get(String id) {
		return super.get(id);
	}
	
	public List<Leave> findList(Leave leave) {
		return super.findList(leave);
	}
	
	public Page<Leave> findPage(Page<Leave> page, Leave leave) {
		return super.findPage(page, leave);
	}
	
	@Transactional(readOnly = false)
	public void save(Leave leave) {
		super.save(leave);
	}
	
	@Transactional(readOnly = false)
	public void delete(Leave leave) {
		super.delete(leave);
	}
	
}