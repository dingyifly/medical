/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import com.medical.modules.sys.entity.User;

import com.medical.common.persistence.DataEntity;

/**
 * 化学试剂管理Entity
 * @author dyf
 * @version 2017-03-02
 */
public class ReagentRecord extends DataEntity<ReagentRecord> {
	
	private static final long serialVersionUID = 1L;
	private Date useDate;		// 时间
	private String useFlag;		// 使用标识
	private String num;			//使用数量
	private User user;		// 申请人
	private String project;		// 使用项目
	private String instructions;		// 说明
	private String state;		// 审核状态
	private Reagent reagent;		// 试剂id 父类
	
	public ReagentRecord() {
		super();
	}

	public ReagentRecord(String id){
		super(id);
	}

	public ReagentRecord(Reagent reagent){
		this.reagent = reagent;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	
	@Length(min=0, max=1, message="使用标识长度必须介于 0 和 1 之间")
	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=100, message="使用项目长度必须介于 0 和 100 之间")
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}
	
	@Length(min=0, max=255, message="说明长度必须介于 0 和 255 之间")
	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	@Length(min=0, max=1, message="审核状态长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=64, message="试剂id长度必须介于 0 和 64 之间")
	public Reagent getReagent() {
		return reagent;
	}

	public void setReagent(Reagent reagent) {
		this.reagent = reagent;
	}
	
}