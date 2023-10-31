package com.grouporder.service;

import java.util.Map;

import com.grouporder.entity.GroupOrder;
import com.userorderdetail.entity.UserOrderDetail;

import java.util.List;

public interface GroupOrderService {
	String getAllJoinGroupOrder(int currentpage);
	Map<String, Object> getOneJoinGroupOrder(Integer groupOrderID);
	String getProductAndVaryOptions(Integer groupOrderID);
	Integer getProductPrice(Integer productID);
	Integer getProductVaryPrice(Integer productVaryID);
	Integer calculateSubtotal(Integer productID, Integer quantity, List<Integer> productVaryIDList);
	Boolean userIsGroupMember(Object userInfo, Integer groupOrderID);
	void addUserToGroup(Object userInfo, Integer groupOrderID);
	List<Map<String, Object>> navbarJoinedGroupOrders(Object userInfo);
	void addProductToCart(Object userInfo, String groupOrderID, String dinerID, String productID, List<String> varyTypeIDs, String quantity);
	List<Map<String, Object>> getCart(Object userInfo, Integer groupOrderID, Integer dinerID);
	void checkoutCart(Object userInfo, Integer groupOrderID, Integer dinerID);
	List<Map<String, Object>> getUserOrderDetailOnThisGroupOrder(Integer groupOrderID, Object userInfo);
	byte[] getGroupOrderDinerImage(Integer groupOrderID);
	byte[] getGroupOrderProductImage(Integer productID, Integer no);
	Integer createGroupOrder(Integer dinerID, Integer buildingID, String groupOrderSubmitTime, Object userInfo);
	List<Map<String, Object>> searchGroupOrder(String nameKeyword, String addressKeyword);
	void changeAllGroupOrderStatus();
	void clearCart(Object userInfo, Integer groupOrderID, Integer dinerID);
}
