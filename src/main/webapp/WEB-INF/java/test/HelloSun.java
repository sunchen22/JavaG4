package test;

import org.hibernate.Session;

import com.util.HibernateUtil;
import com.webempadmin.entity.Webempadmin;


public class HelloSun {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			Webempadmin emp = new Webempadmin();
			emp.setEmpName("aaa");
			emp.setEmpPassword("1234");
			emp.setEmpArriveDate(java.sql.Date.valueOf("2005-01-01"));
			emp.setEmpAdminAuthorization("ceo");
			
			session.save(emp);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}	
		
	}

}
