package com.tha103.model.dao;

import java.util.List;

import entity.GroupOrder;

public interface GroupOrderDAO {
	int add(GroupOrder groupOrder);
	int update(GroupOrder groupOrder);
	int delete(Integer groupOrderID);
	GroupOrder findByPK(Integer groupOrderID);
	List<GroupOrder> getAll();
}