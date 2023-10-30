package com.product.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.dinerinfo.entity.DinerInfo;
import com.product.entity.Product;

import util.HibernateUtil;

public class ProductDAOImplC implements ProductDAOC {
	@Override
	public Product down(Integer ProductID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

//			session.beginTransaction();
			Product pdt = session.get(Product.class, ProductID);

		

//			session.getTransaction().commit();
			return pdt;

			

		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return null;
		
		
	}
	
	@Override
	public List<Product> getAll(Integer dinerID){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

//		session.beginTransaction();//狀態為1的才查的到
		List<Product> list = session.createQuery("from Product where dinerid = ?0 and productStatus = ?1 ", Product.class)		
				.setParameter(0,dinerID)
				.setParameter(1,"上架中")
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
	public byte[] getImg(Integer productID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {

//			session.beginTransaction();
			Product pdt = session.get(Product.class,productID);


			
//			List list = new ArrayList();
			
			byte[] img = pdt.getProductBlob1();
//			byte[] img2 = pdt.getProductBlob2();
//			byte[] img3 = pdt.getProductBlob3();
//			list.add(img1);
//			list.add(img2);
//			list.add(img3);
//			list.get(1);
//			list.get(2);
//			list.get(3);
//			session.getTransaction().commit();
			
			return img;
			
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return null;
		
	}
	
	
	@Override
	public byte[] getImg2(Integer productID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {

//			session.beginTransaction();
			Product pdt = session.get(Product.class,productID);


			
//			List list = new ArrayList();
			
//			byte[] img = pdt.getProductBlob1();
			byte[] img = pdt.getProductBlob2();
//			byte[] img3 = pdt.getProductBlob3();
//			list.add(img1);
//			list.add(img2);
//			list.add(img3);
//			list.get(1);
//			list.get(2);
//			list.get(3);
//			session.getTransaction().commit();
			
			return img;
			
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return null;
		
	}
	
	
	@Override
	public byte[] getImg3(Integer productID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {

//			session.beginTransaction();
			Product pdt = session.get(Product.class,productID);


			
//			List list = new ArrayList();
			
//			byte[] img1 = pdt.getProductBlob1();
//			byte[] img2 = pdt.getProductBlob2();
			byte[] img = pdt.getProductBlob3();
//			list.add(img1);
//			list.add(img2);
//			list.add(img3);
//			list.get(1);
//			list.get(2);
//			list.get(3);
//			session.getTransaction().commit();
			
			return img;
			
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return null;
		
	}
	
	
	
	
	
	
//	public List<byte[]> getImg1(Integer dinerID) {
//		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		
//		try {
////			session.beginTransaction();
//			
//			List<Product> list = session.createQuery("from Product where dinerid = ?0", Product.class)
//			.setParameter(0, dinerID)
//			.list();
//			
//			byte[] img1 = list.get(0).getProductBlob1();
//			byte[] img2 = list.get(1).getProductBlob2();
//			byte[] img3 = list.get(2).getProductBlob3();
//			
//			List li = new ArrayList();
//			li.add(img1);
//			li.add(img2);
//			li.add(img3);
//			
////			session.getTransaction().commit();
//			
//
//			return li;
//		} catch (Exception e) {
//			e.printStackTrace();
////			session.getTransaction().rollback();
//		}
//		
//		return null;
//		
//		
//		
//		
//	}
//	

	
	
		
}

