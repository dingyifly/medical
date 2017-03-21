/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.dao;

import java.util.List;

import com.medical.common.persistence.CrudDao;
import com.medical.common.persistence.Page;
import com.medical.common.persistence.annotation.MyBatisDao;
import com.medical.modules.work.entity.WorkLeave;

/**
 * 请假管理DAO接口
 * @author dyf
 * @version 2017-03-02
 */
@MyBatisDao
public interface WorkLeaveDao extends CrudDao<WorkLeave> {

	/**
	 * 待审核列表（经理审核）
	 * @param workLeave
	 * @return
	 */
	List<WorkLeave> findManagerAuditList(WorkLeave workLeave);

	/**
	 * 待审核列表（部门负责人审核）
	 * @param workLeave
	 * @return
	 */
	List<WorkLeave> findAuditList(WorkLeave workLeave);
	
	/**
	 * 待审核个数（经理审核）
	 * @param workLeave
	 * @return
	 */
	int findManagerAuditCount(WorkLeave workLeave);
	
	/**
	 * 待审核个数（部门负责人审核）
	 * @param workLeave
	 * @return
	 */
	int findAuditCount(WorkLeave workLeave);
	
}