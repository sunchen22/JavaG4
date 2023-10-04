package com.webempadmin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "webempadmin")
public class Webempadmin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empID", updatable = false)
	private Integer empID;
	
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

	@Column(name = "empDeactivate")
	private boolean empDeactivate;

	public Webempadmin() {
		super();
	}

	public Webempadmin(Integer empID, String empName, String empPassword, Date empArriveDate, String empAdminAuthorization, byte[] empBlob, boolean empDeactivate) {
		super();
		this.empID = empID;
		this.empName = empName;
		this.empPassword = empPassword;
		this.empArriveDate = empArriveDate;
		this.empAdminAuthorization = empAdminAuthorization;
		this.empBlob = empBlob;
		this.empDeactivate = empDeactivate;
		
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


	public boolean getEmpDeactivate() {
		return empDeactivate;
	}

	public void setEmpDeactivate(boolean empDeactivate) {
		this.empDeactivate = empDeactivate;
	}
	
	@Override
	public String toString() {
		return "Webempadmin [empID=" + empID + ", empName=" + empName + ", empPassword=" + empPassword + ", empArriveDate=" + empArriveDate + ",empAdminAuthorization =" +empAdminAuthorization+"]";
	}

}
