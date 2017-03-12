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
import com.medical.modules.work.entity.WorkCheck;
import com.medical.modules.work.dao.WorkCheckDao;
import com.medical.modules.work.entity.WorkCheckItem;
import com.medical.modules.work.dao.WorkCheckItemDao;

/**
 * 检测申请Service
 * @author dyf
 * @version 2017-03-11
 */
@Service
@Transactional(readOnly = true)
public class WorkCheckService extends CrudService<WorkCheckDao, WorkCheck> {

	@Autowired
	private WorkCheckItemDao workCheckItemDao;
	
	public WorkCheck get(String id) {
		WorkCheck workCheck = super.get(id);
		workCheck.setWorkCheckItemList(workCheckItemDao.findList(new WorkCheckItem(workCheck)));
		return workCheck;
	}
	
	public List<WorkCheck> findList(WorkCheck workCheck) {
		return super.findList(workCheck);
	}
	
	public Page<WorkCheck> findPage(Page<WorkCheck> page, WorkCheck workCheck) {
		return super.findPage(page, workCheck);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkCheck workCheck) {
		if (StringUtils.isBlank(workCheck.getState())) {
			workCheck.setState("0");
			workCheck.setApplyer(workCheck.getCurrentUser());
		}
		super.save(workCheck);
		for (WorkCheckItem workCheckItem : workCheck.getWorkCheckItemList()){
			if (workCheckItem.getId() == null){
				continue;
			}
			if (WorkCheckItem.DEL_FLAG_NORMAL.equals(workCheckItem.getDelFlag())){
				if (StringUtils.isBlank(workCheckItem.getId())){
					workCheckItem.setCheck(workCheck);
					workCheckItem.preInsert();
					workCheckItemDao.insert(workCheckItem);
				}else{
					workCheckItem.preUpdate();
					workCheckItemDao.update(workCheckItem);
				}
			}else{
				workCheckItemDao.delete(workCheckItem);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(WorkCheck workCheck) {
		super.delete(workCheck);
		workCheckItemDao.delete(new WorkCheckItem(workCheck));
	}
	
}