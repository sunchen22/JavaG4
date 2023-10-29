package com.webempadmin.model;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.webempadmin.entity.Webempadmin;

import util.HibernateUtil;

public class WebempadminDAOImpl implements  WebempadminDAO_hibernate {

	@Override
	public Webempadmin findByPrimaryKey(String empName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

			
			Query query = session.createQuery("FROM Webempadmin WHERE empName = :empName");
	        query.setParameter("empName", empName);
	        Webempadmin emp = (Webempadmin) query.uniqueResult();
			
			return emp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
