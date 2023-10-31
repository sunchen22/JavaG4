package com.usernews.dao;

import java.util.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.usernews.entity.UserNews;

import util.HibernateUtil;

public class UserNewsDAOImpl implements UserNewsDAO {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用，所以可設定成實體變數
	private SessionFactory factory;

	public UserNewsDAOImpl() {
	}

	public UserNewsDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(UserNews entity) {
		// 回傳給 service 剛新增成功的自增主鍵值
		try {
			return (Integer) getSession().save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserNews insert失敗");
			return -1;
		}
	}

	@Override
	public int update(UserNews userNews) {
		try {
			System.out.println("UserNews 中的userNews"+userNews);
			getSession().saveOrUpdate(userNews);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserNews update失敗");
			return -1;
		}
	}

	@Override
	public int delete(Integer id) {
		UserNews usernews = getSession().get(UserNews.class, id);
		if (usernews != null) {
			getSession().delete(usernews);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}

	@Override
	public UserNews getById(Integer id) {
		try {
			UserNews usernews = getSession().get(UserNews.class, id);
//			System.out.println("getById成功");
			return usernews;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getById失敗");
		}
		return null;
	}

	@Override
	public List<UserNews> getAll() {
		return getSession().createQuery("from UserNews where userNewsStatus = 1 or userNewsStatus = 0", UserNews.class).list();
	}

//	@Override
//	public List<UserInfo> getByCompositeQuery(Map<String, String> map) {
//		if (map.size() == 0)
//			return getAll();
//
//		CriteriaBuilder builder = getSession().getCriteriaBuilder();
//		CriteriaQuery<UserInfo> criteria = builder.createQuery(UserInfo.class);
//		Root<UserInfo> root = criteria.from(UserInfo.class);
//
//		List<Predicate> predicates = new ArrayList<>();
//
//		// 複合查詢:利用MAP /或是sql指令的串接
//		for (Map.Entry<String, String> row : map.entrySet()) {
//			if ("username".equals(row.getKey())) {
//				predicates.add(builder.like(root.get("userName"), "%" + row.getValue() + "%"));
//			}
//
//			if ("userid".equals(row.getKey())) {
//				predicates.add(builder.equal(root.get("userID"), row.getValue()));
//			}
//
//			if ("useremail".equals(row.getKey())) {
//				predicates.add(builder.like(root.get("userAccount"), "%" + row.getValue() + "%"));
//			}
//			if ("userphone".equals(row.getKey())) {
//				predicates.add(builder.like(root.get("userPhone"), "%" + row.getValue() + "%"));
//			}
//		}
//
//		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
//		criteria.orderBy(builder.asc(root.get("userID")));
//		TypedQuery<UserInfo> query = getSession().createQuery(criteria);
//
//		return query.getResultList();
//	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from UserNews", Long.class).uniqueResult(); // 單筆查詢.uniqueResult()
	}
	
	// add by tz
	@Override
	public List<UserNews> getAllbyStatus() {
		return getSession().createQuery("from UserNews where userNewsStatus = 1", UserNews.class).list();
	}
	
	

}
