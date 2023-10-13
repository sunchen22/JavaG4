package com.dinernews.dao;

import org.hibernate.Session;

import com.dinernews.entity.DinerNews;

import util.HibernateUtil;

public class DinerNewsDAOHibernateImpl implements DinerNewsDAO {
	public int add(DinerNews dns) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

			session.beginTransaction();
			Integer id = (Integer) session.save(dns);
			session.getTransaction().commit();

			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;

	}

	public int update(DinerNews dns) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(dns);
			session.getTransaction().commit();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}
}
