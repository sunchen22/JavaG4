package com.productvary.service;

import java.util.List;

import com.productvary.dao.ProductVaryDAO;
import com.productvary.dao.ProductVaryDAO_interface;
import com.productvary.entity.ProductVary;

public class ProductVaryService {

	private ProductVaryDAO_interface dao;

	public ProductVaryService() {
//		dao = new EmpJDBCDAO();
		dao = new ProductVaryDAO();
	}

	public ProductVary addProductVary(Integer productID, String productVaryDes, Integer productVaryPrice,
			Integer varyTypeID) {

		ProductVary productVary = new ProductVary();

		productVary.setProductID(productID);
		productVary.setProductVaryDes(productVaryDes);
		productVary.setProductVaryPrice(productVaryPrice);
		productVary.setVaryTypeID(varyTypeID);

		dao.insert(productVary);

		return productVary;
	}

	public ProductVary updateProductVary(Integer productVaryID, Integer productID, String productVaryDes,
			Integer productVaryPrice, Integer varyTypeID) {

		ProductVary productVary = new ProductVary();

		productVary.setProductVaryID(productVaryID);
		productVary.setProductID(productID);
		productVary.setProductVaryDes(productVaryDes);
		productVary.setProductVaryPrice(productVaryPrice);
		productVary.setVaryTypeID(varyTypeID);
		dao.update(productVary);

		return dao.findByPrimaryKey(productVaryID);
	}
	
	

	public void deleteProductVary(Integer productVaryID) {
		dao.delete(productVaryID);
	}

	public ProductVary getOneProductVary(Integer productVaryID) {
		return dao.findByPrimaryKey(productVaryID);
	}

	public List<ProductVary> getAll() {
		return dao.getAll();
	}
	public List<ProductVary> getByPID(Integer productID) {
		return dao.getByPID(productID);
	}

	public List<ProductVary> getByType(Integer varyTypeID) {
		return dao.getByType(varyTypeID);
	}

}
