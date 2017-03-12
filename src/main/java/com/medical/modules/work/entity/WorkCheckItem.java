/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.entity;

import org.hibernate.validator.constraints.Length;

import com.medical.common.persistence.DataEntity;

/**
 * 检测申请Entity
 * @author dyf
 * @version 2017-03-11
 */
public class WorkCheckItem extends DataEntity<WorkCheckItem> {
	
	private static final long serialVersionUID = 1L;
	private WorkCheck check;		// 检测id 父类
	private String dictId;		// 含量类型
	private String num;		// 含量
	
	public WorkCheckItem() {
		super();
	}

	public WorkCheckItem(String id){
		super(id);
	}

	public WorkCheckItem(WorkCheck check){
		this.check = check;
	}

	@Length(min=0, max=64, message="检测id长度必须介于 0 和 64 之间")
	public WorkCheck getCheck() {
		return check;
	}

	public void setCheck(WorkCheck check) {
		this.check = check;
	}
	
	@Length(min=0, max=64, message="含量类型长度必须介于 0 和 64 之间")
	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	
	@Length(min=0, max=64, message="含量长度必须介于 0 和 64 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
}