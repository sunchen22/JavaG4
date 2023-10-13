package com.grouporder.entity;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "grouporder")
public class GroupOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "groupOrderID", updatable = false)
	private	Integer	groupOrderID;
	
	@Column(name = "dinerID")
	private	Integer	dinerID;
	
	@Column(name = "buildingID")
	private	Integer	buildingID;
	
	@Column(name = "orderStatus")
	private	String orderStatus;
	
	@Column(name = "groupOrderCreateTime")
	private	Timestamp groupOrderCreateTime;
	
	@Column(name = "groupOrderSubmitTime")
	private	Timestamp groupOrderSubmitTime;
	
	@Column(name = "holderID")
	private	Integer	holderID;
	
	@Column(name = "groupTotalPrice")
	private	Integer	groupTotalPrice;
	
	@Column(name = "deliveredBlob" , columnDefinition = "longblob")
	private	byte[] deliveredBlob;

	public GroupOrder() {
		super();
	}

	public GroupOrder(Integer groupOrderID, Integer dinerID, Integer buildingID, String orderStatus,
			Timestamp groupOrderCreateTime, Timestamp groupOrderSubmitTime, Integer holderID, Integer groupTotalPrice,
			byte[] deliveredBlob) {
		super();
		this.groupOrderID = groupOrderID;
		this.dinerID = dinerID;
		this.buildingID = buildingID;
		this.orderStatus = orderStatus;
		this.groupOrderCreateTime = groupOrderCreateTime;
		this.groupOrderSubmitTime = groupOrderSubmitTime;
		this.holderID = holderID;
		this.groupTotalPrice = groupTotalPrice;
		this.deliveredBlob = deliveredBlob;
	}

	public Integer getGroupOrderID() {
		return groupOrderID;
	}

	public void setGroupOrderID(Integer groupOrderID) {
		this.groupOrderID = groupOrderID;
	}

	public Integer getDinerID() {
		return dinerID;
	}

	public void setDinerID(Integer dinerID) {
		this.dinerID = dinerID;
	}

	public Integer getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(Integer buildingID) {
		this.buildingID = buildingID;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Timestamp getGroupOrderCreateTime() {
		return groupOrderCreateTime;
	}

	public void setGroupOrderCreateTime(Timestamp groupOrderCreateTime) {
		this.groupOrderCreateTime = groupOrderCreateTime;
	}

	public Timestamp getGroupOrderSubmitTime() {
		return groupOrderSubmitTime;
	}

	public void setGroupOrderSubmitTime(Timestamp groupOrderSubmitTime) {
		this.groupOrderSubmitTime = groupOrderSubmitTime;
	}

	public Integer getHolderID() {
		return holderID;
	}

	public void setHolderID(Integer holderID) {
		this.holderID = holderID;
	}

	public Integer getGroupTotalPrice() {
		return groupTotalPrice;
	}

	public void setGroupTotalPrice(Integer groupTotalPrice) {
		this.groupTotalPrice = groupTotalPrice;
	}

	public byte[] getDeliveredBlob() {
		return deliveredBlob;
	}

	public void setDeliveredBlob(byte[] deliveredBlob) {
		this.deliveredBlob = deliveredBlob;
	}

	@Override
	public String toString() {
		return "GroupOrder [groupOrderID=" + groupOrderID + ", dinerID=" + dinerID + ", buildingID=" + buildingID
				+ ", orderStatus=" + orderStatus + ", groupOrderCreateTime=" + groupOrderCreateTime
				+ ", groupOrderSubmitTime=" + groupOrderSubmitTime + ", holderID=" + holderID + ", groupTotalPrice="
				+ groupTotalPrice + ", deliveredBlob=" + Arrays.toString(deliveredBlob) + "]";
	}
		
}
