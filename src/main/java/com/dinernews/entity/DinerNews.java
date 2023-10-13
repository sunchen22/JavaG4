package com.dinernews.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.webempadmin.model.WebempadminVO;

@Entity
@Table(name = "DinerNews")
public class DinerNews {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "dinerNewsID")
	private Integer dinerNewsID;
	
	@Column(name = "dinerNewsContent")
	private String dinerNewsContent;
	
	
//	@Column(name = "empID")
//	private Integer empID;
	
	
	@JoinColumn(name = "empID" , referencedColumnName = "empID")
	private WebempadminVO webempadminvo;
	
	
	@Column(name = "dinerNewsReleaseTime")
	
	private Timestamp dinerNewsReleaseTime;
		
	
	private Timestamp dinerNewsReviseTime;
	
	public DinerNews(){
		super();
	}

	public DinerNews(Integer dinerNewsID, String dinerNewsContent, WebempadminVO webempadminvo,
			Timestamp dinerNewsReleaseTime, Timestamp dinerNewsReviseTime) {
		super();
		this.dinerNewsID = dinerNewsID;
		this.dinerNewsContent = dinerNewsContent;
		this.webempadminvo = webempadminvo;
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

	public WebempadminVO getWebempadminvo() {
		return webempadminvo;
	}

	public void setWebempadminvo(WebempadminVO webempadminvo) {
		this.webempadminvo = webempadminvo;
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

	
	

	

	

	
	
	
	
	

	
	
	
}
