package com.grouporder.entity;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.buildinginfo.entity.BuildingInfo;
import com.dinerinfo.entity.DinerInfo;
import com.userinfo.entity.UserInfo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "grouporder")
public class GroupOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "groupOrderID", updatable = false)
	private Integer groupOrderID;

//	@Column(name = "dinerID")
//	private	Integer	dinerID;

	@ManyToOne
	@JoinColumn(name = "dinerID")
	private DinerInfo dinerInfo;

//	@Column(name = "buildingID")
//	private	Integer	buildingID;

	@ManyToOne
	@JoinColumn(name = "buildingID")
	private BuildingInfo buildingInfo;

	@Column(name = "orderStatus")
	private String orderStatus;

	@Column(name = "groupOrderCreateTime")
	private Timestamp groupOrderCreateTime;

	@Column(name = "groupOrderSubmitTime")
	private Timestamp groupOrderSubmitTime;

//	@Column(name = "holderID")
//	private	Integer	holderID;

	@ManyToOne
	@JoinColumn(name = "holderID")
	private UserInfo userInfo;

	@Column(name = "groupTotalPrice")
	private Integer groupTotalPrice;

	@Column(name = "deliveredBlob", columnDefinition = "longblob")
	private byte[] deliveredBlob;

	public GroupOrder() {
		super();
	}

	public GroupOrder(Integer groupOrderID, DinerInfo dinerInfo, BuildingInfo buildingInfo, String orderStatus,
			Timestamp groupOrderCreateTime, Timestamp groupOrderSubmitTime, UserInfo userInfo, Integer groupTotalPrice,
			byte[] deliveredBlob) {
		super();
		this.groupOrderID = groupOrderID;
		this.dinerInfo = dinerInfo;
		this.buildingInfo = buildingInfo;
		this.orderStatus = orderStatus;
		this.groupOrderCreateTime = groupOrderCreateTime;
		this.groupOrderSubmitTime = groupOrderSubmitTime;
		this.userInfo = userInfo;
		this.groupTotalPrice = groupTotalPrice;
		this.deliveredBlob = deliveredBlob;
	}

	public Integer getGroupOrderID() {
		return groupOrderID;
	}

	public void setGroupOrderID(Integer groupOrderID) {
		this.groupOrderID = groupOrderID;
	}

	public DinerInfo getDinerInfo() {
		return dinerInfo;
	}

	public void setDinerInfo(DinerInfo dinerInfo) {
		this.dinerInfo = dinerInfo;
	}

	public BuildingInfo getBuildingInfo() {
		return buildingInfo;
	}

	public void setBuildingInfo(BuildingInfo buildingInfo) {
		this.buildingInfo = buildingInfo;
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

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
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
		return "GroupOrder [groupOrderID=" + groupOrderID + ", dinerInfo=" + dinerInfo + ", buildingInfo="
				+ buildingInfo + ", orderStatus=" + orderStatus + ", groupOrderCreateTime=" + groupOrderCreateTime
				+ ", groupOrderSubmitTime=" + groupOrderSubmitTime + ", userInfo=" + userInfo + ", groupTotalPrice="
				+ groupTotalPrice + ", deliveredBlob=" + Arrays.toString(deliveredBlob) + "]";
	}

}
