/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.medical.common.persistence.DataEntity;

/**
 * 化学试剂管理Entity
 * @author dyf
 * @version 2017-03-02
 */
public class Reagent extends DataEntity<Reagent> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 化学名称
	private String repertory;		// 库存
	private String model;		// 型号（CAS号）
	private String descr;		// 描述
	private String instructions;		// 使用说明(使用说明和注意事项合并为MSDS)
	private String matters;		// 注意事项
	
	private String specification;//规格
	private String unit;//单位
	
	private List<ReagentRecord> reagentRecordList = Lists.newArrayList();		// 子表列表
	
	public Reagent() {
		super();
	}

	public Reagent(String id){
		super(id);
	}

	@Length(min=0, max=100, message="化学名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=10, message="库存长度必须介于 0 和 10 之间")
	public String getRepertory() {
		return repertory;
	}

	public void setRepertory(String repertory) {
		this.repertory = repertory;
	}
	
	@Length(min=0, max=50, message="型号长度必须介于 0 和 50 之间")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Length(min=0, max=255, message="描述长度必须介于 0 和 255 之间")
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	public String getMatters() {
		return matters;
	}

	public void setMatters(String matters) {
		this.matters = matters;
	}
	
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<ReagentRecord> getReagentRecordList() {
		return reagentRecordList;
	}

	public void setReagentRecordList(List<ReagentRecord> reagentRecordList) {
		this.reagentRecordList = reagentRecordList;
	}
}