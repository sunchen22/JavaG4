package com.dinerratingcomment.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.dinerinfo.entity.DinerInfo;
import com.dinerratingcomment.entity.DinerRatingComment;

import test.MailService;
import util.HibernateUtil;

public class DinerRatingCommentDAOImplC implements DinerRatingCommentDAOC {
	@Override
	public int delete(Integer commentID) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			
			DinerRatingComment drc = session.get(DinerRatingComment.class , commentID);
			
			if(drc != null) {
			drc.setDinerRatingCommentStatus(2);
			MailService m = new MailService();
			m.sendMail(drc.getDinerInfo().getDinerEmail(), "樓頂揪樓咖通知", "您被評論的不實內容，經審核已由後台刪除");
			m.sendMail(drc.getUserInfo().getUserAccount(), "樓頂揪樓咖通知", "您的評論經後台審核與事實不符，已由後台刪除");
			
			
			}
//			session.getTransaction().commit();	
		
		
			return 0;
		}catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return -1;
						
	}

	
	@Override
	public DinerRatingComment findByPK(Integer commentID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//		session.beginTransaction();
		DinerRatingComment drc = session.get(DinerRatingComment.class, commentID);
		
		
		}catch(Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
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
	
	
	@Override
	public List<DinerRatingComment> getAll(Integer dinerID){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//		session.beginTransaction();
//		List<DinerRatingComment> list = session.createQuery(" from DinerRatingComment " , DinerRatingComment.class).list();
//		NativeQuery<DinerRatingComment> query = session.createNativeQuery("SELECT * FROM DinerRatingComment where dinerid = ?0", DinerRatingComment.class).;
//																		
//		List<DinerRatingComment> list = query.list();
		
		List<DinerRatingComment> list = session.createQuery("from DinerRatingComment where dinerid = ?0 and dinerratingcommentstatus = ?1 ", DinerRatingComment.class)
		.setParameter(0, dinerID)
		.setParameter(1,1)
		.list();
		
		
		
//		Query<DinerRatingComment> list = 
//		session.createQuery("from DinerRatingComment where dinerid = ?0 ", DinerRatingComment.class)
//		.setParameter(0,dinerID);
//		.list();
				
		
		
//		session.getTransaction().commit();
		return list;
		
	}catch(Exception e) {
		e.printStackTrace();
//		session.getTransaction().rollback();
		}
		return null;
	}
		
		
		
		
	}
	

