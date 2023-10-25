package com.dinernews.entity;

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
@Table(name = "DinerNews")
public class DinerNews {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "dinerNewsID")
	private Integer dinerNewsID;
	
	@Column(name = "dinerNewsContent1")
	private String dinerNewsContent1;
	
	@Column(name = "dinerNewsContent2")
	private String dinerNewsContent2;
	
	
	@Column(name = "dinerNewsContent3")
	private String dinerNewsContent3;
	
	
	
//	@Column(name = "empID")
//	private Integer empID;
	
	@ManyToOne
	@JoinColumn(name = "empID" , referencedColumnName = "empID")

	private Webempadmin webempadmin ;
	

	
	@Column(name = "dinerNewsReleaseTime")
	
	private Date dinerNewsReleaseTime;
		
	@Column(name = "dinerNewsReviseTime")
	private Date dinerNewsReviseTime;
	
	@Column(name = "dinerNewsStatus")
	private Integer dinerNewsStatus;
	
	public DinerNews(){
		super();
	}


	public DinerNews(Integer dinerNewsID, String dinerNewsContent1, /*Integer empID,*/
			Date dinerNewsReleaseTime, Date dinerNewsReviseTime , String dinerNewsContent2 , String dinerNewsContent3 , 
			Integer dinerNewsStatus , Webempadmin webempadmin) {

	
		this.dinerNewsID = dinerNewsID;

		this.dinerNewsContent1 = dinerNewsContent1;
		this.dinerNewsContent2 = dinerNewsContent2;
		this.dinerNewsContent3 = dinerNewsContent3;
		this.webempadmin = webempadmin;
//		this.empID = empID;

		
		this.webempadmin = webempadmin;

		this.dinerNewsReleaseTime = dinerNewsReleaseTime;
		this.dinerNewsReviseTime = dinerNewsReviseTime;
		this.dinerNewsStatus = dinerNewsStatus;
		
		
	}

	
	
	public void setDinerNewsStatus(Integer dinerNewsStatus) {
		this.dinerNewsStatus = dinerNewsStatus;
	}
	
	public Integer getDinerNewsStatus() {
			return dinerNewsStatus;
	}
	
	public Integer getDinerNewsID() {
		return dinerNewsID;
	}
	

	public void setDinerNewsID(Integer dinerNewsID) {
		this.dinerNewsID = dinerNewsID;
	}

	public String getDinerNewsContent1() {
		return dinerNewsContent1;
	}

	public void setDinerNewsContent1(String dinerNewsContent1) {
		this.dinerNewsContent1 = dinerNewsContent1;
	}


		
	
	public String getDinerNewsContent2() {
		return dinerNewsContent2;
	}	
	public void setDinerNewsContent2(String dinerNewsContent2) {
	this.dinerNewsContent2 = dinerNewsContent2;
		
	}
	public String getDinerNewsContent3() {
			return dinerNewsContent3;
		}
	
		public void setDinerNewsContent3(String dinerNewsContent3) {
			this.dinerNewsContent3 = dinerNewsContent3;
		}
	public Webempadmin getWebempadmin() {
		return webempadmin;

	}


	

	public void setWebempadmin(Webempadmin webempadmin) {
		this.webempadmin = webempadmin;

	}

	


	
//	public Integer getEmpID() {
//		return empID;
//	}
//
//	public void setEmpID(Integer empID) {
//		this.empID = empID;
//	}

	
	

	public Date getDinerNewsReleaseTime() {
		return dinerNewsReleaseTime;
	}

	
	public void setDinerNewsReleaseTime(Date dinerNewsReleaseTime) {
		this.dinerNewsReleaseTime = dinerNewsReleaseTime;
	}

	public Date getDinerNewsReviseTime() {
		return dinerNewsReviseTime;
	}

	public void setDinerNewsReviseTime(Date dinerNewsReviseTime) {
		this.dinerNewsReviseTime = dinerNewsReviseTime;
	}

	
	

	

	

	
	
	
	
	

	
	
	
}
