package com.webempadmin.model;
import java.io.InputStream;
import java.sql.Date;

public class WebempadminVO implements java.io.Serializable{
	private Integer empID;
	private String empName;
	private String empPassword;
	private Date empArriveDate;
	private String empAdminAuthorization;
	private byte[] empBlob;
	
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
	public void setEmpBlob(byte[] is) {
		this.empBlob = is;
	}
}
