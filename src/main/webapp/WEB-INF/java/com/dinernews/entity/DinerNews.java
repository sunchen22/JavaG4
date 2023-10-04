package com.dinernews.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "DinerNews")
public class DinerNews {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "dinerNewsID")
	private Integer dinerNewsID;
	
	@Column(name = "dinerNewsContent")
	private String dinerNewsContent;
	
	@Column(name = "empID")
	private int empID;
	
	
	@Column(name = "dinerNewsReleaseTime")
	
	private Timestamp dinerNewsReleaseTime;
	
	
	
	private Timestamp dinerNewsReviseTime;
	
	public DinerNews(){
		super();
	}

	public DinerNews(Integer dinerNewsID, String dinerNewsContent, int empID, Timestamp dinerNewsReleaseTime,
			Timestamp dinerNewsReviseTime) {
		super();
		this.dinerNewsID = dinerNewsID;
		this.dinerNewsContent = dinerNewsContent;
		this.empID = empID;
		this.dinerNewsReleaseTime = dinerNewsReleaseTime;
		this.dinerNewsReviseTime = dinerNewsReviseTime;
	}

	public Integer getDinerNewsID() {
		return dinerNewsID;
	}

	public void setDinerNewsID(Integer dinerNewsID) {
		this.dinerNewsID = dinerNewsID;
	}

	public String getDinerNewsContent() {
		return dinerNewsContent;
	}

	public void setDinerNewsContent(String dinerNewsContent) {
		this.dinerNewsContent = dinerNewsContent;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public Timestamp getDinerNewsReleaseTime() {
		return dinerNewsReleaseTime;
	}

	public void setDinerNewsReleaseTime(Timestamp dinerNewsReleaseTime) {
		this.dinerNewsReleaseTime = dinerNewsReleaseTime;
	}

	public Timestamp getDinerNewsReviseTime() {
		return dinerNewsReviseTime;
	}

	public void setDinerNewsReviseTime(Timestamp dinerNewsReviseTime) {
		this.dinerNewsReviseTime = dinerNewsReviseTime;
	}

	@Override
	public String toString() {
		return "DinerNews [dinerNewsID=" + dinerNewsID + ", dinerNewsContent=" + dinerNewsContent + ", empID=" + empID
				+ ", dinerNewsReleaseTime=" + dinerNewsReleaseTime + ", dinerNewsReviseTime=" + dinerNewsReviseTime
				+ "]";
	}
	
	
}
