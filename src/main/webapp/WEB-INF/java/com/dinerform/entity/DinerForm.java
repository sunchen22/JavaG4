package com.dinerform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dinerForm")
public class DinerForm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dinerFormID", updatable = false)
	private Integer dinerFormID;

	@Column(name = "dinerID")
	private Integer dinerID;

	@Column(name = "dinerFormTime")
	private Date dinerFormTime;

	@Column(name = "dinerFormType")
	private String dinerFormType;

	@Column(name = "dinerFormContent", columnDefinition = "longtext")
	private String dinerFormContent;

	@Column(name = "dinerFormReplyStatus")
	private String dinerFormReplyStatus;

	@Column(name = "dinerFormReplyContent", columnDefinition = "longtext")
	private String dinerFormReplyContent;

	@Column(name = "dinerFormReplyTime")
	private Date dinerFormReplyTime;

	@Column(name = "empID")
	private Integer empID;

	public DinerForm() {
		super();
	}

	public DinerForm(Integer dinerFormID, Integer dinerID, Date dinerFormTime, String dinerFormType,
			String dinerFormContent, String dinerFormReplyStatus, String dinerFormReplyContent, Date dinerFormReplyTime,
			Integer empID) {
		super();
		this.dinerFormID = dinerFormID;
		this.dinerID = dinerID;
		this.dinerFormTime = dinerFormTime;
		this.dinerFormType = dinerFormType;
		this.dinerFormContent = dinerFormContent;
		this.dinerFormReplyContent = dinerFormReplyContent;
		this.dinerFormReplyTime = dinerFormReplyTime;
		this.empID = empID;

	}

	public Integer getDinerFormID() {
		return dinerFormID;
	}

	public void setDinerFormID(Integer dinerFormID) {
		this.dinerFormID = dinerFormID;
	}

	public Integer getDinerID() {
		return dinerID;
	}

	public void setDinerID(Integer dinerID) {
		this.dinerID = dinerID;
	}

	public Date getDinerFormTime() {
		return dinerFormTime;
	}

	public void setDinerFormTime(Date dinerFormTime) {
		this.dinerFormTime = dinerFormTime;
	}

	public String getDinerFormType() {
		return dinerFormType;
	}

	public void setDinerFormType(String dinerFormType) {
		this.dinerFormType = dinerFormType;
	}

	public String getDinerFormContent() {
		return dinerFormContent;
	}

	public void setDinerFormContent(String dinerFormContent) {
		this.dinerFormContent = dinerFormContent;
	}

	public String getDinerFormReplyStatus() {
		return dinerFormReplyStatus;
	}

	public void setDinerFormReplyStatus(String dinerFormReplyStatus) {
		this.dinerFormReplyStatus = dinerFormReplyStatus;
	}

	public String getDinerFormReplyContent() {
		return dinerFormReplyContent;
	}

	public void setDinerFormReplyContent(String dinerFormReplyContent) {
		this.dinerFormReplyContent = dinerFormReplyContent;
	}

	public Date getDinerFormReplyTime() {
		return dinerFormReplyTime;
	}

	public void setDinerFormReplyTime(Date dinerFormReplyTime) {
		this.dinerFormReplyTime = dinerFormReplyTime;
	}

	public Integer getEmpID() {
		return empID;
	}

	public void setEmpID(Integer empID) {
		this.empID = empID;
	}

	

}
