/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.entity;

import org.hibernate.validator.constraints.Length;
import com.medical.modules.sys.entity.User;
import java.util.List;
import com.google.common.collect.Lists;

import com.medical.common.persistence.DataEntity;

/**
 * 项目管理Entity
 * @author dyf
 * @version 2017-03-24
 */
public class Project extends DataEntity<Project> {
	
	private static final long serialVersionUID = 1L;
	private String no;		// 项目编号
	private String name;		// 项目名称
	private User principal;		// 项目负责人
	private String lvl;		// 评级
	private String state;		// 状态
	private String investigationReport;		// 调研报告
	private String costBudgeting;		// 成本预算
	private String devFlag;		// 开发状态
	private String smallNum;		// 小试次数
	private String middleNum;		// 中试次数
	private String largeNum;		// 发大次数
	private String bonusRecord;		// 奖金发放记录
	private String summary;		// 总结
	private List<ProjectDevelop> projectDevelopList = Lists.newArrayList();		// 子表列表
	
	public Project() {
		super();
	}

	public Project(String id){
		super(id);
	}

	@Length(min=0, max=64, message="项目编号长度必须介于 0 和 64 之间")
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	@Length(min=0, max=100, message="项目名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public User getPrincipal() {
		return principal;
	}

	public void setPrincipal(User principal) {
		this.principal = principal;
	}
	
	@Length(min=0, max=1, message="评级长度必须介于 0 和 1 之间")
	public String getLvl() {
		return lvl;
	}

	public void setLvl(String lvl) {
		this.lvl = lvl;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getInvestigationReport() {
		return investigationReport;
	}

	public void setInvestigationReport(String investigationReport) {
		this.investigationReport = investigationReport;
	}
	
	public String getCostBudgeting() {
		return costBudgeting;
	}

	public void setCostBudgeting(String costBudgeting) {
		this.costBudgeting = costBudgeting;
	}
	
	@Length(min=0, max=1, message="开发状态长度必须介于 0 和 1 之间")
	public String getDevFlag() {
		return devFlag;
	}

	public void setDevFlag(String devFlag) {
		this.devFlag = devFlag;
	}
	
	@Length(min=0, max=5, message="小试次数长度必须介于 0 和 5 之间")
	public String getSmallNum() {
		return smallNum;
	}

	public void setSmallNum(String smallNum) {
		this.smallNum = smallNum;
	}
	
	@Length(min=0, max=5, message="中试次数长度必须介于 0 和 5 之间")
	public String getMiddleNum() {
		return middleNum;
	}

	public void setMiddleNum(String middleNum) {
		this.middleNum = middleNum;
	}
	
	@Length(min=0, max=5, message="发大次数长度必须介于 0 和 5 之间")
	public String getLargeNum() {
		return largeNum;
	}

	public void setLargeNum(String largeNum) {
		this.largeNum = largeNum;
	}
	
	public String getBonusRecord() {
		return bonusRecord;
	}

	public void setBonusRecord(String bonusRecord) {
		this.bonusRecord = bonusRecord;
	}
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public List<ProjectDevelop> getProjectDevelopList() {
		return projectDevelopList;
	}

	public void setProjectDevelopList(List<ProjectDevelop> projectDevelopList) {
		this.projectDevelopList = projectDevelopList;
	}
}