package com.product.dao;

import java.util.List;

import com.product.entity.Product;

public interface ProductDAO_interface {

	public void insert(Product product);

	public void update(Product product);

	public void delete(Integer productID);

	public Product findByPrimaryKey(Integer productID);

	public List<Product> getAll();

//	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//	public List<ProductVO> getAll(Map<String, String[]> map);
}
