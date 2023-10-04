package com.grouporder.dao;

import java.util.List;

import org.hibernate.Session;

import com.util.HibernateUtil;

import com.grouporder.entity.GroupOrder;

public class GroupOrderDAOHibernateImpl implements GroupOrderDAO{

	@Override
	public int add(GroupOrder groupOrder) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(groupOrder);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return -1;
	}

	@Override
	public int update(GroupOrder groupOrder) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			session.update(groupOrder);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return -1;
	}

	@Override
	public int delete(Integer groupOrderID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			GroupOrder groupOrder = session.get(GroupOrder.class, groupOrderID);
			if (groupOrder != null) {
				session.delete(groupOrder);
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
	public GroupOrder findByPK(Integer groupOrderID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			GroupOrder groupOrder = session.get(GroupOrder.class, groupOrderID);
			session.getTransaction().commit();
			return groupOrder;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return null;
	}

	@Override
	public List<GroupOrder> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			List<GroupOrder> list = session.createQuery("from GroupOrder", GroupOrder.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return null;
	}

}
