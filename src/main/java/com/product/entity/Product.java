package com.product.entity;

import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productID", updatable = false)
	private Integer productID;

	@Column(name = "dinerID")
	private Integer dinerID;

	@Column(name = "productName")
	private String productName;

	@Column(name = "productPrice")
	private Integer productPrice;

	@Column(name = "productTypeID")
	private Integer productTypeID;

	@Column(name = "productDailyStock")
	private Integer productDailyStock;

	@Column(name = "productReleaseTime", insertable = false)
	private Timestamp productReleaseTime;

	@Column(name = "productBlob1" , columnDefinition = "longblob")
	private byte[] productBlob1;

	@Column(name = "productBlob2" , columnDefinition = "longblob")
	private byte[] productBlob2;

	@Column(name = "productBlob3" , columnDefinition = "longblob")
	private byte[] productBlob3;

	@Column(name = "productRemark")
	private String productRemark;
	
	@Column(name = "productStatus")
	private String productStatus;
	
	

	public Product() {
		super();
	}

	public Product(Integer productID, Integer dinerID, String productName, Integer productPrice, Integer productTypeID,

			Integer productDailyStock, Timestamp productReleaseTime, byte[] productBlob1, byte[] productBlob2, byte[] productBlob3,

			

			String productRemark) {
		super();
		this.productID = productID;
		this.dinerID = dinerID;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productTypeID = productTypeID;
		this.productDailyStock = productDailyStock;
		this.productReleaseTime = productReleaseTime;
		this.productBlob1 = productBlob1;
		this.productBlob2 = productBlob2;
		this.productBlob3 = productBlob3;
		this.productRemark = productRemark;
	}


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



	public byte[] getProductBlob1() {

		return productBlob1;
	}







	public void setProductBlob1(byte[] productBlob1) {
		this.productBlob1 = productBlob1;
	}






	public byte[] getProductBlob2() {
		return productBlob2;
	}





	public void setProductBlob2(byte[] productBlob2) {
		this.productBlob2 = productBlob2;
	}






	public byte[] getProductBlob3() {
		return productBlob3;
	}






	public void setProductBlob3(byte[] productBlob3) {
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



	@Override
	public String toString() {
		return "Product [productID=" + productID + ", dinerID=" + dinerID + ", productName=" + productName
				+ ", productPrice=" + productPrice + ", productTypeID=" + productTypeID + ", productDailyStock="
				+ productDailyStock + ", productReleaseTime=" + productReleaseTime + ", productBlob1=" + productBlob1
				+ ", productBlob2=" + productBlob2 + ", productBlob3=" + productBlob3 + ", productRemark="
				+ productRemark + ", productStatus=" + productStatus + "]";
	}


}

