package com.product.dao;

import java.util.List;
import java.util.Map;

import com.product.entity.Product;

public interface ProductDAO {

	int insert(Product entity);

	int update(Product entity);
	
	int delete(Integer id);
	 
	Product getById(Integer id);
	
	List<Product> getAll();
	
	List<Product> getByCompositeQuery(Map<String, String> map);
	
	List<Product> getAll(int currentPage);
	
	long getTotal();
}
