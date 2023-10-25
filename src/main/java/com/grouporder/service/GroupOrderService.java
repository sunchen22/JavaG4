package com.grouporder.service;

import java.util.Map;
import java.util.List;

public interface GroupOrderService {
	String getAllJoinGroupOrder(int currentpage);
	Map<String, Object> getOneJoinGroupOrder(Integer groupOrderID);
	String getProductAndVaryOptions(Integer groupOrderID);
	Integer getProductPrice(Integer productID);
	Integer getProductVaryPrice(Integer productVaryID);
	Boolean userIsGroupMember(Object userInfo, Integer groupOrderID);
	void addUserToGroup(Object userInfo, Integer groupOrderID, String dinerName);
	List<Map<String, Object>> navbarJoinedGroupOrders(Object userInfo);
	byte[] getGroupOrderDinerImage(Integer groupOrderID);
	byte[] getGroupOrderProductImage(Integer productID, Integer no);
}
