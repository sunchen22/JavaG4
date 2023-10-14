package com.product.model;

import java.util.List;
import java.util.Map;

public interface ProductDAO_interface {

	public void insert(ProductVO productVO);

	public void update(ProductVO productVO);

	public void delete(Integer productID);

	public ProductVO findByPrimaryKey(Integer productID);

	public List<ProductVO> getAll();

//	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//	public List<ProductVO> getAll(Map<String, String[]> map);
}
