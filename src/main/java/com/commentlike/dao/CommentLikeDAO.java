package com.commentlike.dao;

import java.util.List;

import org.hibernate.Session;

import com.commentlike.entity.CommentLike;


import util.HibernateUtil;

public class CommentLikeDAO implements CommentLikeDAO_Interface{

	public CommentLikeDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int insert(CommentLike commentLike) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer insertID = (Integer) session.save(commentLike);
			session.getTransaction().commit();
			return insertID;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(CommentLike commentLike) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(commentLike);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer commentLikeID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			CommentLike commentLike = session.get(CommentLike.class, commentLikeID);
			if (commentLike != null) {
				session.delete(commentLikeID);
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
	public CommentLike findByPrimaryKey(Integer commentLikeID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			CommentLike commentLike = session.get(CommentLike.class, commentLikeID);
			session.getTransaction().commit();
			return commentLike;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<CommentLike> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<CommentLike> list = session.createQuery("from CommentLike", CommentLike.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

}
