package com.advertisement.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import util.HibernateUtil;

import com.advertisement.entity.Advertisement;
import com.dinerinfo.entity.DinerInfo;

public class AdvertisementDAOHibernateImplC implements AdvertisementDAOC {
	

	@Override
	public Advertisement update(Integer advertisementID) {
		
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			
			Advertisement ad = session.get(Advertisement.class, advertisementID);
						
//			session.getTransaction().commit();

			return ad;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return null;
		
		
		
		
	}
	@Override
	public List<Advertisement> getAllApprovedAD(){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//		session.beginTransaction();
//			List<Advertisement> list = session.createQuery("from Advertisement where advertisementStatus = ?0 ", Advertisement.class)
//					.setParameter(0, "Approved")
//					.list();		
			NativeQuery<Advertisement> query = session.createNativeQuery("SELECT * FROM DinerInfo d JOIN Advertisement a ON d.dinerid = a.dinerid where advertisementStatus = ?0 ", Advertisement.class)
					.setParameter(0, "Approved");
					
					
			List <Advertisement> list = query.list();
		
//		session.getTransaction().commit();
			return list;
	}catch(Exception e) {
		e.printStackTrace();
//		session.getTransaction().rollback();
		}
		return null;
	}
	
	
	
@Override	
public List<Advertisement> getAllSubmittedAD(){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//		session.beginTransaction();
//			List<Advertisement> list = session.createQuery("from Advertisement where advertisementStatus = ?0 ", Advertisement.class)
//					.setParameter(0, "Approved")
//					.list();		
			NativeQuery<Advertisement> query = session.createNativeQuery("SELECT * FROM DinerInfo d JOIN Advertisement a ON d.dinerid = a.dinerid where advertisementStatus = ?0 ", Advertisement.class)
					.setParameter(0, "Submitted");
					
					
			List <Advertisement> list = query.list();
		
//		session.getTransaction().commit();
			return list;
	}catch(Exception e) {
		e.printStackTrace();
//		session.getTransaction().rollback();
		}
		return null;
	}
//@Override
//public List<Advertisement> getDinerInfoSubmittedAD(Integer dinerID){
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
////			session.beginTransaction();
//			List<Advertisement> list = session.createQuery(" from Advertisement where dinerID =?0 and advertisementStatus = ?1 ",Advertisement.class)
//					.setParameter(0, dinerID)
//					.setParameter(1, "Submitted")
//					.list();
//			
////			session.getTransaction().commit();
//			return list;
//		}
//			
//		catch(Exception e) {
//			e.printStackTrace();
////			session.getTransaction().rollback();
//	
//
//			
//	
//}
//		return null;
//}	



//@Override
//public List<Advertisement> getDinerInfoDeactivatedAD(Integer dinerID){
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
////			session.beginTransaction();
//			List<Advertisement> list = session.createQuery(" from Advertisement where dinerID =?0 and advertisementStatus = ?1 ",Advertisement.class)
//					.setParameter(0, dinerID)
//					.setParameter(1, "Deactivated")
//					.list();
//			
////			session.getTransaction().commit();
//			return list;
//		}
//			
//		catch(Exception e) {
//			e.printStackTrace();
////			session.getTransaction().rollback();
//	
//
//			
//	
//}
//		return null;
//}	








}
