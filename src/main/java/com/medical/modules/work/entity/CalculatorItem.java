/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.entity;

import org.hibernate.validator.constraints.Length;

import com.medical.common.persistence.DataEntity;

/**
 * 物料项目Entity
 * @author dyf
 * @version 2017-03-13
 */
public class CalculatorItem extends DataEntity<CalculatorItem> {
	
	private static final long serialVersionUID = 1L;
	private Calculator project;		// 项目id 父类
	private String name;		// 物料名称
	private String num;		// 物料量
	
	public CalculatorItem() {
		super();
	}

	public CalculatorItem(String id){
		super(id);
	}

	public CalculatorItem(Calculator project){
		this.project = project;
	}

	@Length(min=0, max=64, message="项目id长度必须介于 0 和 64 之间")
	public Calculator getProject() {
		return project;
	}

	public void setProject(Calculator project) {
		this.project = project;
	}
	
	@Length(min=0, max=100, message="物料名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="物料量长度必须介于 0 和 20 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
}