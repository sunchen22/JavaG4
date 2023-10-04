package com.producttype.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ProductType")
public class ProductType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productTypeID", updatable = false)
	private Integer productTypeID;
	
	@Column(name = "productTypeDes")
	private String productTypeDes;

	public ProductType() {
		super();
	}

	public ProductType(Integer productTypeID, String productTypeDes) {
		super();
		this.productTypeID = productTypeID;
		this.productTypeDes = productTypeDes;
	}

	public Integer getproductTypeID() {
		return productTypeID;
	}

	public void setgetproductTypeID() {
		this.productTypeID = productTypeID;
	}

	public String getproductTypeDes() {
		return productTypeDes;
	}

	public void setproductTypeDes() {
		this.productTypeDes = productTypeDes;
	}

	@Override
	public String toString() {
		return "ProductType [productTypeID=" + productTypeID + ", productTypeDes=" + productTypeDes + "]";
	}

}
