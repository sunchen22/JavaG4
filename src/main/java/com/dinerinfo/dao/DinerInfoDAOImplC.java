package com.dinerinfo.dao;

import java.util.List;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.json.JSONObject;
import org.hibernate.Session;

import com.buildinginfo.entity.BuildingInfo;
import com.dinerinfo.entity.DinerInfo;


import util.HibernateUtil;


public class DinerInfoDAOImplC implements DinerInfoDAOC{
	@Override
	public DinerInfo findByPK(Integer dinerInfoID) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//		session.beginTransaction();
		DinerInfo dif = session.get(DinerInfo.class, dinerInfoID);
//		session.getTransaction().commit();
		
		
		
		return dif;
																			
		}catch(Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return null;
	}
	@Override
	public List<DinerInfo> getAll(){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//		session.beginTransaction();
		List<DinerInfo> list = session.createQuery("from DinerInfo where dinerstatus = ?0 ", DinerInfo.class)
				.setParameter(0,"active")
				.list();
		
//		session.getTransaction().commit();
		return list;
	}catch(Exception e) {
		e.printStackTrace();
//		session.getTransaction().rollback();
		}
		return null;
	}
	@Override	
	public List<DinerInfo> getAllSubmitted(){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//		session.beginTransaction();
		List<DinerInfo> list = session.createQuery("from DinerInfo where dinerstatus = ?0 ", DinerInfo.class)
				.setParameter(0, "submitted")
				.list();
		
//		session.getTransaction().commit();
		return list;
	}catch(Exception e) {
		e.printStackTrace();
//		session.getTransaction().rollback();
		}
		return null;
	}
	
	
		
	
	
	@Override
public List<DinerInfo> getAllChanged(){
	
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	try {
//	session.beginTransaction();
	List<DinerInfo> list = session.createQuery("from DinerInfo where dinerstatus = ?0 ", DinerInfo.class)
			.setParameter(0, "changed")
			.list();
	
//	session.getTransaction().commit();
	return list;
}catch(Exception e) {
	e.printStackTrace();
//	session.getTransaction().rollback();
	}
	return null;
}
	@Override
public List<DinerInfo> getAllAD(){
	
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	try {
//	session.beginTransaction();
		List<DinerInfo> list = session.createQuery("FROM DinerInfo ", DinerInfo.class).list();		
//		NativeQuery<DinerInfo> query = session.createNativeQuery("SELECT * FROM DinerInfo d JOIN Advertisement a ON d.dinerid = a.dinerid ", DinerInfo.class);
//		
//		List <DinerInfo> list = query.list();
	
//	session.getTransaction().commit();
		return list;
}catch(Exception e) {
	e.printStackTrace();
//	session.getTransaction().rollback();
	}
	return null;
}

@Override
public DinerInfo update(Integer dinerInfoID) {
	
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	try {
//	session.beginTransaction();
	DinerInfo dif = session.get(DinerInfo.class, dinerInfoID);
	

	
	
//	session.getTransaction().commit();
	
	
	
	return dif;
																		
	}catch(Exception e) {
		e.printStackTrace();
//		session.getTransaction().rollback();
	}
	return null;
}


@Override
public DinerInfo DeactivatedByPK(Integer dinerInfoID) {
	
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	try {
//	session.beginTransaction();
	DinerInfo dif = session.get(DinerInfo.class, dinerInfoID);
//	session.getTransaction().commit();
	
	
	
	return dif;
																		
	}catch(Exception e) {
		e.printStackTrace();
//		session.getTransaction().rollback();
	}
	return null;
}

@Override
public DinerInfo ActiveByPK(Integer dinerInfoID) {
	
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	try {
//	session.beginTransaction();
	DinerInfo dif = session.get(DinerInfo.class, dinerInfoID);
//	session.getTransaction().commit();
	
	
	
	return dif;
																		
	}catch(Exception e) {
		e.printStackTrace();
//		session.getTransaction().rollback();
	}
	return null;
}




	
}
		
		

