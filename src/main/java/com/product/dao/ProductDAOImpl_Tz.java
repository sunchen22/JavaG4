package com.product.dao;


import java.util.*;
import org.hibernate.Session;
import com.product.entity.Product_Tz;
import util.HibernateUtil;

public class ProductDAOImpl_Tz {
	
	public Product_Tz getProductByPK(Integer productID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//		session.beginTransaction();
			Product_Tz product = session.get(Product_Tz.class, productID);

//		session.getTransaction().commit();
			return product;
		} catch (Exception e) {
			e.printStackTrace();
//		session.getTransaction().rollback();
		}
		return null;
	}

	public List<Product_Tz> getAll(Integer dinerID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//		session.beginTransaction();//狀態為1的才查的到
			List<Product_Tz> list = session.createQuery("from Product_Tz where dinerid = ?0 ", Product_Tz.class)
					.setParameter(0, dinerID).list();

//		session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
//		session.getTransaction().rollback();
		}
		return null;
	}


}
