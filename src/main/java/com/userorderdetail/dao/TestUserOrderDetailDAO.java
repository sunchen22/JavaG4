package com.userorderdetail.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.userorderdetail.entity.UserOrderDetail;

public class TestUserOrderDetailDAO {
	public static void main(String[] args) throws Exception {
		UserOrderDetailDAO dao = new UserOrderDetailDAOHibernateImpl();

		// 新增
		UserOrderDetail userOrderDetail1 = new UserOrderDetail();
		userOrderDetail1.setProductID(5);
		userOrderDetail1.setProductQuantity(5);
		userOrderDetail1.setUserItemPrice(500);
		userOrderDetail1.setUserPaymentTime(Timestamp.valueOf(LocalDateTime.now()));
		userOrderDetail1.setUserID(5);
		userOrderDetail1.setGroupOrderID(3);
		dao.add(userOrderDetail1);

		// 修改
		UserOrderDetail userOrderDetail2 = new UserOrderDetail();
		userOrderDetail2.setUserOrderItemID(1);
		userOrderDetail2.setProductID(3);
		userOrderDetail2.setProductQuantity(3);
		userOrderDetail2.setUserItemPrice(300);
		userOrderDetail2.setUserPaymentTime(Timestamp.valueOf(LocalDateTime.now()));
		userOrderDetail2.setUserID(3);
		userOrderDetail2.setGroupOrderID(3);
		dao.update(userOrderDetail2);

		// 刪除
		dao.delete(7);

		// 查詢單筆
		UserOrderDetail userOrderDetail3 = dao.findByPK(2);
		System.out.print(userOrderDetail3.getUserOrderItemID() + ",");
		System.out.print(userOrderDetail3.getProductID() + ",");
		System.out.print(userOrderDetail3.getProductQuantity() + ",");
		System.out.print(userOrderDetail3.getUserItemPrice() + ",");
		System.out.print(userOrderDetail3.getUserPaymentTime() + ",");
		System.out.print(userOrderDetail3.getUserID() + ",");
		System.out.println(userOrderDetail3.getGroupOrderID());
		System.out.println("---------------------");

		// 查詢多筆
		List<UserOrderDetail> list = dao.getAll();
		for (UserOrderDetail userOrderDetail : list) {
			System.out.print(userOrderDetail.getUserOrderItemID() + ",");
			System.out.print(userOrderDetail.getProductID() + ",");
			System.out.print(userOrderDetail.getProductQuantity() + ",");
			System.out.print(userOrderDetail.getUserItemPrice() + ",");
			System.out.print(userOrderDetail.getUserPaymentTime() + ",");
			System.out.print(userOrderDetail.getUserID() + ",");
			System.out.println(userOrderDetail.getGroupOrderID());
			System.out.println();
		}
	}
}
