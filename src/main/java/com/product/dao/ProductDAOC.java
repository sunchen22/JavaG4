package com.product.dao;

import java.util.List;

import com.product.entity.Product;

public interface ProductDAOC {
	List<Product> getAll(Integer dinerID);
	int down(Integer dinerID);
}