/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.common.persistence.Page;
import com.medical.common.service.CrudService;
import com.medical.modules.work.entity.Document;
import com.medical.modules.work.dao.DocumentDao;

/**
 * 文档文献管理Service
 * @author dyf
 * @version 2017-02-22
 */
@Service
@Transactional(readOnly = true)
public class DocumentService extends CrudService<DocumentDao, Document> {

	public Document get(String id) {
		return super.get(id);
	}
	
	public List<Document> findList(Document document) {
		return super.findList(document);
	}
	
	public Page<Document> findPage(Page<Document> page, Document document) {
		return super.findPage(page, document);
	}
	
	@Transactional(readOnly = false)
	public void save(Document document) {
		super.save(document);
	}
	
	@Transactional(readOnly = false)
	public void delete(Document document) {
		super.delete(document);
	}
	
}