package com.product.dao;

import java.util.List;
import java.util.Map;

import com.product.entity.Product;

public interface ProductDAO {
	List<Product> getAll(Integer dinerID);
	int down(Integer dinerID);
	List<Product> getByCompositeQuery(Map<String, String> map);
	List<Product> getAll();
	Product getById(Integer id);
	int delete(Integer id);
	int update(Product entity);
	int insert(Product entity);
}
