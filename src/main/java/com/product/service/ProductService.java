package com.product.service;

import java.util.List;
import java.util.Map;

import com.product.entity.Product;

public interface ProductService {
	
	Product addProduct(Product product);
	
	Product updateProduct(Product product);
	
	void deleteProduct(Integer productID);
	
	Product getProductByProductID(Integer productID);
	
	List<Product> getAllProducts(int currentPage);
	
	int getPageTotal();
	
	List<Product> getProductsByCompositeQuery(Map<String, String[]> map);
}
