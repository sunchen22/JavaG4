package com.userfaq.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "userfaq")
public class UserFAQ {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userFAQID", updatable = false)
	private Integer userFAQID;
	
	@Column(name = "userFAQTitle")
	private String userFAQTitle;
	
	@Column(name = "userFAQContent", columnDefinition = "longtext")
	private String userFAQContent;
	
	@Column(name = "userFAQReleaseTime")
	private Date userFAQReleaseTime;
	
	@Column(name = "userFAQReviseTime")
	private Date userFAQReviseTime;
	
	@Column(name = "empID")
	private Integer empID;
	
	public UserFAQ() {
		super();
	}

	public UserFAQ(Integer userFAQID, String userFAQTitle, String userFAQContent, Date userFAQReleaseTime, Date userFAQReviseTime, Integer empID) {
		super();
		this.userFAQID = userFAQID;
		this.userFAQTitle = userFAQTitle;
		this.userFAQContent = userFAQContent;
		this.userFAQReleaseTime = userFAQReleaseTime;
		this.userFAQReviseTime = userFAQReviseTime;
		this.empID = empID;
		
	}
	
	public Integer getUserFAQID() {
		return userFAQID;
	}
	public void setUserFAQID(Integer userFAQID) {
		this.userFAQID = userFAQID;
	}
	public String getUserFAQTitle() {
		return userFAQTitle;
	}
	public void setUserFAQTitle(String userFAQTitle) {
		this.userFAQTitle = userFAQTitle;
	}
	public String getUserFAQContent() {
		return userFAQContent;
	}
	public void setUserFAQContent(String userFAQContent) {
		this.userFAQContent = userFAQContent;
	}
	public Date getUserFAQReleaseTime() {
		return userFAQReleaseTime;
	}
	public void setUserFAQReleaseTime(Date userFAQReleaseTime) {
		this.userFAQReleaseTime = userFAQReleaseTime;
	}
	public Date getUserFAQReviseTime() {
		return userFAQReviseTime;
	}
	public void setUserFAQReviseTime(Date userFAQReviseTime) {
		this.userFAQReviseTime = userFAQReviseTime;
	}
	public Integer getEmpID() {
		return empID;
	}
	public void setEmpID(Integer empID) {
		this.empID = empID;
	}
	
	
	
}
