package com.businesshours.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class BusinessHours {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dinerOpenHoursID", updatable = false)
	private Integer dinerOpenHoursID;
	
	@Column(name="dinerID")
	private Integer dinerID;
	
	@Column(name="dayOfWeek")
	private String dayOfWeek;
	
	@Column(name="openTime")
	private Time openTime;
	
	@Column(name="closeTime")
	private Time closeTime;
	
	@Column(name="openStatus")
	private String openStatus;
	
	
	public Integer getDinerOpenHoursID() {
		return dinerOpenHoursID;
	}
	public void setDinerOpenHoursID(Integer dinerOpenHoursID) {
		this.dinerOpenHoursID = dinerOpenHoursID;
	}
	public Integer getDinerID() {
		return dinerID;
	}
	public void setDinerID(Integer dinerID) {
		this.dinerID = dinerID;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public Time getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Time openTime) {
		this.openTime = openTime;
	}
	public Time getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Time closeTime) {
		this.closeTime = closeTime;
	}
	public String getOpenStatus() {
		return openStatus;
	}
	public void setOpenStatus(String openStatus) {
		this.openStatus = openStatus;
	}
	
	@Override
	public String toString() {
		return "BusinessHours [dinerOpenHoursID=" + dinerOpenHoursID + ", dinerID=" + dinerID + ", dayOfWeek="
				+ dayOfWeek + ", openTime=" + openTime + ", closeTime=" + closeTime + ", openStatus=" + openStatus
				+ "]";
	}
	
	
}
