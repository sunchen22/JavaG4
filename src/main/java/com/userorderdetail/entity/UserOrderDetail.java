package com.userorderdetail.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.grouporder.entity.GroupOrder;

@Entity
@Table(name = "userorderdetail")
public class UserOrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userOrderItemID", updatable = false)
	private	Integer	userOrderItemID;
	
	@Column(name = "productID")
	private	Integer	productID;
	
	@Column(name = "productQuantity")
	private	Integer	productQuantity;
	
	@Column(name = "userItemPrice")
	private	Integer userItemPrice;
	
	@Column(name = "userID")
	private	Integer	userID;

//	@Column(name = "groupOrderID")
//	private	Integer	groupOrderID;
	
	@ManyToOne
	@JoinColumn(name = "groupOrderID" , referencedColumnName = "groupOrderID")
	private GroupOrder groupOrder;
	
	@Column(name = "userPaymentTime")
	private	Timestamp userPaymentTime;

	public UserOrderDetail() {
		super();
	}

	public UserOrderDetail(Integer userOrderItemID, Integer productID, Integer productQuantity, Integer userItemPrice,
			Integer userID, GroupOrder groupOrder, Timestamp userPaymentTime) {
		super();
		this.userOrderItemID = userOrderItemID;
		this.productID = productID;
		this.productQuantity = productQuantity;
		this.userItemPrice = userItemPrice;
		this.userID = userID;
		this.groupOrder = groupOrder;
		this.userPaymentTime = userPaymentTime;
	}

	public Integer getUserOrderItemID() {
		return userOrderItemID;
	}

	public void setUserOrderItemID(Integer userOrderItemID) {
		this.userOrderItemID = userOrderItemID;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Integer getUserItemPrice() {
		return userItemPrice;
	}

	public void setUserItemPrice(Integer userItemPrice) {
		this.userItemPrice = userItemPrice;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public GroupOrder getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}

	public Timestamp getUserPaymentTime() {
		return userPaymentTime;
	}

	public void setUserPaymentTime(Timestamp userPaymentTime) {
		this.userPaymentTime = userPaymentTime;
	}

	@Override
	public String toString() {
		return "UserOrderDetail [userOrderItemID=" + userOrderItemID + ", productID=" + productID + ", productQuantity="
				+ productQuantity + ", userItemPrice=" + userItemPrice + ", userID=" + userID + ", groupOrder="
				+ groupOrder + ", userPaymentTime=" + userPaymentTime + "]";
	}
		
}
