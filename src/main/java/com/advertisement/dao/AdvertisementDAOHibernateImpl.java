package com.advertisement.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import util.HibernateUtil;

import com.advertisement.entity.Advertisement;

public class AdvertisementDAOHibernateImpl implements AdvertisementDAO {

	@Override
	public int add(Advertisement advertisement) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(advertisement);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return -1;
	}

	@Override
	public int update(Advertisement advertisement) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.update(advertisement);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return -1;
	}

	@Override
	public int delete(Integer advertisementId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			Advertisement advertisement = session.get(Advertisement.class, advertisementId);
			if (advertisement != null) {
				session.delete(advertisement);
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
	public Advertisement findByPK(Integer advertisementId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			Advertisement advertisement = session.get(Advertisement.class, advertisementId);
			session.getTransaction().commit();
			return advertisement;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return null;
	}

	@Override
	public List<Advertisement> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			List<Advertisement> list = session.createQuery("from Advertisement", Advertisement.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return null;
	}

	@Override
	public List<Advertisement> getByCompositeQuery(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertisement> getAll(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
