/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.entity;

import org.hibernate.validator.constraints.Length;

import com.medical.common.persistence.DataEntity;

/**
 * 项目管理Entity
 * @author dyf
 * @version 2017-03-24
 */
public class ProjectDevelop extends DataEntity<ProjectDevelop> {
	
	private static final long serialVersionUID = 1L;
	private Project project;		// 项目id 父类
	private String projectNo;		// 项目编号
	private String testFlag;		// 开发状态
	private String num;		// 次数
	private String summary;		// 总结
	
	public ProjectDevelop() {
		super();
	}

	public ProjectDevelop(String id){
		super(id);
	}

	public ProjectDevelop(Project project){
		this.project = project;
	}

	@Length(min=0, max=64, message="项目id长度必须介于 0 和 64 之间")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	@Length(min=0, max=64, message="项目编号长度必须介于 0 和 64 之间")
	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	
	@Length(min=0, max=255, message="开发状态长度必须介于 0 和 255 之间")
	public String getTestFlag() {
		return testFlag;
	}

	public void setTestFlag(String testFlag) {
		this.testFlag = testFlag;
	}
	
	@Length(min=0, max=5, message="次数长度必须介于 0 和 5 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}