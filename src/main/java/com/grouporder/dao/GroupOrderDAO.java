package com.grouporder.dao;

import java.util.List;

import com.buildinginfo.entity.BuildingInfo;
import com.dinerinfo.entity.DinerInfo;
import com.grouporder.entity.GroupOrder;
import com.product.entity.Product;
import com.productvary.entity.ProductVary;

public interface GroupOrderDAO {
	int add(GroupOrder groupOrder);
	int update(GroupOrder groupOrder);
	int delete(Integer groupOrderID);
	GroupOrder findByPK(Integer groupOrderID);
	DinerInfo findByPKJoinDiner(Integer groupOrderID);
	Product findByPKProduct(Integer productID);
	ProductVary findByPKProductVary(Integer productID);
	List<GroupOrder> getAll();
	List<Object[]> getAllJoin(Integer currentPage);
	List<Object[]> getOneJoin(Integer groupOrderID);
	List<Object[]> getOneJoinMenu(Integer dinerID);
	List<Object[]> getProductJoinProductVary(Integer productID);
	DinerInfo findByPKDiner(Integer dinerID);
	BuildingInfo findByPKBuilding(Integer buildingID);
	List<Object[]> getGroupOrderByKeywords(String nameKeyword, String addressKeyword);
	List<GroupOrder> getAllStatusOneTwo();
}