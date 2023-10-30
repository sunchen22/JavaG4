package com.userorderdetail.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.userorderdetail.entity.UserOrderDetail;

public class UserOrderDetailDAOHibernateImpl implements UserOrderDetailDAO{
	// One SessionFactory(which is thread-safe) for one DAO
	private SessionFactory factory;

	public UserOrderDetailDAOHibernateImpl(SessionFactory factory) {
		this.factory = factory;
	}

	// Each CRUD method in this DAO should get its own Session(which is not
	// thread-safe)
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int add(UserOrderDetail userOrderDetail) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//			session.beginTransaction();
			Integer id = (Integer) getSession().save(userOrderDetail);
//			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return -1;
	}

	@Override
	public int update(UserOrderDetail userOrderDetail) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//			session.beginTransaction();
			getSession().update(userOrderDetail);
//			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return -1;
	}

	@Override
	public int delete(Integer userOrderItemID) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//			session.beginTransaction();
			UserOrderDetail userOrderDetail = getSession().get(UserOrderDetail.class, userOrderItemID);
			if(userOrderDetail != null) {
				getSession().delete(userOrderDetail);
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
	public UserOrderDetail findByPK(Integer userOrderItemID) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//			session.beginTransaction();
			UserOrderDetail userOrderDetail = getSession().get(UserOrderDetail.class, userOrderItemID);
//			session.getTransaction().commit();
			return userOrderDetail;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return null;
	}

	@Override
	public List<UserOrderDetail> getAll() {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//			session.beginTransaction();
			List<UserOrderDetail> list = getSession().createQuery("from UserOrderDetail", UserOrderDetail.class).list();
//			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return null;
	}
	
	@Override
	public List<Object[]> findByGroupOrderAndUser(Integer groupOrderID, Integer userID) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			String sql = "SELECT p.productName, uod.productQuantity, uod.userItemPrice, "
					+ "(SELECT productVaryDes FROM productvary AS pv WHERE pv.productVaryID = uodv.productVaryID1) AS 'productVary1', "
					+ "(SELECT productVaryDes FROM productvary AS pv WHERE pv.productVaryID = uodv.productVaryID2) AS 'productVary2', "
					+ "(SELECT productVaryDes FROM productvary AS pv WHERE pv.productVaryID = uodv.productVaryID3) AS 'productVary3', "
					+ "(SELECT productVaryDes FROM productvary AS pv WHERE pv.productVaryID = uodv.productVaryID4) AS 'productVary4' "
					+ "FROM UserOrderDetail AS uod "
					+ "LEFT JOIN UserOrderDetailVary AS uodv ON uod.userOrderItemID = uodv.userOrderItemID "
					+ "LEFT JOIN Product AS p ON uod.productID = p.productID "
					+ "WHERE uod.groupOrderID = " + String.valueOf(groupOrderID) + " AND uod.userID = " + String.valueOf(userID)
					+ " ORDER BY uod.userOrderItemID";
			
			
			@SuppressWarnings("unchecked")
			List<Object[]> results = getSession().createNativeQuery(sql).list();
//			session.getTransaction().commit();
			
			return results;
			
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return null;
	}

}
