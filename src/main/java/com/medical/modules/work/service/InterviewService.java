/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.common.persistence.Page;
import com.medical.common.service.CrudService;
import com.medical.modules.work.entity.Interview;
import com.medical.modules.work.dao.InterviewDao;

/**
 * 面试管理Service
 * @author dyf
 * @version 2017-02-22
 */
@Service
@Transactional(readOnly = true)
public class InterviewService extends CrudService<InterviewDao, Interview> {

	public Interview get(String id) {
		return super.get(id);
	}
	
	public List<Interview> findList(Interview interview) {
		return super.findList(interview);
	}
	
	public Page<Interview> findPage(Page<Interview> page, Interview interview) {
		return super.findPage(page, interview);
	}
	
	@Transactional(readOnly = false)
	public void save(Interview interview) {
		super.save(interview);
	}
	
	@Transactional(readOnly = false)
	public void delete(Interview interview) {
		super.delete(interview);
	}
	
}