package com.dinerratingcomment.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.dinerratingcomment.entity.DinerRatingComment;

import util.HibernateUtil;

public class DinerRatingCommentDAO implements DinerRatingCommentDAO_Interface {

	@Override
	public int insert(DinerRatingComment dinerRatingComment) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			Integer insertID = (Integer) session.save(dinerRatingComment);
//			session.getTransaction().commit();
			return insertID;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(DinerRatingComment dinerRatingComment) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			session.update(dinerRatingComment);
//			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer dinerRatingCommentID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			DinerRatingComment dinerRatingComment = session.get(DinerRatingComment.class, dinerRatingCommentID);
			if (dinerRatingComment != null) {
				session.delete(dinerRatingCommentID);
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
	public DinerRatingComment findByPrimaryKey(Integer dinerRatingCommentID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			DinerRatingComment dinerRatingComment = session.get(DinerRatingComment.class, dinerRatingCommentID);
//			session.getTransaction().commit();
			return dinerRatingComment;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<DinerRatingComment> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			List<DinerRatingComment> list = session.createQuery("from DinerRatingComment", DinerRatingComment.class)
					.list();
//			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return null;
	}

	public List<DinerRatingComment> getAllbyDinerID(Integer dinerID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			List<DinerRatingComment> list = session
					.createQuery("from DinerRatingComment d where d.dinerInfo.dinerID = :dinerID AND d.dinerRatingCommentStatus = 1",
							DinerRatingComment.class)
					.setParameter("dinerID", dinerID).list();
//			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return null;
	}

//	public Double getAverageRatingByDinerID(Integer dinerID) {
//	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//	    String hql = "SELECT AVG(drc.dinerRating) FROM DinerRatingComment drc WHERE drc.dinerInfo.dinerID = :dinerID";
//	    Double averageRating = (Double) session.createQuery(hql).setParameter("dinerID", dinerID).uniqueResult();
//	    return averageRating;
//	}
	public Double getAverageRatingByDinerIDWithCriteria(Integer dinerID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			String hql = "SELECT AVG(drc.dinerRating) FROM DinerRatingComment drc WHERE drc.dinerInfo.dinerID = :dinerID AND drc.dinerRatingCommentStatus = 1";
	        Query query = session.createQuery(hql);
	        query.setParameter("dinerID", dinerID);
	        return (Double) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
			
		} 
//		finally {
//		    session.close();
//		}

		return null;
	}
}
