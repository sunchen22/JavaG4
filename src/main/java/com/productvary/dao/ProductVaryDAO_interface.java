package com.productvary.dao;

import java.util.List;

import com.productvary.entity.ProductVary;


public interface ProductVaryDAO_interface {

	public void insert(ProductVary productVary);
	
	public void update(ProductVary productVary);

	public void delete(Integer productVaryID);

	public ProductVary findByPrimaryKey(Integer productVaryID);

	public List<ProductVary> getAll();

	List<ProductVary> getByPID(Integer productID);

	List<ProductVary> getByType(Integer varyTypeID);
}
