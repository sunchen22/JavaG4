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
		List<Webempadmin> list = session.createQuery(" from Webempadmin where empStatus = : status ", Webempadmin.class)
				.setParameter("status", false)//查詢SQL empStatus boolean 傳入false為0(0是查的到)
				.list();
		
//		session.getTransaction().commit();
		
		
		return list;
		
		}catch(Exception e) {
			
			
		e.printStackTrace();
//		session.getTransaction().rollback();
		}
		
		return null;
	}
}
