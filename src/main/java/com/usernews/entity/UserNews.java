package com.usernews.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.webempadmin.entity.Webempadmin;

@Entity
@Table(name = "usernews")
public class UserNews {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userNewsID", updatable = false)
	private Integer userNewsID;
	
	@Column(name = "userNewsContent")
	private String userNewsContent;
	
	@ManyToOne
	@JoinColumn(name = "empID" , referencedColumnName = "empID")
	private Webempadmin webempadmin ;
	
	@Column(name = "userNewsReleaseTime")
	private Date userNewsReleaseTime;
	
	@Column(name = "userNewsReviseTime")
	private Date userNewsReviseTime;
	
	
	@Column(name = "userNewsStatus", columnDefinition = "int")
	private Integer userNewsStatus;
	
	public UserNews() {
		super();
	}

	public UserNews(Integer userNewsID, String userNewsContent, Webempadmin webempadmin, Date userNewsReleaseTime, Date userNewsReviseTime, Integer  userNewsStatus) {
		super();
		this.userNewsID = userNewsID;
		this.userNewsContent = userNewsContent;
		this.webempadmin = webempadmin;
		this.userNewsReleaseTime = userNewsReleaseTime;
		this.userNewsReviseTime = userNewsReviseTime;
		this.userNewsStatus = userNewsStatus;
		
	}
	
	
	public Webempadmin getWebempadmin() {
		return webempadmin;
	}

	public void setWebempadmin(Webempadmin webempadmin) {
		this.webempadmin = webempadmin;
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
	
	public Integer getUserNewsStatus() {
		return userNewsStatus;
	}

	public void setUserNewsStatus(Integer userNewsStatus) {
		this.userNewsStatus = userNewsStatus;
	}

	@Override
	public String toString() {
		return "UserNews [userNewsID=" + userNewsID + ", userNewsContent=" + userNewsContent + ", webempadmin="
				+ webempadmin + ", userNewsReleaseTime=" + userNewsReleaseTime + ", userNewsReviseTime="
				+ userNewsReviseTime + ", userNewsStatus=" + userNewsStatus + "]";
	}
	
	
}
