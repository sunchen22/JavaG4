package com.dinerinfo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.buildinginfo.entity.BuildingInfo;
import com.dinerinfo.entity.DinerInfo;

import util.HibernateUtil;


public class DinerInfoDAOImplC implements DinerInfoDAOC{
	
	public DinerInfo findByPK(Integer dinerInfoID) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
		session.beginTransaction();
		DinerInfo dif = session.get(DinerInfo.class, dinerInfoID);
		session.getTransaction().commit();
		
		
		
		return dif;
																			
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	
	public List<DinerInfo> getAll(){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
		session.beginTransaction();
		List<DinerInfo> list = session.createQuery("from DinerInfo where dinerstatus = ?0 ", DinerInfo.class)
				.setParameter(0,"active")
				.list();
		
		session.getTransaction().commit();
		return list;
	}catch(Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
		}
		return null;
	}
		
public List<DinerInfo> getAllSubmitted(){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
		session.beginTransaction();
		List<DinerInfo> list = session.createQuery("from DinerInfo where dinerstatus = ?0 ", DinerInfo.class)
				.setParameter(0, "submitted")
				.list();
		
		session.getTransaction().commit();
		return list;
	}catch(Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
		}
		return null;
	}
	
	
		
	}
	
		
		

