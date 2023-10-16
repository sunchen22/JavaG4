package com.grouporder.dao;

import java.util.List;

import com.dinerinfo.entity.DinerInfo;
import com.grouporder.entity.GroupOrder;
import com.product.entity.Product;

public interface GroupOrderDAO {
	int add(GroupOrder groupOrder);
	int update(GroupOrder groupOrder);
	int delete(Integer groupOrderID);
	GroupOrder findByPK(Integer groupOrderID);
	DinerInfo findByPKJoinDiner(Integer groupOrderID);
	Product findByPKProduct(Integer productID);
	List<GroupOrder> getAll();
	List<Object[]> getAllJoin(Integer currentPage);
	List<Object[]> getOneJoin(Integer groupOrderID);
	List<Object[]> getOneJoinMenu(Integer dinerID);
	
}