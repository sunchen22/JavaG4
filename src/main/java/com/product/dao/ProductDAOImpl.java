package com.product.dao;

import static util.Constants.PAGE_MAX_RESULT;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.product.entity.Product;


public class ProductDAOImpl implements ProductDAO {

	private SessionFactory factory;

	public ProductDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(Product entity) {
		// 回傳給 service 剛新增成功的自增主鍵值
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
		if (map.size() == 0)
			return getAll();

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);

		List<Predicate> predicates = new ArrayList<>();

		
//暫時不需要


		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("productID")));
		TypedQuery<Product> query = getSession().createQuery(criteria);

		return query.getResultList();
	}

	

	public long getTotal() {
		return getSession().createQuery("select count(*) from Product", Long.class).uniqueResult();
	}

	@Override
	public List<Product> getAll(Integer dinerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int down(Integer dinerID) {
		// TODO Auto-generated method stub
		return 0;
	}

}
