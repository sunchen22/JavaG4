package com.webempadmin.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dinernews.entity.DinerNews;
import com.usernews.entity.UserNews;

@Entity
@Table(name = "webempadmin")
public class Webempadmin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empID", updatable = false)
	private Integer empID;
	
	@OneToMany(mappedBy = "webempadmin" , cascade = CascadeType.ALL  , fetch =  FetchType.EAGER)
	private Set<DinerNews> webempadmin;
	
	@Column(name = "empName")
	private String empName;
	
	@Column(name = "empPassword")
	private String empPassword;
	
	@Column(name = "empArriveDate")
	private Date empArriveDate;
	
	@Column(name = "empAdminAuthorization")
	private String empAdminAuthorization;
	
	@Column(name = "empBlob" , columnDefinition = "longblob")
	private byte[] empBlob;

	@Column(name = "empStatus")
	private boolean empStatus;

	
	@OneToMany(mappedBy = "webempadmin" , cascade = CascadeType.ALL)
	private Set<UserNews> webempadmin1;
	
	
	public Webempadmin() {
		super();
	}

	public Webempadmin(Integer empID, String empName, String empPassword, Date empArriveDate, String empAdminAuthorization, byte[] empBlob, boolean empStatus , Set<DinerNews> webempadmin) {
		super();
		this.empID = empID;
		this.empName = empName;
		this.empPassword = empPassword;
		this.empArriveDate = empArriveDate;
		this.empAdminAuthorization = empAdminAuthorization;
		this.empBlob = empBlob;
		this.empStatus = empStatus;
		this.webempadmin = webempadmin;
	}
	
	

	public Set<DinerNews> getWebempadmin() {
		return webempadmin;
	}

	public void setWebempadmin(Set<DinerNews> webempadmin) {
		this.webempadmin = webempadmin;
	}

	public Integer getEmpID() {
		return empID;
	}
	public void setEmpID(Integer empID) {
		this.empID = empID;
	}
	public String getEmpName() {
		return empName;
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
	public void setEmpBlob(byte[] empBlob) {
		this.empBlob = empBlob;
	}



	public boolean getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(boolean empStatus) {
		this.empStatus = empStatus;

	}


	
	public Set<UserNews> getWebempadmin1() {
		return webempadmin1;
	}

	public void setWebempadmin1(Set<UserNews> webempadmin1) {
		this.webempadmin1 = webempadmin1;
	}

	@Override
	public String toString() {
		return "Webempadmin [empID=" + empID + ", empName=" + empName + ", empPassword=" + empPassword + ", empArriveDate=" + empArriveDate + ",empAdminAuthorization =" +empAdminAuthorization+"]";
	}

}
