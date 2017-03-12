/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.dao;

import com.medical.common.persistence.CrudDao;
import com.medical.common.persistence.annotation.MyBatisDao;
import com.medical.modules.work.entity.WorkCheck;

/**
 * 检测申请DAO接口
 * @author dyf
 * @version 2017-03-11
 */
@MyBatisDao
public interface WorkCheckDao extends CrudDao<WorkCheck> {
	
}