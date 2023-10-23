package com.webempadmin.model;

import java.util.List;


import org.hibernate.Session;

import com.webempadmin.entity.Webempadmin;

import util.HibernateUtil;

public class WebempadminDAOImplC implements WebempadminDAOC{

	@Override
	public List<Webempadmin> getAllEmp(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//		session.beginTransaction();
		List<Webempadmin> list = session.createQuery(" from Webempadmin  ", Webempadmin.class).list();
		
//		session.getTransaction().commit();
		
		
		return list;
		
		}catch(Exception e) {
			
			
		e.printStackTrace();
//		session.getTransaction().rollback();
		}
		
		return null;
	}
}
