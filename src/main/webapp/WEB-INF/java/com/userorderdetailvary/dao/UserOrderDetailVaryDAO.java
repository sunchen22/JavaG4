package com.userorderdetailvary.dao;

import java.util.List;

import com.userorderdetailvary.entity.UserOrderDetailVary;

public interface UserOrderDetailVaryDAO {
	int add(UserOrderDetailVary userOrderDetailVary);
	int update(UserOrderDetailVary userOrderDetailVary);
	int delete(Integer userOrderDetailVaryID);
	UserOrderDetailVary findByPK(Integer userOrderDetailVaryID);
	List<UserOrderDetailVary> getAll();
}
