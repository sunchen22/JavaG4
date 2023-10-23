package com.advertisement.entity;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dinerinfo.entity.DinerInfo;

@Entity
@Table(name="Advertisement")
public class Advertisement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="advertisementID", updatable = false)
	private Integer advertisementID;
		
	
//	@Column(name="dinerID")
//	private Integer	dinerID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dinerid" ,referencedColumnName = "dinerid")
	private DinerInfo dinerid;
	
	
	
	@Column(name="advertisementBlob", columnDefinition = "longBlob" )
	private byte[] advertisementBlob;
	
	@Column(name="advertisementName")
	private String advertisementName;
	
	@Column(name="advertisementUpTime")
	private Timestamp advertisementUpTime;
	
	@Column(name="advertisementDownTime")
	private Timestamp advertisementDownTime;
	
	@Column(name="advertisementDuringTime")
	private Integer advertisementDuringTime;
	
	@Column(name="advertisementStatus")
	private String advertisementStatus;
	

	public Advertisement() {
		super();
	}


	public Advertisement(Integer advertisementID, byte[] advertisementBlob, String advertisementName,
			Timestamp advertisementUpTime, Timestamp advertisementDownTime, Integer advertisementDuringTime,
			String advertisementStatus , DinerInfo dinerid) {
		super();
		this.advertisementID = advertisementID;
//		this.dinerID = dinerID;
		this.advertisementBlob = advertisementBlob;
		this.advertisementName = advertisementName;
		this.advertisementUpTime = advertisementUpTime;
		this.advertisementDownTime = advertisementDownTime;
		this.advertisementDuringTime = advertisementDuringTime;
		this.advertisementStatus = advertisementStatus;
		this.dinerid = dinerid;
	}


	public Integer getAdvertisementID() {
		return advertisementID;
	}


	public void setAdvertisementID(Integer advertisementID) {
		this.advertisementID = advertisementID;
	}

	
	
	
	
	
	

//	public Integer getDinerID() {
//		return dinerID;
//	}
//
//
//	public void setDinerID(Integer dinerID) {
//		this.dinerID = dinerID;
//	}
	
	
	


	

	public DinerInfo getDinerid() {
		return dinerid;
	}


	public void setDinerid(DinerInfo dinerid) {
		this.dinerid = dinerid;
	}
	
	
	
	public byte[] getAdvertisementBlob() {
		return advertisementBlob;
	}


	


	public void setAdvertisementBlob(byte[] advertisementBlob) {
		this.advertisementBlob = advertisementBlob;
	}


	public String getAdvertisementName() {
		return advertisementName;
	}


	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
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
	
	



}
