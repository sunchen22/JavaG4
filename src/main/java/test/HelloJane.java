package test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;

import com.grouporder.dao.GroupOrderDAO;
import com.grouporder.dao.GroupOrderDAOHibernateImpl;
import com.userorderdetail.entity.UserOrderDetail;
import com.userorderdetailvary.entity.UserOrderDetailVary;
import util.HibernateUtil;

import com.grouporder.entity.GroupOrder;

public class HelloJane {
//	public static void main(String[] args) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			
//			GroupOrder groupOrder = new GroupOrder();
//			groupOrder.setDinerID(5);
//			groupOrder.setBuildingID(5);;
//			groupOrder.setOrderStatus("1");
//			groupOrder.setGroupOrderCreateTime(Timestamp.valueOf(LocalDateTime.now()));
//			groupOrder.setGroupOrderSubmitTime(Timestamp.valueOf(LocalDateTime.now()));
//			groupOrder.setHolderID(5);
//			
//			session.save(groupOrder);
//			
//			UserOrderDetail userOrderDetail = new UserOrderDetail();
//			userOrderDetail.setProductID(2);
//			userOrderDetail.setProductQuantity(3);
//			userOrderDetail.setUserItemPrice(200);
//			userOrderDetail.setUserID(1);
//			userOrderDetail.setGroupOrderID(1);
//			userOrderDetail.setUserPaymentTime(Timestamp.valueOf(LocalDateTime.now()));
//			
//			session.save(userOrderDetail);
//			
//			UserOrderDetailVary userOrderDetailVary = new UserOrderDetailVary();
//			userOrderDetailVary.setUserOrderItemID(1);
//			userOrderDetailVary.setProductVaryID1(1);
//			userOrderDetailVary.setProductVaryID2(2);
//			userOrderDetailVary.setProductVaryID3(3);
//			userOrderDetailVary.setProductVaryID4(4);
//			
//			session.save(userOrderDetailVary);
//			
//			session.getTransaction().commit();
//			
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		} finally {
//			HibernateUtil.shutdown();
//		}	
//		
//		GroupOrderDAO dao1 = new GroupOrderDAOHibernateImpl();
//		
//		GroupOrder groupOrder2 = new GroupOrder();
//		groupOrder2.setDinerID(3);
//		groupOrder2.setBuildingID(3);;
//		groupOrder2.setOrderStatus("1");
//		groupOrder2.setGroupOrderCreateTime(Timestamp.valueOf(LocalDateTime.now()));
//		groupOrder2.setGroupOrderSubmitTime(Timestamp.valueOf(LocalDateTime.now()));
//		groupOrder2.setHolderID(3);
//		Integer newID2 = dao1.add(groupOrder2);
//		System.out.println("new group order ID = " + newID2);
//		
//		groupOrder2 = dao1.findByPK(newID2);
//		groupOrder2.setOrderStatus("2");
//		if(dao1.update(groupOrder2) == 1) {
//			System.out.println(dao1.findByPK(newID2));
//			System.out.println("group order updated");
//		}
//		
//		List<GroupOrder> list1 = dao1.getAll();
//		System.out.println(list1);
//
//		dao1.delete(newID2);
//		System.out.println(dao1.findByPK(newID2)); 
//
//			
//	}
//}
}