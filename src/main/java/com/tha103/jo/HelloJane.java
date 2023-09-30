package com.tha103.jo;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.Session;
import entity.GroupOrder;
import entity.UserOrderDetail;
import entity.UserOrderDetailVary;
import util.HibernateUtil;

public class HelloJane {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			GroupOrder groupOrder = new GroupOrder();
			groupOrder.setDinerID(5);
			groupOrder.setBuildingID(5);;
			groupOrder.setOrderStatus("1");
			groupOrder.setGroupOrderCreateTime(Timestamp.valueOf(LocalDateTime.now()));
			groupOrder.setGroupOrderSubmitTime(Timestamp.valueOf(LocalDateTime.now()));
			groupOrder.setHolderID(5);
			
			session.save(groupOrder);
			
			UserOrderDetail userOrderDetail = new UserOrderDetail();
			userOrderDetail.setProductID(2);
			userOrderDetail.setProductQuantity(3);
			userOrderDetail.setUserItemPrice(200);
			userOrderDetail.setUserID(1);
			userOrderDetail.setGroupOrderID(1);
			userOrderDetail.setUserPaymentTime(Timestamp.valueOf(LocalDateTime.now()));
			
			session.save(userOrderDetail);
			
			UserOrderDetailVary userOrderDetailVary = new UserOrderDetailVary();
			userOrderDetailVary.setUserOrderItemID(1);
			userOrderDetailVary.setProductVaryID1(1);
			userOrderDetailVary.setProductVaryID2(2);
			userOrderDetailVary.setProductVaryID3(3);
			userOrderDetailVary.setProductVaryID4(4);
			
			session.save(userOrderDetailVary);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}	
		
	}
}
