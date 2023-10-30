package com.grouporder.dao;

import java.util.List;

import com.grouporder.entity.GroupOrderVO;

public interface GroupOrderDAO_interface {

	List<GroupOrderVO> getAll(Integer orderStatus);

	GroupOrderVO findByPrimaryKey(Integer groupOrderID);

	void gostatus(GroupOrderVO groupOrder);

}
