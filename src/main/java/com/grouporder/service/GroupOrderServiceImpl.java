package com.grouporder.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashMap;

import com.grouporder.entity.GroupOrder;
import com.product.entity.Product;

import util.HibernateUtil;

import com.grouporder.dao.GroupOrderDAOHibernateImpl;
import com.dinerinfo.entity.DinerInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
 
public class GroupOrderServiceImpl implements GroupOrderService {
	// One DAO instance for one service instance
	private GroupOrderDAOHibernateImpl dao;

	public GroupOrderServiceImpl() {
		dao = new GroupOrderDAOHibernateImpl(HibernateUtil.getSessionFactory());
	}

	public String getAllJoinGroupOrder(int currentpage) {
		List<Object[]> results = dao.getAllJoin(currentpage);

		List<Map<String, Object>> jsonElements = new ArrayList<>();
		for (Object[] result : results) {
			Map<String, Object> groupOrderData = new LinkedHashMap<>();
			GroupOrder groupOrder = (GroupOrder) result[0]; // Access the entity object
			groupOrderData.put("groupOrderID", groupOrder.getGroupOrderID());
			groupOrderData.put("orderStatus", groupOrder.getOrderStatus());
			groupOrderData.put("groupOrderCreateTime", groupOrder.getGroupOrderCreateTime());
			groupOrderData.put("groupOrderSubmitTime", groupOrder.getGroupOrderSubmitTime());
//			groupTotalPrice

			groupOrderData.put("dinerID", (int) result[1]);
			groupOrderData.put("dinerName", (String) result[2]);
			groupOrderData.put("dinerAddress", (String) result[3]);
			groupOrderData.put("dinerType", (String) result[4]);
			groupOrderData.put("dinerOrderThreshold", (Integer) result[5]);
			groupOrderData.put("dinerStatus", (String) result[6]);
			groupOrderData.put("buildingName", (String) result[7]);
			groupOrderData.put("buildingAddress", (String) result[8]);
			groupOrderData.put("userNickName", (String) result[9]);
			groupOrderData.put("dinerRating", (double) result[10]);

			jsonElements.add(groupOrderData);

		}

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String json = gson.toJson(jsonElements);
		return json;
	}

	public Map<String, Object> getOneJoinGroupOrder(int groupOrderID) {
		List<Object[]> results = dao.getOneJoin(groupOrderID);
//		
		Object[] result = results.get(0);
		Map<String, Object> groupOrderData = new HashMap<>();
		GroupOrder groupOrder = (GroupOrder) result[0]; // Access the entity object
		groupOrderData.put("groupOrderID", groupOrder.getGroupOrderID());
		groupOrderData.put("orderStatus", groupOrder.getOrderStatus());
		groupOrderData.put("groupOrderCreateTime", groupOrder.getGroupOrderCreateTime());
		groupOrderData.put("groupOrderSubmitTime", groupOrder.getGroupOrderSubmitTime());
//		groupTotalPrice

		groupOrderData.put("dinerID", (int) result[1]);
		groupOrderData.put("dinerName", (String) result[2]);
		groupOrderData.put("dinerAddress", (String) result[3]);
		groupOrderData.put("dinerType", (String) result[4]);
		groupOrderData.put("dinerOrderThreshold", (Integer) result[5]);
		groupOrderData.put("dinerStatus", (String) result[6]);
		groupOrderData.put("buildingName", (String) result[7]);
		groupOrderData.put("buildingAddress", (String) result[8]);
		groupOrderData.put("userNickName", (String) result[9]);
		groupOrderData.put("dinerRating", (double) result[10]);

		List<Object[]> menuResult = dao.getOneJoinMenu((int) result[1]);

		Map<String, List<Map<String, Object>>> menuData = new LinkedHashMap<>();
		for (Object[] row : menuResult) {
			Map<String, Object> productData = new HashMap();
			
			productData.put("productID", (int) row[0]);
			productData.put("productName", (String) row[1]);
			productData.put("productPrice", (int) row[2]);
			
			if (menuData.get((String) row[3]) == null) {
				List<Map<String, Object>> productDataList = new ArrayList<>();
				productDataList.add(productData);
				menuData.put((String) row[3], productDataList);
			} else {
				menuData.get((String) row[3]).add(productData);
			}
		}
		System.out.println("========" + menuData);
		groupOrderData.put("menuData", menuData);
		
	return groupOrderData;

	}

	public byte[] getGroupOrderDinerImage(int groupOrderID) {
		DinerInfo dinerInfo = dao.findByPKJoinDiner(groupOrderID);
		if (dinerInfo != null) {
			byte[] imageData = dinerInfo.getDinerBlob();
			return imageData;
		} else {
			return null;
		}
	}
	public byte[] getGroupOrderProductImage(int productID) {
		Product product = dao.findByPKProduct(productID);
		if (product != null) {
			byte[] imageData = product.getProductBlob1();
			return imageData;
		} else {
			return null;
		}
	}
	
}