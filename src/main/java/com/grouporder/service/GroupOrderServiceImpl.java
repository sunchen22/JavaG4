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
		// returns a Map in the following format:
		// {
		//  groupOrderID: 1,
		//	orderStatus: 1,
		//	groupOrderCreateTime: 2023-10-08 09:01:00.0,
		//	groupOrderSubmitTime: 2023-10-08 11:00:00.0,
		//	dinerID: 1, 
		//	dinerName: John's Cafe,
		//	dinerAddress: 台北市羅斯福路三段124巷7樓,
		//	dinerType: M
		//	dinerOrderThreshold: 100,
		//	dinerStatus: Submitted, 
		//	buildingName: BB大樓,
		//	buildingAddress: 台北市松山區2號,
		//	userNickName: nickname3,
		//	dinerRating: 4.0, 
		//	menuData: {1飯類: [{productID: 10, productName: 炒飯, productPrice: 65}, 
		//	                  {productID: 1, productName: 滷肉飯, productPrice: 70}, 
		//	           2麵類: [{productID: 8, productName: 義大利麵, productPrice: 90}], 
		//	           3飲料: [{productID: 9, productName: 可樂, productPrice: 60}]
		//			  }
		// }
		
		// First query
		List<Object[]> results = dao.getOneJoin(groupOrderID);

		Map<String, Object> groupOrderData = new HashMap<>();
		Object[] result = results.get(0);
		GroupOrder groupOrder = (GroupOrder) result[0]; // Access the entity object
		groupOrderData.put("groupOrderID", groupOrder.getGroupOrderID());
		groupOrderData.put("orderStatus", groupOrder.getOrderStatus());
		groupOrderData.put("groupOrderCreateTime", groupOrder.getGroupOrderCreateTime());
		groupOrderData.put("groupOrderSubmitTime", groupOrder.getGroupOrderSubmitTime());
		// groupTotalPrice

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

		// Second query
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
		System.out.println("==== menuData returned by GroupOrderServiceImpl.getOneJoinGroupOrder(int groupOrderID) ====\n" 
							+ menuData + "\n====================\n");
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