package com.grouporder.service;

import java.util.List;

import com.grouporder.dao.GroupOrderDAON;
import com.grouporder.dao.GroupOrderDAO_interface;
import com.grouporder.entity.GroupOrderVO;
import com.product.entity.ProductVO;

public class GroupOrderServiceN {

	private GroupOrderDAO_interface dao;

	public GroupOrderServiceN() {
		dao = new GroupOrderDAON();
	}

	public List<GroupOrderVO> getAll(Integer orderStatus) {
		return dao.getAll(orderStatus);
	}

	public GroupOrderVO gostatus(Integer groupOrderID, Integer orderStatus) {
		GroupOrderVO groupOrder = new GroupOrderVO();

		groupOrder.setGroupOrderID(groupOrderID);

		groupOrder.setOrderStatus(orderStatus);

		dao.gostatus(groupOrder);
		
		return dao.findByPrimaryKey(groupOrderID);
	}

	public GroupOrderVO getOneGO(Integer groupOrderID) {
		return dao.findByPrimaryKey(groupOrderID);
	}
}
