/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.entity;

import org.hibernate.validator.constraints.Length;
import com.medical.modules.sys.entity.User;

import com.medical.common.persistence.DataEntity;

/**
 * 考勤表Entity
 * @author dyf
 * @version 2017-02-22
 */
public class Attendance extends DataEntity<Attendance> {
	
	private static final long serialVersionUID = 1L;
	private String dateTime;		// 日期
	private User user;		// 用户
	private String no;		// 工号
	private String amPlanTime;		// 上班应打卡时间
	private String amRealityTime;		// 上班实际打卡时间
	private String pmPlayTime;		// 下班应打卡时间
	private String pmRealityTim;		// 下班实际打卡时间
	private String lateFlag;		// 迟到标记
	private String leaveFlag;		// 早退标记
	private String absentFlag;		// 旷到标记
	
	public Attendance() {
		super();
	}

	public Attendance(String id){
		super(id);
	}

	@Length(min=0, max=20, message="日期长度必须介于 0 和 20 之间")
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=64, message="工号长度必须介于 0 和 64 之间")
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	@Length(min=0, max=20, message="上班应打卡时间长度必须介于 0 和 20 之间")
	public String getAmPlanTime() {
		return amPlanTime;
	}

	public void setAmPlanTime(String amPlanTime) {
		this.amPlanTime = amPlanTime;
	}
	
	@Length(min=0, max=20, message="上班实际打卡时间长度必须介于 0 和 20 之间")
	public String getAmRealityTime() {
		return amRealityTime;
	}

	public void setAmRealityTime(String amRealityTime) {
		this.amRealityTime = amRealityTime;
	}
	
	@Length(min=0, max=20, message="下班应打卡时间长度必须介于 0 和 20 之间")
	public String getPmPlayTime() {
		return pmPlayTime;
	}

	public void setPmPlayTime(String pmPlayTime) {
		this.pmPlayTime = pmPlayTime;
	}
	
	@Length(min=0, max=20, message="下班实际打卡时间长度必须介于 0 和 20 之间")
	public String getPmRealityTim() {
		return pmRealityTim;
	}

	public void setPmRealityTim(String pmRealityTim) {
		this.pmRealityTim = pmRealityTim;
	}
	
	@Length(min=0, max=1, message="迟到标记长度必须介于 0 和 1 之间")
	public String getLateFlag() {
		return lateFlag;
	}

	public void setLateFlag(String lateFlag) {
		this.lateFlag = lateFlag;
	}
	
	@Length(min=0, max=1, message="早退标记长度必须介于 0 和 1 之间")
	public String getLeaveFlag() {
		return leaveFlag;
	}

	public void setLeaveFlag(String leaveFlag) {
		this.leaveFlag = leaveFlag;
	}
	
	@Length(min=0, max=1, message="旷到标记长度必须介于 0 和 1 之间")
	public String getAbsentFlag() {
		return absentFlag;
	}

	public void setAbsentFlag(String absentFlag) {
		this.absentFlag = absentFlag;
	}
	
}