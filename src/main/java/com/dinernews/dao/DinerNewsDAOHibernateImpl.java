package com.dinernews.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import com.dinernews.entity.DinerNews;

import util.HibernateUtil;

public class DinerNewsDAOHibernateImpl implements DinerNewsDAO {
	@Override
	public int add(DinerNews dns) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

//			session.beginTransaction();
			session.save(dns);
//			session.getTransaction().commit();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return -1;

	}
	
	@Override
	public DinerNews update() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//			session.beginTransaction();
			DinerNews dn = session.get(DinerNews.class , 1);
//			session.getTransaction().commit();
			
			return dn;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return null;
	}
	@Override
	public int down(Integer dinerNewsID) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			
			DinerNews dns = session.get(DinerNews.class , dinerNewsID);
			
//			session.getTransaction().commit();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}
	
	@Override
	public List<DinerNews> getAll() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			
			List<DinerNews> list = session.createQuery("from DinerNews where dinerNewsStatus = ?0 " ,DinerNews.class)
					.setParameter(0, 1)
					.list();
			
//			session.getTransaction().commit();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	
	
	
	
	
	
	
}
