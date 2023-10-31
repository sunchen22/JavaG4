package com.dinernews.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import com.dinernews.entity.DinerNews;
import com.webempadmin.entity.Webempadmin;

import util.HibernateUtil;

public class DinerNewsDAOHibernateImpl implements DinerNewsDAO {
	@Override
	public int add(DinerNews dns) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

//			session.beginTransaction();
			session.save(dns);
//			session.getTransaction().commit();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return -1;

	}
	
	@SuppressWarnings("null")
	@Override
	public DinerNews update(String news1 , String news2 , String news3 , String startDate ,
			String endDate ,/* Integer empid*/ String empName) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//			session.beginTransaction();
			DinerNews dn = session.get(DinerNews.class , 1);
			if(dn!=null) {
				Webempadmin w = session.createQuery("from Webempadmin where empName = ?0 ",Webempadmin.class)
						.setParameter(0, empName)
						.uniqueResult();
//				Webempadmin w = session.get(Webempadmin.class, empid);
				if (w == null) {
	                
	                
	                w.setEmpName(w.getEmpName());//這樣寫更新時 頁面不用刷新員工才會直接顯示
	                
	            }
				
				dn.setWebempadmin(w);
				
			
				dn.setDinerNewsContent1(news1);
				dn.setDinerNewsContent2(news2);
				dn.setDinerNewsContent3(news3);

				dn.setDinerNewsStatus(1);
				
				try {
					
					dn.setDinerNewsReleaseTime(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));
					
				
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				try {
					dn.setDinerNewsReviseTime(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
					
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				
				
				}
				
//				session.merge(dn);
	
			
//			session.getTransaction().commit();
				
			return dn;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return null;
	}
	@Override
	public int down(Integer dinerNewsID) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			
			DinerNews dns = session.get(DinerNews.class , dinerNewsID);
			
//			session.getTransaction().commit();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}
	
	@Override
	public List<DinerNews> getAll() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			
			List<DinerNews> list = session.createQuery("from DinerNews where dinerNewsStatus = ?0 " ,DinerNews.class)
					.setParameter(0, 1)
					.list();
			
//			session.getTransaction().commit();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	
	
	
	
	
	
	
}
