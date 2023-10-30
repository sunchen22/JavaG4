package com.webempadmin.model;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dinernews.entity.DinerNews;

@Entity
@Table(name = "Webempadmin")
public class WebempadminVO implements java.io.Serializable{
	
	@Id
	@Column(name = "empID")
	private Integer empID;
	
//	@OneToMany(mappedBy = "webempadmin" , cascade = CascadeType.ALL)
//	private Set<DinerNews> webempadmin;

	
	private String empName;
	private String empPassword;
	private Date empArriveDate;
	private String empAdminAuthorization;
	@Column(name = "empBlob", columnDefinition = "longblob")
	private byte[] empBlob;
	private Integer empStatus;
	
//	public Integer getEmpID() {
//		return empID;
//	}
//	public void setEmpID(Integer empID) {
//		this.empID = empID;
//	}
	

	
	public String getEmpName() {
		return empName;
	}
	
	
	public Integer getEmpID() {
		return empID;
	}


	public void setEmpID(Integer empID) {
		this.empID = empID;
	}


	


	public Integer getEmpStatus() {
		return empStatus;
	}


	public void setEmpStatus(Integer empStatus) {
		this.empStatus = empStatus;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	public Date getEmpArriveDate() {
		return empArriveDate;
	}
	public void setEmpArriveDate(Date empArriveDate) {
		this.empArriveDate = empArriveDate;
	}
	public String getEmpAdminAuthorization() {
		return empAdminAuthorization;
	}
	public void setEmpAdminAuthorization(String empAdminAuthorization) {
		this.empAdminAuthorization = empAdminAuthorization;
	}
	public byte[] getEmpBlob() {
		return empBlob;
	}
	public void setEmpBlob(byte[] is) {
		this.empBlob = is;
	}
}
