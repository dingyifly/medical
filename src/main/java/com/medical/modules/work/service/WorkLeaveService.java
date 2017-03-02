/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.common.persistence.Page;
import com.medical.common.service.CrudService;
import com.medical.modules.work.entity.WorkLeave;
import com.medical.modules.work.dao.WorkLeaveDao;

/**
 * 请假管理Service
 * @author dyf
 * @version 2017-03-02
 */
@Service
@Transactional(readOnly = true)
public class WorkLeaveService extends CrudService<WorkLeaveDao, WorkLeave> {

	public WorkLeave get(String id) {
		return super.get(id);
	}
	
	public List<WorkLeave> findList(WorkLeave workLeave) {
		return super.findList(workLeave);
	}
	
	public Page<WorkLeave> findPage(Page<WorkLeave> page, WorkLeave workLeave) {
		return super.findPage(page, workLeave);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkLeave workLeave) {
		super.save(workLeave);
	}
	
	@Transactional(readOnly = false)
	public void delete(WorkLeave workLeave) {
		super.delete(workLeave);
	}
	
}