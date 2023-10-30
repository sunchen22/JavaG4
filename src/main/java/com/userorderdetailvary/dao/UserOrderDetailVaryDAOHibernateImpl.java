package com.userorderdetailvary.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.userorderdetailvary.entity.UserOrderDetailVary;
import util.HibernateUtil;

public class UserOrderDetailVaryDAOHibernateImpl implements UserOrderDetailVaryDAO{
	// One SessionFactory(which is thread-safe) for one DAO
	private SessionFactory factory;

	public UserOrderDetailVaryDAOHibernateImpl(SessionFactory factory) {
		this.factory = factory;
	}

	// Each CRUD method in this DAO should get its own Session(which is not
	// thread-safe)
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int add(UserOrderDetailVary userOrderDetailVary) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//			session.beginTransaction();
			Integer id = (Integer) getSession().save(userOrderDetailVary);
//			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return -1;
	}

	@Override
	public int update(UserOrderDetailVary userOrderDetailVary) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//			session.beginTransaction();
			getSession().update(userOrderDetailVary);
//			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return -1;
	}

	@Override
	public int delete(Integer userOrderDetailVaryID) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//			session.beginTransaction();
			UserOrderDetailVary userOrderDetailVary = getSession().get(UserOrderDetailVary.class, userOrderDetailVaryID);
			if (userOrderDetailVary != null) {
				getSession().delete(userOrderDetailVary);
			} 
//			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return -1;
	}

	@Override
	public UserOrderDetailVary findByPK(Integer userOrderDetailVaryID) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//			session.beginTransaction();
			UserOrderDetailVary userOrderDetailVary = getSession().get(UserOrderDetailVary.class, userOrderDetailVaryID);
//			session.getTransaction().commit();
			return userOrderDetailVary;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return null;
	}

	@Override
	public List<UserOrderDetailVary> getAll() {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//			session.beginTransaction();
			List<UserOrderDetailVary> list = getSession().createQuery("from UserOrderDetailVary", UserOrderDetailVary.class).list();
//			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return null;
	}

}
