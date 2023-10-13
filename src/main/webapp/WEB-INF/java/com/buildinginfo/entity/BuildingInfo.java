package com.buildinginfo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BuildingInfo")
public class BuildingInfo implements Serializable{
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "buildingID")
	private Integer buildingID;
	
	@Column(name = "buildingName")
	private String buildingName;
	
	@Column(name = "buildingAddress")
	private String buildingAddress;
	
	@Column(name = "buildingState")
	private Integer buildingState;
	
	
	public BuildingInfo() {
		super();
		
	}


	public BuildingInfo(Integer buildingID, String buildingName, String buildingAddress ,Integer buildingState) {
		super();
		this.buildingID = buildingID;
		this.buildingName = buildingName;
		this.buildingAddress = buildingAddress;
		this.buildingState = buildingState;
	}


	public Integer getBuildingID() {
		return buildingID;
	}


	public void setBuildingID(Integer buildingID) {
		this.buildingID = buildingID;
	}


	public String getBuildingName() {
		return buildingName;
	}


	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}


	public String getBuildingAddress() {
		return buildingAddress;
	}


	public void setBuildingAddress(String buildingAddress) {
		this.buildingAddress = buildingAddress;
	}
	
	


	public Integer getBuildingState() {
		return buildingState;
	}


	public void setBuildingState(Integer buildingState) {
		this.buildingState = buildingState;
	}


	@Override
	public String toString() {
		return "BuildingInfo [buildingID=" + buildingID + ", buildingName=" + buildingName + ", buildingAddress="
				+ buildingAddress + "]";
	}
	
	
	
}
