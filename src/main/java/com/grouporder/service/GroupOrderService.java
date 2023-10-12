package com.grouporder.service;

public interface GroupOrderService {
	public String getAllJoinGroupOrder(int currentpage);
	
	public byte[] getGroupOrderDinerImage(int groupOrderID);
}
