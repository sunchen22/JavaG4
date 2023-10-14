package com.userorderdetailvary.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "userorderdetailvary")
public class UserOrderDetailVary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userOrderDetailVaryID", updatable = false)
	private	Integer	userOrderDetailVaryID;
	
	@Column(name = "userOrderItemID")
	private	Integer	userOrderItemID;

	@Column(name = "productVaryID1")
	private	Integer	productVaryID1;

	@Column(name = "productVaryID2")
	private	Integer	productVaryID2;
	
	@Column(name = "productVaryID3")
	private	Integer	productVaryID3;
	
	@Column(name = "productVaryID4")
	private	Integer	productVaryID4;

	public UserOrderDetailVary() {
		super();
	}

	public UserOrderDetailVary(Integer userOrderDetailVaryID, Integer userOrderItemID, Integer productVaryID1,
			Integer productVaryID2, Integer productVaryID3, Integer productVaryID4) {
		super();
		this.userOrderDetailVaryID = userOrderDetailVaryID;
		this.userOrderItemID = userOrderItemID;
		this.productVaryID1 = productVaryID1;
		this.productVaryID2 = productVaryID2;
		this.productVaryID3 = productVaryID3;
		this.productVaryID4 = productVaryID4;
	}

	
	
	public Integer getUserOrderDetailVaryID() {
		return userOrderDetailVaryID;
	}

	public void setUserOrderDetailVaryID(Integer userOrderDetailVaryID) {
		this.userOrderDetailVaryID = userOrderDetailVaryID;
	}

	public Integer getUserOrderItemID() {
		return userOrderItemID;
	}

	public void setUserOrderItemID(Integer userOrderItemID) {
		this.userOrderItemID = userOrderItemID;
	}

	public Integer getProductVaryID1() {
		return productVaryID1;
	}

	public void setProductVaryID1(Integer productVaryID1) {
		this.productVaryID1 = productVaryID1;
	}

	public Integer getProductVaryID2() {
		return productVaryID2;
	}

	public void setProductVaryID2(Integer productVaryID2) {
		this.productVaryID2 = productVaryID2;
	}

	public Integer getProductVaryID3() {
		return productVaryID3;
	}

	public void setProductVaryID3(Integer productVaryID3) {
		this.productVaryID3 = productVaryID3;
	}

	public Integer getProductVaryID4() {
		return productVaryID4;
	}

	public void setProductVaryID4(Integer productVaryID4) {
		this.productVaryID4 = productVaryID4;
	}

	@Override
	public String toString() {
		return "UserOrderDetailVary [userOrderDetailVaryID=" + userOrderDetailVaryID + ", userOrderItemID="
				+ userOrderItemID + ", productVaryID1=" + productVaryID1 + ", productVaryID2=" + productVaryID2
				+ ", productVaryID3=" + productVaryID3 + ", productVaryID4=" + productVaryID4 + "]";
	}
	
}
