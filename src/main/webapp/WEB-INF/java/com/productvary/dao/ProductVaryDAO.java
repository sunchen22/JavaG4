package com.productvary.dao;

import java.util.List;
import java.util.Map;

import com.productvary.entity.ProductVary;

public interface ProductVaryDAO {

	int insert(ProductVary entity);

	int update(ProductVary entity);
	
	int delete(Integer id);
	 
	ProductVary getById(Integer id);
	
	List<ProductVary> getAll();
	
	List<ProductVary> getByCompositeQuery(Map<String, String> map);
	
	List<ProductVary> getAll(int currentPage);
	
	long getTotal();
}
