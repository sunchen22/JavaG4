package com.product.service;

import static util.Constants.PAGE_MAX_RESULT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.product.dao.ProductDAO;
import com.product.dao.ProductDAOImpl;
import com.product.entity.Product;
import util.HibernateUtil;

// 搭配 JSP / Thymeleaf 後端渲染畫面，將交易動作至於 view filter
public class ProductServiceImpl implements ProductService {
	// 一個 service 實體對應一個 dao 實體
	private ProductDAO dao;
	
	public ProductServiceImpl() {
		dao = new ProductDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct(Integer productID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product getProductByProductID(Integer productID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProducts(int currentPage) {
		return dao.getAll(currentPage);
	}

	@Override
	public List<Product> getProductsByCompositeQuery(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();
		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		
		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0];//getValue拿到一個String陣列，接著[0]取得第一個元素檢查
			if (value.isEmpty() || value == null) {
				continue;
			}
			query.put(key, value);
		}
		
		System.out.println(query);
		
		return dao.getByCompositeQuery(query);
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		// 計算Emp數量每頁3筆的話總共有幾頁
		int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

}
