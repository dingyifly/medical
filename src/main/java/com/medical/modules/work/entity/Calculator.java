/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.medical.common.persistence.DataEntity;

/**
 * 物料项目Entity
 * @author dyf
 * @version 2017-03-13
 */
public class Calculator extends DataEntity<Calculator> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 项目名称
	private List<CalculatorItem> calculatorItemList = Lists.newArrayList();		// 子表列表
	
	public Calculator() {
		super();
	}

	public Calculator(String id){
		super(id);
	}

	@Length(min=0, max=100, message="项目名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<CalculatorItem> getCalculatorItemList() {
		return calculatorItemList;
	}

	public void setCalculatorItemList(List<CalculatorItem> calculatorItemList) {
		this.calculatorItemList = calculatorItemList;
	}
}