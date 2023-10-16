package com.grouporder.dao;

import java.util.List;

import org.hibernate.Session;

import com.grouporder.entity.GroupOrder;

import util.HibernateUtil;

public class GroupOrderDAOHibernateImplC implements GroupOrderDAOC{

	
	public List<GroupOrder> getAll(Integer dinerID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//			session.beginTransaction();
			List<GroupOrder> list = session.createQuery
					("from GroupOrder where dinerid = ?0 ", GroupOrder.class)
					.setParameter(0,dinerID)
					.list();
			
//			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return null;
	}
	
	public byte[] getImg(Integer groupOrderID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//			session.beginTransaction();
			GroupOrder gor = session.get(GroupOrder.class,groupOrderID);
			byte[] img = gor.getDeliveredBlob();
			
//			session.getTransaction().commit();
			return img ;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return null;
		
	}
	
	
	
	@Override
	public GroupOrder findByPK(Integer groupOrderID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

//			session.beginTransaction();

			GroupOrder gor = session.get(GroupOrder.class,groupOrderID);
//			session.getTransaction().commit();


			


			return gor;
		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}

		return null;
	}
	
}
