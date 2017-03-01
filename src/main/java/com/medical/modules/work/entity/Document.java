/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.entity;

import org.hibernate.validator.constraints.Length;
import com.medical.modules.sys.entity.Office;

import com.medical.common.persistence.DataEntity;

/**
 * 文档文献管理Entity
 * @author dyf
 * @version 2017-02-22
 */
public class Document extends DataEntity<Document> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 文挡名
	private Office office;		// 部门
	private String type;		// 类别(文档或文献,0\1)
	private String filePath;		// 文件路径
	private String content;		// 文档内容(可选填)
	
	public Document() {
		super();
	}

	public Document(String id){
		super(id);
	}

	@Length(min=0, max=255, message="文挡名长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=1, message="类别(文档或文献,0\1)长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="文件路径长度必须介于 0 和 255 之间")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}