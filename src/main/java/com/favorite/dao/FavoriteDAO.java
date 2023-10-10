package com.favorite.dao;

import java.util.List;
import org.hibernate.Session;

import com.favorite.entity.Favorite;

import util.HibernateUtil;


public class FavoriteDAO implements FavoriteDAO_Interface{

	@Override
	public int insert(Favorite favorite) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(favorite);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return -1;
	}

	@Override
	public int update(Favorite favorite) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			session.update(favorite);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return -1;
	}

	@Override
	public int delete(Integer favoriteID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			Favorite favorite = session.get(Favorite.class, favoriteID);
			if (favorite != null) {
				session.delete(favorite);
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
	public Favorite findByPrimaryKey(Integer favoriteID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			Favorite favorite = session.get(Favorite.class, favoriteID);
			session.getTransaction().commit();
			return favorite;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return null;
	}

	@Override
	public List<Favorite> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			List<Favorite> list = session.createQuery("from Favorite", Favorite.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return null;
	}

}
