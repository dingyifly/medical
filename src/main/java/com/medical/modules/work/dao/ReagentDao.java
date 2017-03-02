/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.dao;

import com.medical.common.persistence.CrudDao;
import com.medical.common.persistence.annotation.MyBatisDao;
import com.medical.modules.work.entity.Reagent;

/**
 * 化学试剂管理DAO接口
 * @author dyf
 * @version 2017-03-02
 */
@MyBatisDao
public interface ReagentDao extends CrudDao<Reagent> {
	
}