/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.dao;

import com.medical.common.persistence.CrudDao;
import com.medical.common.persistence.annotation.MyBatisDao;
import com.medical.modules.work.entity.Document;

/**
 * 文档文献管理DAO接口
 * @author dyf
 * @version 2017-02-22
 */
@MyBatisDao
public interface DocumentDao extends CrudDao<Document> {
	
}