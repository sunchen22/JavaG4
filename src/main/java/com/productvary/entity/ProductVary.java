package com.productvary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ProductVary")
public class ProductVary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productVaryID", updatable = false)
	private Integer productVaryID;
	
	@Column(name = "productID")
	private Integer productID;
	
	@Column(name = "productVaryDes")
	private String productVaryDes;
	
	@Column(name = "productVaryPrice")
	private Integer productVaryPrice;
	
	@Column(name = "varyTypeID")
	private Integer varyTypeID;

	public ProductVary() {
		super();
	}

	public ProductVary(Integer productVaryID, Integer productID, String productVaryDes, Integer productVaryPrice,
			Integer varyTypeID) {
		super();
		this.productVaryID = productVaryID;
		this.productID = productID;
		this.productVaryDes = productVaryDes;
		this.productVaryPrice = productVaryPrice;
		this.varyTypeID = varyTypeID;
	}

	public Integer getProductVaryID() {
		return productVaryID;
	}

	public void setProductVaryID(Integer productVaryID) {
		this.productVaryID = productVaryID;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public String getProductVaryDes() {
		return productVaryDes;
	}

	public void setProductVaryDes(String productVaryDes) {
		this.productVaryDes = productVaryDes;
	}

	public Integer getProductVaryPrice() {
		return productVaryPrice;
	}

	public void setProductVaryPrice(Integer productVaryPrice) {
		this.productVaryPrice = productVaryPrice;
	}

	public Integer getVaryTypeID() {
		return varyTypeID;
	}

	public void setVaryTypeID(Integer varyTypeID) {
		this.varyTypeID = varyTypeID;
	}

	@Override
	public String toString() {
		return "ProductVary [productVaryID=" + productVaryID + ", productID=" + productID + ", productVaryDes="
				+ productVaryDes + ", productVaryPrice=" + productVaryPrice + ", varyTypeID=" + varyTypeID + "]";
	}
    public com.varytype.entity.VaryType getVaryTypeVO() {
    	com.varytype.service.VaryTypeService vtSvc = new com.varytype.service.VaryTypeService();
    	com.varytype.entity.VaryType varyType = vtSvc.getOneVaryType(varyTypeID);
	    return varyType;
    }
    
    
    public com.product.entity.ProductVO getProductVO() {
    	com.product.service.ProductService pSvc = new com.product.service.ProductService();
    	com.product.entity.ProductVO product = pSvc.getOneProduct(productID);
	    return product;
    }

}
