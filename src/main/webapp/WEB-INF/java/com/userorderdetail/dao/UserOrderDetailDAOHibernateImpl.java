package com.userorderdetail.dao;

import java.util.List;

import org.hibernate.Session;

import com.userorderdetail.entity.UserOrderDetail;
import com.util.HibernateUtil;

public class UserOrderDetailDAOHibernateImpl implements UserOrderDetailDAO{

	@Override
	public int add(UserOrderDetail userOrderDetail) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(userOrderDetail);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return -1;
	}

	@Override
	public int update(UserOrderDetail userOrderDetail) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			session.update(userOrderDetail);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return -1;
	}

	@Override
	public int delete(Integer userOrderItemID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			UserOrderDetail userOrderDetail = session.get(UserOrderDetail.class, userOrderItemID);
			if(userOrderDetail != null) {
				session.delete(userOrderDetail);
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
	public UserOrderDetail findByPK(Integer userOrderItemID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			UserOrderDetail userOrderDetail = session.get(UserOrderDetail.class, userOrderItemID);
			session.getTransaction().commit();
			return userOrderDetail;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return null;
	}

	@Override
	public List<UserOrderDetail> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			List<UserOrderDetail> list = session.createQuery("from UserOrderDetail", UserOrderDetail.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return null;
	}

}
