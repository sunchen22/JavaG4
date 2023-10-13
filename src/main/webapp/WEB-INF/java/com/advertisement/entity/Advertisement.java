package com.advertisement.entity;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
public class Advertisement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="advertisementID", updatable = false)
	private Integer advertisementID;
	
	@Column(name="dinerID")
	private Integer dinerID;
	
	@Column(name="dinerID", columnDefinition = "longBlob" )
	private byte[] advertisementBlob;
	
	@Column(name="advertisementUpTime")
	private Timestamp advertisementUpTime;
	
	@Column(name="advertisementDownTime")
	private Timestamp advertisementDownTime;
	
	@Column(name="advertisementDuringTime")
	private Integer advertisementDuringTime;
	
	@Column(name="advertisementStatus")
	private String advertisementStatus;

	
	public Integer getAdvertisementID() {
		return advertisementID;
	}

	public void setAdvertisementID(Integer advertisementID) {
		this.advertisementID = advertisementID;
	}

	public Integer getDinerID() {
		return dinerID;
	}

	public void setDinerID(Integer dinerID) {
		this.dinerID = dinerID;
	}

	public byte[] getAdvertisementBlob() {
		return advertisementBlob;
	}

	public void setAdvertisementBlob(byte[] advertisementBlob) {
		this.advertisementBlob = advertisementBlob;
	}

	public Timestamp getAdvertisementUpTime() {
		return advertisementUpTime;
	}

	public void setAdvertisementUpTime(Timestamp advertisementUpTime) {
		this.advertisementUpTime = advertisementUpTime;
	}

	public Timestamp getAdvertisementDownTime() {
		return advertisementDownTime;
	}

	public void setAdvertisementDownTime(Timestamp advertisementDownTime) {
		this.advertisementDownTime = advertisementDownTime;
	}

	public Integer getAdvertisementDuringTime() {
		return advertisementDuringTime;
	}

	public void setAdvertisementDuringTime(Integer advertisementDuringTime) {
		this.advertisementDuringTime = advertisementDuringTime;
	}

	public String getAdvertisementStatus() {
		return advertisementStatus;
	}

	public void setAdvertisementStatus(String advertisementStatus) {
		this.advertisementStatus = advertisementStatus;
	}

	@Override
	public String toString() {
		return "Advertisement [advertisementID=" + advertisementID + ", dinerID=" + dinerID + ", advertisementBlob="
				+ Arrays.toString(advertisementBlob) + ", advertisementUpTime=" + advertisementUpTime
				+ ", advertisementDownTime=" + advertisementDownTime + ", advertisementDuringTime="
				+ advertisementDuringTime + ", advertisementStatus=" + advertisementStatus + "]";
	}

}
