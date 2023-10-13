package com.product.model;

import java.sql.Blob;
import java.sql.Timestamp;

public class ProductVO implements java.io.Serializable {

	private Integer productID;
	private Integer dinerID;
	private String productName;
	private Integer productPrice;
	private Integer productTypeID;
	private Integer productDailyStock;
	private Timestamp productReleaseTime;
	private Blob productBlob1;
	private Blob productBlob2;
	private Blob productBlob3;
	private String productRemark;
	private String productStatus;

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public Integer getDinerID() {
		return dinerID;
	}

	public void setDinerID(Integer dinerID) {
		this.dinerID = dinerID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductTypeID() {
		return productTypeID;
	}

	public void setProductTypeID(Integer productTypeID) {
		this.productTypeID = productTypeID;
	}

	public Integer getProductDailyStock() {
		return productDailyStock;
	}

	public void setProductDailyStock(Integer productDailyStock) {
		this.productDailyStock = productDailyStock;
	}

	public Timestamp getProductReleaseTime() {
		return productReleaseTime;
	}

	public void setProductReleaseTime(Timestamp productReleaseTime) {
		this.productReleaseTime = productReleaseTime;
	}

	public Blob getProductBlob1() {
		return productBlob1;
	}

	public void setProductBlob1(Blob productBlob1) {
		this.productBlob1 = productBlob1;
	}

	public Blob getProductBlob2() {
		return productBlob2;
	}

	public void setProductBlob2(Blob productBlob2) {
		this.productBlob2 = productBlob2;
	}

	public Blob getProductBlob3() {
		return productBlob3;
	}

	public void setProductBlob3(Blob productBlob3) {
		this.productBlob3 = productBlob3;
	}

	public String getProductRemark() {
		return productRemark;
	}

	public void setProductRemark(String productRemark) {
		this.productRemark = productRemark;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

}
