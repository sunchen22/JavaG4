package com.usernews.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usernews")
public class UserNews {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userNewsID", updatable = false)
	private Integer userNewsID;
	
	@Column(name = "userNewsContent")
	private String userNewsContent;
	
	@Column(name = "empID")
	private Integer empID;
	
	@Column(name = "userNewsReleaseTime")
	private Date userNewsReleaseTime;
	
	@Column(name = "userNewsReviseTime")
	private Date userNewsReviseTime;
	
	
	public UserNews() {
		super();
	}

	public UserNews(Integer userNewsID, String userNewsContent, Integer empID, Date userNewsReleaseTime, Date userNewsReviseTime) {
		super();
		this.userNewsID = userNewsID;
		this.userNewsContent = userNewsContent;
		this.empID = empID;
		this.userNewsReleaseTime = userNewsReleaseTime;
		this.userNewsReviseTime = userNewsReviseTime;
		
	}
	
	
	public Integer getUserNewsID() {
		return userNewsID;
	}
	public void setUserNewsID(Integer userNewsID) {
		this.userNewsID = userNewsID;
	}
	public String getUserNewsContent() {
		return userNewsContent;
	}
	public void setUserNewsContent(String userNewsContent) {
		this.userNewsContent = userNewsContent;
	}
	public Integer getEmpID() {
		return empID;
	}
	public void setEmpID(Integer empID) {
		this.empID = empID;
	}
	public Date getUserNewsReleaseTime() {
		return userNewsReleaseTime;
	}
	public void setUserNewsReleaseTime(Date userNewsReleaseTime) {
		this.userNewsReleaseTime = userNewsReleaseTime;
	}
	public Date getUserNewsReviseTime() {
		return userNewsReviseTime;
	}
	public void setUserNewsReviseTime(Date userNewsReviseTime) {
		this.userNewsReviseTime = userNewsReviseTime;
	}
	

}
