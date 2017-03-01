/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.entity;

import org.hibernate.validator.constraints.Length;
import com.medical.modules.sys.entity.Office;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.medical.common.persistence.DataEntity;

/**
 * 面试管理Entity
 * @author dyf
 * @version 2017-02-22
 */
public class Interview extends DataEntity<Interview> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private Office office;		// 面试部门
	private Date faceDate;		// 面试日期
	private String state;		// 状态(初试、复试、通过)
	private String resume;		// 简历
	
	public Interview() {
		super();
	}

	public Interview(String id){
		super(id);
	}

	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFaceDate() {
		return faceDate;
	}

	public void setFaceDate(Date faceDate) {
		this.faceDate = faceDate;
	}
	
	@Length(min=0, max=1, message="状态(初试、复试、通过)长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=255, message="简历长度必须介于 0 和 255 之间")
	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
	
}