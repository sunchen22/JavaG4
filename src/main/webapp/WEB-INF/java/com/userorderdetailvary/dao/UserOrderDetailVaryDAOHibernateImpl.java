package com.userorderdetailvary.dao;

import java.util.List;

import org.hibernate.Session;

import com.userorderdetailvary.entity.UserOrderDetailVary;
import com.util.HibernateUtil;

public class UserOrderDetailVaryDAOHibernateImpl implements UserOrderDetailVaryDAO{

	@Override
	public int add(UserOrderDetailVary userOrderDetailVary) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(userOrderDetailVary);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return -1;
	}

	@Override
	public int update(UserOrderDetailVary userOrderDetailVary) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			session.update(userOrderDetailVary);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return -1;
	}

	@Override
	public int delete(Integer userOrderDetailVaryID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			UserOrderDetailVary userOrderDetailVary = session.get(UserOrderDetailVary.class, userOrderDetailVaryID);
			if (userOrderDetailVary != null) {
				session.delete(userOrderDetailVary);
			} 
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return -1;
	}

	@Override
	public UserOrderDetailVary findByPK(Integer userOrderDetailVaryID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			UserOrderDetailVary userOrderDetailVary = session.get(UserOrderDetailVary.class, userOrderDetailVaryID);
			session.getTransaction().commit();
			return userOrderDetailVary;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return null;
	}

	@Override
	public List<UserOrderDetailVary> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			List<UserOrderDetailVary> list = session.createQuery("from UserOrderDetailVary", UserOrderDetailVary.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return null;
	}

}
