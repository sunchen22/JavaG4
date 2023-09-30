package com.tha103.modal.dao;

import java.util.List;

import entity.UserOrderDetailVary;

public interface UserOrderDetailVaryDAO {
	int add(UserOrderDetailVary userOrderDetailVary);
	int update(UserOrderDetailVary userOrderDetailVary);
	int delete(Integer userOrderDetailVaryID);
	UserOrderDetailVary findByPK(Integer userOrderDetailVaryID);
	List<UserOrderDetailVary> getAll();
}
