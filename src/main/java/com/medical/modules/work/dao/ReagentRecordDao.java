/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.medical.common.persistence.CrudDao;
import com.medical.common.persistence.annotation.MyBatisDao;
import com.medical.modules.work.entity.ReagentRecord;

/**
 * 化学试剂管理DAO接口
 * @author dyf
 * @version 2017-03-02
 */
@MyBatisDao
public interface ReagentRecordDao extends CrudDao<ReagentRecord> {

	/**
	 * 查询审核列表
	 * @param record
	 * @param flag 如果为“1”表示部门主管，其他为管理员或
	 * @return
	 */
	List<ReagentRecord> findAuditList(@Param("record")ReagentRecord record, @Param("flag")String flag);
	
	int findAuditCount(ReagentRecord record, @Param("flag")String flag);
	
}