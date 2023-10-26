package com.producttype.service;

import java.util.List;


import com.producttype.dao.ProductTypeDAO;
import com.producttype.dao.ProductTypeDAO_interface;
import com.producttype.entity.ProductType;

public class ProductTypeService {

	private ProductTypeDAO_interface dao;

	public ProductTypeService() {

		dao = new ProductTypeDAO();
	}

	public ProductType addProductType(String productTypeDes) {

		ProductType PT = new ProductType();

		PT.setProductTypeDes(productTypeDes);

		dao.insert(PT);

		return PT;
	}

	public void deleteProductType(Integer productTypeID) {
		dao.delete(productTypeID);
	}

	public List<ProductType> getAll() {
		return dao.getAll();
	}

	public ProductType getOneProductType(Integer productTypeID) {
		return dao.findByPrimaryKey(productTypeID);
	}
}
