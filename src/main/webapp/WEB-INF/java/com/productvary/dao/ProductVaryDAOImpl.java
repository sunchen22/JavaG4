package com.productvary.dao;

import static util.Constants.PAGE_MAX_RESULT;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.producttype.entity.ProductType;
import com.productvary.entity.ProductVary;

public class ProductVaryDAOImpl implements ProductVaryDAO{
	
	private SessionFactory factory;

	public ProductVaryDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(ProductVary entity) {
		// TODO Auto-generated method stub
		return  (Integer) getSession().save(entity);
	}

	@Override
	public int update(ProductVary entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer id) {
		ProductVary productvary = getSession().get(ProductVary.class, id);
		if (productvary != null) {
			getSession().delete(productvary);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}

	@Override
	public ProductVary getById(Integer id) {
		return getSession().get(ProductVary.class, id);
	}

	@Override
	public List<ProductVary> getAll() {
		return getSession().createQuery("from ProductVary", ProductVary.class).list();
	}

	@Override
	public List<ProductVary> getByCompositeQuery(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVary> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("from ProductVary", ProductVary.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from ProductVary", Long.class).uniqueResult();

	}

}
