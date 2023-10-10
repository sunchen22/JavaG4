package com.dinerratingcomment.dao;

import java.util.List;

import org.hibernate.Session;

import com.dinerratingcomment.entity.DinerRatingComment;


import util.HibernateUtil;

public class DinerRatingCommentDAO implements DinerRatingCommentDAO_Interface{

	public DinerRatingCommentDAO() {
		
	}

	@Override
	public int insert(DinerRatingComment dinerRatingComment) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer insertID = (Integer) session.save(dinerRatingComment);
			session.getTransaction().commit();
			return insertID;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}
	

	@Override
	public int update(DinerRatingComment dinerRatingComment) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(dinerRatingComment);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer dinerRatingCommentID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			DinerRatingComment dinerRatingComment = session.get(DinerRatingComment.class, dinerRatingCommentID);
			if (dinerRatingComment != null) {
				session.delete(dinerRatingCommentID);
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
	public DinerRatingComment findByPrimaryKey(Integer dinerRatingCommentID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			DinerRatingComment dinerRatingComment = session.get(DinerRatingComment.class, dinerRatingCommentID);
			session.getTransaction().commit();
			return dinerRatingComment;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<DinerRatingComment> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<DinerRatingComment> list = session.createQuery("from DinerRatingComment", DinerRatingComment.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
}
