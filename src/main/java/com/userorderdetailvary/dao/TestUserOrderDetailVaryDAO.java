package com.userorderdetailvary.dao;

import java.util.List;

import com.userorderdetailvary.entity.UserOrderDetailVary;
import com.userorderdetail.entity.UserOrderDetail;

public class TestUserOrderDetailVaryDAO {
	public static void main(String[] args) throws Exception {
		UserOrderDetailVaryDAO dao = new UserOrderDetailVaryDAOHibernateImpl();
		
		UserOrderDetail userOrderDetail = new UserOrderDetail();
		// 新增
		UserOrderDetailVary userOrderDetailVary1 = new UserOrderDetailVary();
		userOrderDetailVary1.setUserOrderDetail(userOrderDetail);
		userOrderDetailVary1.setProductVaryID1(2);
		userOrderDetailVary1.setProductVaryID2(2);
		userOrderDetailVary1.setProductVaryID3(2);
		userOrderDetailVary1.setProductVaryID4(2);
		dao.add(userOrderDetailVary1);

		// 修改
		UserOrderDetailVary userOrderDetailVary2 = new UserOrderDetailVary();
		userOrderDetailVary2.setUserOrderDetailVaryID(3);
		userOrderDetailVary2.setUserOrderDetail(userOrderDetail);
		userOrderDetailVary2.setProductVaryID1(2);
		userOrderDetailVary2.setProductVaryID2(2);
		userOrderDetailVary2.setProductVaryID3(2);
		userOrderDetailVary2.setProductVaryID4(2);
		dao.update(userOrderDetailVary2);

		// 刪除
		dao.delete(4);

		// 查詢單筆
		UserOrderDetailVary userOrderDetailVary3 = dao.findByPK(2);
		System.out.print(userOrderDetailVary3.getUserOrderDetailVaryID() + ",");
		System.out.print(userOrderDetailVary3.getUserOrderDetail() + ",");
		System.out.print(userOrderDetailVary3.getProductVaryID1() + ",");
		System.out.print(userOrderDetailVary3.getProductVaryID2() + ",");
		System.out.print(userOrderDetailVary3.getProductVaryID3() + ",");
		System.out.println(userOrderDetailVary3.getProductVaryID4());
		System.out.println("---------------------");
		
		// Many-to-one
		System.out.println(userOrderDetailVary3.getUserOrderDetail().getUserOrderItemID());

		// 查詢多筆
		List<UserOrderDetailVary> list = dao.getAll();
		for (UserOrderDetailVary userOrderDetailVary : list) {
			System.out.print(userOrderDetailVary.getUserOrderDetailVaryID() + ",");
			System.out.print(userOrderDetailVary.getUserOrderDetail() + ",");
			System.out.print(userOrderDetailVary.getProductVaryID1() + ",");
			System.out.print(userOrderDetailVary.getProductVaryID2() + ",");
			System.out.print(userOrderDetailVary.getProductVaryID3() + ",");
			System.out.println(userOrderDetailVary.getProductVaryID4());
			System.out.println();
		}
	}
}
