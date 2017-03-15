/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.common.persistence.Page;
import com.medical.common.service.CrudService;
import com.medical.common.utils.StringUtils;
import com.medical.modules.sys.utils.UserUtils;
import com.medical.modules.work.dao.ReagentDao;
import com.medical.modules.work.dao.ReagentRecordDao;
import com.medical.modules.work.entity.Reagent;
import com.medical.modules.work.entity.ReagentRecord;

/**
 * 化学试剂管理Service
 * @author dyf
 * @version 2017-03-02
 */
@Service
@Transactional(readOnly = true)
public class ReagentRecordService extends CrudService<ReagentRecordDao, ReagentRecord> {

	@Autowired
	private ReagentDao reagentDao;
	
	public ReagentRecord get(String id) {
		ReagentRecord record = super.get(id);
		return record;
	}
	
	public List<ReagentRecord> findList(ReagentRecord record) {
		return super.findList(record);
	}
	
	public Page<ReagentRecord> findPage(Page<ReagentRecord> page, ReagentRecord record) {
		if(!record.getCurrentUser().isAdmin()) {
			record.setUser(record.getCurrentUser());
		}
		return super.findPage(page, record);
	}
	
	@Transactional(readOnly = false)
	public void delete(ReagentRecord record) {
		super.delete(record);
	}
	
	@Transactional(readOnly = false)
	public void save(ReagentRecord record) {
		if (StringUtils.isBlank(record.getId())){
			record.preInsert();
			dao.insert(record);
		}else{
			record.preUpdate();
			dao.update(record);
			if ("2".equals(record.getState())) {
				Reagent reagent = reagentDao.get(record.getReagent());
				String num = reagent.getRepertory();
				num = String.valueOf(Integer.parseInt(num) + ("0".equals(record.getUseFlag()) ? -1 * Integer.parseInt(record.getNum()) : Integer.parseInt(record.getNum())));
				reagent.setRepertory(num);
				reagentDao.update(reagent);
			}
		}
	}
	
	
	public Page<ReagentRecord> findAuditPage(Page<ReagentRecord> page, ReagentRecord record) {
		String flag = null;
		if (!UserUtils.hasRole(record.getCurrentUser(), "reagent-manager") 
				&& !record.getCurrentUser().isAdmin()) {
			flag = "1";
		}
		page.setList(dao.findAuditList(record, flag));
		return page;
	}
	
	public int todoCount(ReagentRecord record) {
		String flag = null;
		if (!UserUtils.hasRole(record.getCurrentUser(), "reagent-manager") 
				&& !record.getCurrentUser().isAdmin()) {
			flag = "1";
		}
		return dao.todoCount(record, flag);
	}
	
}