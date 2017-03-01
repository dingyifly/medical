/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.medical.common.persistence.DataEntity;
import com.medical.common.utils.Collections3;
import com.medical.modules.sys.entity.User;

/**
 * 会议管理Entity
 * @author dyf
 * @version 2017-02-28
 */
public class Meeting extends DataEntity<Meeting> {
	
	private static final long serialVersionUID = 1L;
	private Date meetingTime;		// 会议时间
	private User compere;		// 主持人
	private String theme;		// 主题
	private String participant;		// 参加会议人员
	private String record;		// 会议记录
	private User recorder;		// 记录员
	private String executiveCondition;		// 执行情况
	
	private List<User> participantList = Lists.newArrayList();
	
	public Meeting() {
		super();
	}

	public Meeting(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(Date meetingTime) {
		this.meetingTime = meetingTime;
	}
	
	@NotNull(message="主持人不能为空")
	public User getCompere() {
		return compere;
	}

	public void setCompere(User compere) {
		this.compere = compere;
	}
	
	@Length(min=1, max=255, message="主题长度必须介于 1 和 255 之间")
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}
	
	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}
	
	@NotNull(message="记录员不能为空")
	public User getRecorder() {
		return recorder;
	}

	public void setRecorder(User recorder) {
		this.recorder = recorder;
	}
	
	@Length(min=0, max=255, message="执行情况长度必须介于 0 和 255 之间")
	public String getExecutiveCondition() {
		return executiveCondition;
	}

	public void setExecutiveCondition(String executiveCondition) {
		this.executiveCondition = executiveCondition;
	}

	@JsonIgnore
	public List<User> getParticipantList() {
		return participantList;
	}

	public void setParticipantList(List<User> participantList) {
		this.participantList = participantList;
	}
	
	private String participantNames;
	
	@JsonIgnore
	public String getParticipantNames() {
//		return Collections3.convertToString(participantList, "name", ",");
		return participantNames;
	}
	
	public void setParticipantNames(String participantNames) {
		this.participantNames = participantNames;
	}
	
}