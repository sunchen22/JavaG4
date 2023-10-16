package com.advertisement.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import util.HibernateUtil;

import com.advertisement.entity.Advertisement;
import com.dinerinfo.entity.DinerInfo;

public class AdvertisementDAOHibernateImplC implements AdvertisementDAOC {
	
	
	public List<Advertisement> getAllAD(){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//		session.beginTransaction();
//			List<Advertisement> list = session.createQuery("from Advertisement where advertisementStatus = ?0 ", Advertisement.class)
//					.setParameter(0, "Approved")
//					.list();		
			NativeQuery<Advertisement> query = session.createNativeQuery("SELECT * FROM DinerInfo d JOIN Advertisement a ON d.dinerid = a.dinerid ", Advertisement.class);
			
			List <Advertisement> list = query.list();
		
//		session.getTransaction().commit();
			return list;
	}catch(Exception e) {
		e.printStackTrace();
//		session.getTransaction().rollback();
		}
		return null;
	}

	
	
}
