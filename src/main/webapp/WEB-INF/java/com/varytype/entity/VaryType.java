package com.varytype.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VaryType")
public class VaryType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "varyTypeID", updatable = false)
	private Integer varyTypeID;
	
	@Column(name = "varyType")
	private String varyType;

	public VaryType() {
		super();
	}

	public VaryType(Integer varyTypeID, String varyType) {
		super();
		this.varyTypeID = varyTypeID;
		this.varyType = varyType;
	}

	public Integer getVaryTypeID() {
		return varyTypeID;
	}

	public void setVaryTypeID(Integer varyTypeID) {
		this.varyTypeID = varyTypeID;
	}

	public String getVaryType() {
		return varyType;
	}

	public void setVaryType(String varyType) {
		this.varyType = varyType;
	}

	@Override
	public String toString() {
		return "VaryType [varyTypeID=" + varyTypeID + ", varyType=" + varyType + "]";
	}

}
