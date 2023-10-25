package com.product.service;

import java.util.List;

import com.product.dao.ProductDAO;
import com.product.dao.ProductDAO_interface;
import com.product.entity.Product;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
//		dao = new EmpJDBCDAO();
		dao = new ProductDAO();
	}

	public Product addProduct(Integer dinerID, String productName, Integer productPrice, Integer productTypeID,
			Integer productDailyStock, String productRemark,byte[] productBlob1,byte[] productBlob2,byte[] productBlob3) {

		Product product = new Product();

		product.setDinerID(dinerID);
		product.setProductName(productName);
		product.setProductPrice(productPrice);
		product.setProductTypeID(productTypeID);
		product.setProductDailyStock(productDailyStock);
		product.setProductRemark(productRemark);
		product.setProductBlob1(productBlob1);
		product.setProductBlob2(productBlob2);
		product.setProductBlob3(productBlob3);
		dao.insert(product);

		return product;
	}

	public Product updateProduct(Integer productID, Integer dinerID, String productName, Integer productPrice,
			Integer productTypeID, Integer productDailyStock, String productRemark,byte[] productBlob1,byte[] productBlob2,byte[] productBlob3) {

		Product product = new Product();

		product.setProductID(productID);
		product.setDinerID(dinerID);
		product.setProductName(productName);
		product.setProductPrice(productPrice);
		product.setProductTypeID(productTypeID);
		product.setProductDailyStock(productDailyStock);
		product.setProductRemark(productRemark);
		product.setProductBlob1(productBlob1);
		product.setProductBlob2(productBlob2);
		product.setProductBlob3(productBlob3);

		dao.update(product);

		return dao.findByPrimaryKey(productID);
	}

	public void deleteProduct(Integer productID) {
		dao.delete(productID);
	}
	

	public Product getOneProduct(Integer productID) {
		return dao.findByPrimaryKey(productID);
	}
	
	//從類型找
	public Product getOneProductByType(Integer productTypeID) {
		return dao.findByPrimaryKey(productTypeID);
	}

	
	public List<Product> getAll() {
		return dao.getAll();
	}
	
	public List<Product> getByType() {
		return dao.getByType();
	}
	//下架
	public void offShelveProduct(Integer productID,String productStatus) {
		Product product = new Product();

		product.setProductID(productID);

		product.setProductStatus(productStatus);

		dao.offshelve(product);

	}
}
