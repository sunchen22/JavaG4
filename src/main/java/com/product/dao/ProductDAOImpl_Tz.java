package com.product.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.product.entity.Product;

import util.HibernateUtil;

public class ProductDAOImpl_Tz {
	
	
	public List<Product> getAll(Integer dinerID){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//		session.beginTransaction();//狀態為1的才查的到
		List<Product> list = session.createQuery("from Product where dinerid = ?0 ", Product.class)
				.setParameter(0,dinerID)
				.list();
		
//		session.getTransaction().commit();
		return list;
	}catch(Exception e) {
		e.printStackTrace();
//		session.getTransaction().rollback();
		}
		return null;
	}
	
	public List<byte[]> getImg(Integer productID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			Product gor = session.get(Product.class,productID);
			
			List list = new ArrayList();
			
			byte[] img1 = gor.getProductBlob1();
			byte[] img2 = gor.getProductBlob2();
			byte[] img3 = gor.getProductBlob3();
			list.add(img1);
			list.add(img2);
			list.add(img3);
//			list.get(1);
//			list.get(2);
//			list.get(3);
			session.getTransaction().commit();
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return null;
		
	}
	
	
		
}

