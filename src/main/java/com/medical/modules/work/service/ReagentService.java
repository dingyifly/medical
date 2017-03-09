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
import com.medical.modules.work.entity.Reagent;
import com.medical.modules.work.dao.ReagentDao;
import com.medical.modules.work.entity.ReagentRecord;
import com.medical.modules.work.dao.ReagentRecordDao;

/**
 * 化学试剂管理Service
 * @author dyf
 * @version 2017-03-02
 */
@Service
@Transactional(readOnly = true)
public class ReagentService extends CrudService<ReagentDao, Reagent> {

	@Autowired
	private ReagentRecordDao reagentRecordDao;
	
	public Reagent get(String id) {
		Reagent reagent = super.get(id);
		reagent.setReagentRecordList(reagentRecordDao.findList(new ReagentRecord(reagent)));
		return reagent;
	}
	
	public List<Reagent> findList(Reagent reagent) {
		return super.findList(reagent);
	}
	
	public Page<Reagent> findPage(Page<Reagent> page, Reagent reagent) {
		return super.findPage(page, reagent);
	}
	
	@Transactional(readOnly = false)
	public void save(Reagent reagent) {
		super.save(reagent);
		for (ReagentRecord reagentRecord : reagent.getReagentRecordList()){
			if (reagentRecord.getId() == null){
				continue;
			}
			if (ReagentRecord.DEL_FLAG_NORMAL.equals(reagentRecord.getDelFlag())){
				if (StringUtils.isBlank(reagentRecord.getId())){
					reagentRecord.setReagent(reagent);
					reagentRecord.preInsert();
					reagentRecordDao.insert(reagentRecord);
				}else{
					reagentRecord.preUpdate();
					reagentRecordDao.update(reagentRecord);
				}
			}else{
				reagentRecordDao.delete(reagentRecord);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void saveRecord(ReagentRecord record) {
		if (StringUtils.isBlank(record.getId())){
			record.preInsert();
			reagentRecordDao.insert(record);
		}else{
			record.preUpdate();
			reagentRecordDao.update(record);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Reagent reagent) {
		super.delete(reagent);
		reagentRecordDao.delete(new ReagentRecord(reagent));
	}
	
}