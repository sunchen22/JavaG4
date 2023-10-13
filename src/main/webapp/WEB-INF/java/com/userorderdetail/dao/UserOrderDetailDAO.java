package com.userorderdetail.dao;

import java.util.List;

import com.userorderdetail.entity.UserOrderDetail;

public interface UserOrderDetailDAO {
	int add(UserOrderDetail userOrderDetail);
	int update(UserOrderDetail userOrderDetail);
	int delete(Integer userOrderItemID);
	UserOrderDetail findByPK(Integer userOrderItemID);
	List<UserOrderDetail> getAll();
}