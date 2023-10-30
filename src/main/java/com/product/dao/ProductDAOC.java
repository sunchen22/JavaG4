package com.product.dao;

import java.util.List;

import com.product.entity.Product;

public interface ProductDAOC {
	List<Product> getAll(Integer dinerID);
	Product down(Integer ProductID);
	byte[] getImg(Integer productID);
	byte[] getImg2(Integer productID);
	byte[] getImg3(Integer productID);
}
