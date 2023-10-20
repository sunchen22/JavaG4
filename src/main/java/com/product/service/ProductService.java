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
			Integer productDailyStock, String productRemark) {

		Product product = new Product();

		product.setDinerID(dinerID);
		product.setProductName(productName);
		product.setProductPrice(productPrice);
		product.setProductTypeID(productTypeID);
		product.setProductDailyStock(productDailyStock);
		product.setProductRemark(productRemark);
		dao.insert(product);

		return product;
	}

	public Product updateProduct(Integer productID, Integer dinerID, String productName, Integer productPrice,
			Integer productTypeID, Integer productDailyStock, String productRemark) {

		Product product = new Product();

		product.setProductID(productID);
		product.setDinerID(dinerID);
		product.setProductName(productName);
		product.setProductPrice(productPrice);
		product.setProductTypeID(productTypeID);
		product.setProductDailyStock(productDailyStock);
		product.setProductRemark(productRemark);
		dao.update(product);

		return dao.findByPrimaryKey(productID);
	}

	public void deleteProduct(Integer productID) {
		dao.delete(productID);
	}

	public Product getOneProduct(Integer productID) {
		return dao.findByPrimaryKey(productID);
	}

	public List<Product> getAll() {
		return dao.getAll();
	}
}
