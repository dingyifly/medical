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
 * 检测申请Entity
 * @author dyf
 * @version 2017-03-11
 */
public class WorkCheck extends DataEntity<WorkCheck> {
	
	private static final long serialVersionUID = 1L;
	private String applyNo;		// 申请单号
	private User applyer;		// 申请人
	private String state;		// 测试报告状态
	private User analyser;		// 分析人
	private String report;		// 检测报告
	private String reportFiles;		// 检测报告文件
	private List<WorkCheckItem> workCheckItemList = Lists.newArrayList();		// 子表列表
	
	public WorkCheck() {
		super();
	}

	public WorkCheck(String id){
		super(id);
	}

	@Length(min=0, max=64, message="申请单号长度必须介于 0 和 64 之间")
	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	
	public User getApplyer() {
		return applyer;
	}

	public void setApplyer(User applyer) {
		this.applyer = applyer;
	}
	
	@Length(min=0, max=1, message="测试报告状态长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public User getAnalyser() {
		return analyser;
	}

	public void setAnalyser(User analyser) {
		this.analyser = analyser;
	}
	
	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}
	
	public String getReportFiles() {
		return reportFiles;
	}

	public void setReportFiles(String reportFiles) {
		this.reportFiles = reportFiles;
	}
	
	public List<WorkCheckItem> getWorkCheckItemList() {
		return workCheckItemList;
	}

	public void setWorkCheckItemList(List<WorkCheckItem> workCheckItemList) {
		this.workCheckItemList = workCheckItemList;
	}
}