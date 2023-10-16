package com.buildinginfo.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.userinfo.entity.UserInfo;

@Entity
@Table(name="BuildingInfo")
public class BuildingInfo implements Serializable{
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "buildingID" )
	private Integer buildingID;
	
	@OneToMany(mappedBy = "buildinginfo" , cascade = CascadeType.ALL)
	private Set<UserInfo> buildinginfo;
	
	@Column(name = "buildingName")
	private String buildingName;
	
	@Column(name = "buildingAddress")
	private String buildingAddress;
	
	@Column(name = "buildingStatus")
	private Integer buildingStatus;
	
	
	public BuildingInfo() {
		super();
		
	}


	public BuildingInfo(Integer buildingID, String buildingName, String buildingAddress ,Integer buildingStatus) {
		super();
		this.buildingID = buildingID;
		this.buildingName = buildingName;
		this.buildingAddress = buildingAddress;
		this.buildingStatus = buildingStatus;
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




	public Integer getbuildingStatus() {
		return buildingStatus;
	}



	public void setbuildingStatus(Integer buildingStatus) {
		this.buildingStatus = buildingStatus;
	}


	@Override
	public String toString() {
		return "BuildingInfo [buildingID=" + buildingID + ", buildingName=" + buildingName + ", buildingAddress="
				+ buildingAddress + ", buildingStatus=" + buildingStatus + "]";
	}
	
	
	
}
