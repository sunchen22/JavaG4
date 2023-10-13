package com.producttype.dao;

import static util.Constants.PAGE_MAX_RESULT;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.product.entity.Product;
import com.producttype.entity.ProductType;

public class ProductTypeDAOImpl implements ProductTypeDAO{
	
	private SessionFactory factory;

	public ProductTypeDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(ProductType entity) {
		// TODO Auto-generated method stub
		return  (Integer) getSession().save(entity);
	}

	@Override
	public int update(ProductType entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer id) {
		ProductType producttype = getSession().get(ProductType.class, id);
		if (producttype != null) {
			getSession().delete(producttype);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}

	@Override
	public ProductType getById(Integer id) {
		return getSession().get(ProductType.class, id);
	}

	@Override
	public List<ProductType> getAll() {
		return getSession().createQuery("from ProductType", ProductType.class).list();
	}

	@Override
	public List<ProductType> getByCompositeQuery(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductType> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("from ProductType", ProductType.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from ProductType", Long.class).uniqueResult();

	}

}
