package com.consumerform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consumerForm")
public class ConsumerForm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "consumerFormID", updatable = false)
	private Integer consumerFormID;
	
	@Column(name = "userID")
	private Integer userID;
	
	@Column(name = "consumerFormTime")
	private Date consumerFormTime;
	
	@Column(name = "consumerFormType")
	private String consumerFormType;
	
	@Column(name = "consumerFormMail")
	private String consumerFormMail;
	
	@Column(name = "consumerFormPhone")
	private String consumerFormPhone;
	
	@Column(name = "consumerFormContent", columnDefinition = "longtext")
	private String consumerFormContent;
	
	@Column(name = "consumerFormReplyStatus")
	private String consumerFormReplyStatus;
	
	@Column(name = "consumerFormReplyContent", columnDefinition = "longtext")
	private String consumerFormReplyContent;
	
	@Column(name = "consumerFormReplyTime")
	private Date consumerFormReplyTime;
	
	@Column(name = "empID")
	private Integer empID;
	
	
	public ConsumerForm() {
		super();
	}

	public ConsumerForm(Integer consumerFormID, Integer userID, Date consumerFormTime, String consumerFormType, String consumerFormMail, String consumerFormPhone, String consumerFormContent,  String consumerFormReplyStatus, String consumerFormReplyContent, Date consumerFormReplyTime, Integer empID) {
		super();
		this.consumerFormID = empID;
		this.userID = userID;
		this.consumerFormTime = consumerFormTime;
		this.consumerFormType = consumerFormType;
		this.consumerFormMail = consumerFormMail;
		this.consumerFormPhone = consumerFormPhone;
		this.consumerFormContent = consumerFormContent;
		this.consumerFormReplyStatus = consumerFormReplyStatus;
		this.consumerFormReplyContent = consumerFormReplyContent;
		this.consumerFormReplyTime = consumerFormReplyTime;
		this.empID = empID;
		
	}
	
	
	
	public Integer getConsumerFormID() {
		return consumerFormID;
	}
	public void setConsumerFormID(Integer consumerFormID) {
		this.consumerFormID = consumerFormID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Date getConsumerFormTime() {
		return consumerFormTime;
	}
	public void setConsumerFormTime(Date consumerFormTime) {
		this.consumerFormTime = consumerFormTime;
	}
	public String getConsumerFormType() {
		return consumerFormType;
	}
	public void setConsumerFormType(String consumerFormType) {
		this.consumerFormType = consumerFormType;
	}
	public String getConsumerFormMail() {
		return consumerFormMail;
	}
	public void setConsumerFormMail(String consumerFormMail) {
		this.consumerFormMail = consumerFormMail;
	}
	public String getConsumerFormPhone() {
		return consumerFormPhone;
	}
	public void setConsumerFormPhone(String consumerFormPhone) {
		this.consumerFormPhone = consumerFormPhone;
	}
	public String getConsumerFormContent() {
		return consumerFormContent;
	}
	public void setConsumerFormContent(String consumerFormContent) {
		this.consumerFormContent = consumerFormContent;
	}
	public String getConsumerFormReplyStatus() {
		return consumerFormReplyStatus;
	}
	public void setConsumerFormReplyStatus(String consumerFormReplyStatus) {
		this.consumerFormReplyStatus = consumerFormReplyStatus;
	}
	public String getConsumerFormReplyContent() {
		return consumerFormReplyContent;
	}
	public void setConsumerFormReplyContent(String consumerFormReplyContent) {
		this.consumerFormReplyContent = consumerFormReplyContent;
	}
	public Date getConsumerFormReplyTime() {
		return consumerFormReplyTime;
	}
	public void setConsumerFormReplyTime(Date consumerFormReplyTime) {
		this.consumerFormReplyTime = consumerFormReplyTime;
	}
	public Integer getEmpID() {
		return empID;
	}
	public void setEmpID(Integer empID) {
		this.empID = empID;
	}

	
}
