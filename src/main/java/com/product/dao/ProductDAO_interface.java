package com.product.dao;

import java.util.List;

import com.product.entity.ProductVO;

public interface ProductDAO_interface {

	public void insert(ProductVO product);

	public void update(ProductVO product);

	public void delete(Integer productID);

	public ProductVO findByPrimaryKey(Integer productID);

	public List<ProductVO> getByType();

	public ProductVO findByPrimaryKeyType(Integer productTypeID);

	void offshelve(ProductVO product);

	List<ProductVO> getAll();
 
//	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//	public List<ProductVO> getAll(Map<String, String[]> map);
}
