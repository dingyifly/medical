package com.medical.modules.oa.dao;

import com.medical.common.persistence.CrudDao;
import com.medical.common.persistence.annotation.MyBatisDao;
import com.medical.modules.oa.entity.Leave;

@MyBatisDao
public interface VacateDao extends CrudDao<Leave> {

	void updateProcessInstanceId(Leave leave);

}
