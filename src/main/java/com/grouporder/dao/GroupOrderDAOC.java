package com.grouporder.dao;

import java.util.List;

import com.grouporder.entity.GroupOrder;

public interface GroupOrderDAOC {
	List<GroupOrder> getAll(Integer dinerID);
	byte[] getImg(Integer groupOrderID);
	GroupOrder findByPK(Integer groupOrderID);
	List<Object[]> getOrderDetail(Integer dinerID);
	List<Object[]> getOrderDetail2(Integer dinerID, String orderStatus);
	
}
