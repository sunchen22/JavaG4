package com.producttype.dao;

import java.util.List;
import java.util.Map;

import com.producttype.entity.ProductType;

public interface ProductTypeDAO_interface {

	void insert(ProductType entity);

	void delete(Integer id);

	List<ProductType> getAll();

}
