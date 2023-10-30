package com.grouporder.entity;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.buildinginfo.entity.BuildingInfo;
import com.dinerinfo.entity.DinerInfo;
import com.userinfo.entity.UserInfo;
import com.userorderdetail.entity.UserOrderDetail;

@Entity
@Table(name = "grouporder")
public class GroupOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "groupOrderID")
	private Integer groupOrderID;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "groupOrderID", referencedColumnName = "groupOrderID")
//	private List<UserOrderDetail> userOrderDetails;

//	@Column(name = "dinerID")
//	private	Integer	dinerID;

	@ManyToOne
	@JoinColumn(name = "dinerID" ,updatable = false)
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
			byte[] deliveredBlob /*, List<UserOrderDetail> userOrderDetails */ ) {
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
//		this.userOrderDetails = userOrderDetails;
	}

	
	
	
	
//	public List<UserOrderDetail> getuserOrderDetails() {
//		return userOrderDetails;
//	}
//
//	public void setuserOrderDetails(List<UserOrderDetail> userOrderDetails) {
//		this.userOrderDetails = userOrderDetails;
//	}

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
