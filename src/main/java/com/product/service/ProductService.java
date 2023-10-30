package com.product.service;

import java.util.List;

import com.product.dao.ProductDAO;
import com.product.dao.ProductDAO_interface;
import com.product.entity.ProductVO;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
//		dao = new EmpJDBCDAO();
		dao = new ProductDAO();
	}

	public ProductVO addProduct(Integer dinerID, String productName, Integer productPrice, Integer productTypeID,
			Integer productDailyStock, String productRemark,byte[] productBlob1,byte[] productBlob2,byte[] productBlob3) {

		ProductVO product = new ProductVO();

		product.setDinerID(dinerID);
		product.setProductName(productName);
		product.setProductPrice(productPrice);
		product.setProductTypeID(productTypeID);
		product.setProductDailyStock(productDailyStock);
		product.setProductRemark(productRemark);
		product.setProductBlob1(productBlob1);
		product.setProductBlob2(productBlob2);
		product.setProductBlob3(productBlob3);
		dao.insert(product);

		return product;
	}

	public ProductVO updateProduct(Integer productID, Integer dinerID, String productName, Integer productPrice,
			Integer productTypeID, Integer productDailyStock, String productRemark,byte[] productBlob1,byte[] productBlob2,byte[] productBlob3) {

		ProductVO product = new ProductVO();

		product.setProductID(productID);
		product.setDinerID(dinerID);
		product.setProductName(productName);
		product.setProductPrice(productPrice);
		product.setProductTypeID(productTypeID);
		product.setProductDailyStock(productDailyStock);
		product.setProductRemark(productRemark);
		product.setProductBlob1(productBlob1);
		product.setProductBlob2(productBlob2);
		product.setProductBlob3(productBlob3);
		
		dao.update(product);

		return dao.findByPrimaryKey(productID);
	}

	public void deleteProduct(Integer productID) {
		dao.delete(productID);
	}
	

	public ProductVO getOneProduct(Integer productID) {
		return dao.findByPrimaryKey(productID);
	}
	
	//從類型找
	public ProductVO getOneProductByType(Integer productTypeID) {
		return dao.findByPrimaryKey(productTypeID);
	}

	
	public List<ProductVO> getAll() {
		return dao.getAll();
	}
	
	public List<ProductVO> getByDID(Integer dinerID) {
		
		return dao.getByDID(dinerID);
	}
	//下架
	
	public ProductVO offShelveProduct(Integer productID,String productStatus) {
		ProductVO product = new ProductVO();

		product.setProductID(productID);

		product.setProductStatus(productStatus);

		dao.offshelve(product);
		return dao.findByPrimaryKey(productID);

	}
}
