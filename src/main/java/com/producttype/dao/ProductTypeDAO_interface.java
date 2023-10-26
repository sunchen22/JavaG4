package com.producttype.dao;

import java.util.List;
import java.util.Set;

import com.product.entity.Product;
import com.producttype.entity.ProductType;

public interface ProductTypeDAO_interface {

	void insert(ProductType entity);

	void delete(Integer id);

	List<ProductType> getAll();
    //查詢某部門的員工(一對多)(回傳 Set)
//    public Set<Product> getProductsByType(Integer deptno);

	public ProductType findByPrimaryKey(Integer productTypeID);
	
	
}
