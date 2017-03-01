/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.dao;

import com.medical.common.persistence.CrudDao;
import com.medical.common.persistence.annotation.MyBatisDao;
import com.medical.modules.work.entity.Interview;

/**
 * 面试管理DAO接口
 * @author dyf
 * @version 2017-02-22
 */
@MyBatisDao
public interface InterviewDao extends CrudDao<Interview> {
	
}