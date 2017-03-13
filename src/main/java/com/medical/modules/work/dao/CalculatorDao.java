/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.dao;

import com.medical.common.persistence.CrudDao;
import com.medical.common.persistence.annotation.MyBatisDao;
import com.medical.modules.work.entity.Calculator;

/**
 * 物料项目DAO接口
 * @author dyf
 * @version 2017-03-13
 */
@MyBatisDao
public interface CalculatorDao extends CrudDao<Calculator> {
	
}