package com.grouporder.service;

import java.util.Map;

public interface GroupOrderService {
	String getAllJoinGroupOrder(int currentpage);
	Map<String, Object> getOneJoinGroupOrder(Integer groupOrderID);
	String getProductAndVaryOptions(Integer groupOrderID);
	Integer getProductPrice(Integer productID);
	Integer getProductVaryPrice(Integer productVaryID);
	byte[] getGroupOrderDinerImage(Integer groupOrderID);
	byte[] getGroupOrderProductImage(Integer productID);	
}
