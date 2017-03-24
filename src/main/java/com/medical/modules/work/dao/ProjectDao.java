/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.dao;

import com.medical.common.persistence.CrudDao;
import com.medical.common.persistence.annotation.MyBatisDao;
import com.medical.modules.work.entity.Project;

/**
 * 项目管理DAO接口
 * @author dyf
 * @version 2017-03-24
 */
@MyBatisDao
public interface ProjectDao extends CrudDao<Project> {
	
}