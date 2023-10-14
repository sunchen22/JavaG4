package com.grouporder.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.grouporder.entity.GroupOrder;


public class TestGroupOrderDAO {
	public static void main(String[] args) throws Exception {
//		GroupOrderDAO dao = new GroupOrderDAOHibernateImpl();

//		// 新增
//		GroupOrder groupOrder1 = new GroupOrder();
//		groupOrder1.setDinerID(3);
//		groupOrder1.setBuildingID(3);
//		groupOrder1.setOrderStatus("1");
//		groupOrder1.setGroupOrderCreateTime(Timestamp.valueOf(LocalDateTime.now()));
//		groupOrder1.setGroupOrderSubmitTime(Timestamp.valueOf(LocalDateTime.now()));
//		groupOrder1.setHolderID(3);
//		dao.add(groupOrder1);
//
//		// 修改
//		GroupOrder groupOrder2 = new GroupOrder();
//		groupOrder2.setGroupOrderID(2);
//		groupOrder2.setDinerID(1);
//		groupOrder2.setBuildingID(1);
//		groupOrder2.setOrderStatus("2");
//		groupOrder2.setGroupOrderCreateTime(Timestamp.valueOf(LocalDateTime.now()));
//		groupOrder2.setGroupOrderSubmitTime(Timestamp.valueOf(LocalDateTime.now()));
//		groupOrder2.setHolderID(3);
//		dao.update(groupOrder2);
//
//		// 刪除
//		dao.delete(31);
//
//		// 查詢單筆
//		GroupOrder groupOrder3 = dao.findByPK(2);
//		System.out.print(groupOrder3.getGroupOrderID() + ",");
//		System.out.print(groupOrder3.getDinerID() + ",");
//		System.out.print(groupOrder3.getBuildingID() + ",");
//		System.out.print(groupOrder3.getOrderStatus() + ",");
//		System.out.print(groupOrder3.getGroupOrderCreateTime() + ",");
//		System.out.print(groupOrder3.getGroupOrderSubmitTime() + ",");
//		System.out.println(groupOrder3.getHolderID());
//		System.out.println("---------------------");

//		//查詢多筆
//		List<GroupOrder> list = dao.getAll();
//		for (GroupOrder groupOrder : list) {
//			System.out.print(groupOrder.getGroupOrderID() + ",");
//			System.out.print(groupOrder.getDinerID() + ",");
//			System.out.print(groupOrder.getBuildingID() + ",");
//			System.out.print(groupOrder.getOrderStatus() + ",");
//			System.out.print(groupOrder.getGroupOrderCreateTime() + ",");
//			System.out.print(groupOrder.getGroupOrderSubmitTime() + ",");
//			System.out.println(groupOrder.getHolderID());
//			System.out.println();
//		}



		// 查詢join
//		List<Object[]> results = dao.getAllJoin(2);
//		System.out.println(results);
		

	}
}
