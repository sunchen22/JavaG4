package com.producttype.dao;

import java.util.List;
import java.util.Map;

import com.producttype.entity.ProductType;

public interface ProductTypeDAO {

	int insert(ProductType entity);

	int update(ProductType entity);
	
	int delete(Integer id);
	 
	ProductType getById(Integer id);
	
	List<ProductType> getAll();
	
	List<ProductType> getByCompositeQuery(Map<String, String> map);
	
	List<ProductType> getAll(int currentPage);
	
	long getTotal();
}
