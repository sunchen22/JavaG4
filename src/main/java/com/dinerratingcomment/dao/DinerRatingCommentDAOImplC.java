package com.dinerratingcomment.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.dinerinfo.entity.DinerInfo;
import com.dinerratingcomment.entity.DinerRatingComment;


import util.HibernateUtil;

public class DinerRatingCommentDAOImplC implements DinerRatingCommentDAOC {
	
	public int delete(Integer commentID) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			DinerRatingComment drc = session.get(DinerRatingComment.class , commentID);
			if(drc != null) {
			
			session.delete(drc);
			}
			session.getTransaction().commit();	
		
		
			return 0;
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
						
	}

	
	
	public DinerRatingComment findByPK(Integer commentID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
		session.beginTransaction();
		DinerRatingComment drc = session.get(DinerRatingComment.class, commentID);
		
		
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return null;
		
		
		
		
		
	}
	
	
	
//	public DinerInfo findByPK1(Integer dinerID) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//		session.beginTransaction();
//		DinerInfo dif = session.get(DinerInfo.class ,dinerID);
//			for(DinerRatingComment drc : dif.getDinerID()) {
//				System.out.println(drc.getDinerinfo());
//				
//				
//			}
//			return dif;
//	
//		}catch(Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		
//		return null;
//		
//		
//		
//		
//		
//	}
	
	
	
	public List<DinerRatingComment> getAll(Integer dinerID){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
		session.beginTransaction();
//		List<DinerRatingComment> list = session.createQuery(" from DinerRatingComment " , DinerRatingComment.class).list();
//		NativeQuery<DinerRatingComment> query = session.createNativeQuery("SELECT * FROM DinerRatingComment where dinerid = ?0", DinerRatingComment.class).;
//																		
//		List<DinerRatingComment> list = query.list();
		
		List<DinerRatingComment> list = session.createQuery("from DinerRatingComment where dinerid = ?0 ", DinerRatingComment.class)
		.setParameter(0, dinerID)
		.list();
		
		
		
//		Query<DinerRatingComment> list = 
//		session.createQuery("from DinerRatingComment where dinerid = ?0 ", DinerRatingComment.class)
//		.setParameter(0,dinerID);
//		.list();
				
		
		
		session.getTransaction().commit();
		return list;
		
	}catch(Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
		}
		return null;
	}
		
		
		
		
	}
	

