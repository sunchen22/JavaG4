package com.product.dao;

import static util.Constants.PAGE_MAX_RESULT;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.product.entity.Product;

public class ProductDAOImpl implements ProductDAO {

	private SessionFactory factory;

	public ProductDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(Product entity) {
		
		return  (Integer) getSession().save(entity);
	}

	@Override
	public int update(Product entity) {
		
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer id) {
		Product product = getSession().get(Product.class, id);
		if (product != null) {
			getSession().delete(product);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}

	@Override
	public Product getById(Integer id) {
		return getSession().get(Product.class, id);
	}

	@Override
	public List<Product> getAll() {
		return getSession().createQuery("from Product", Product.class).list();
	}

	@Override
	public List<Product> getByCompositeQuery(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("from Product", Product.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from Product", Long.class).uniqueResult();

	}

}
