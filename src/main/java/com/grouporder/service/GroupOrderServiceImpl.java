package com.grouporder.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashMap;

import util.HibernateUtil;
import util.JedisUtil;

import com.grouporder.entity.GroupOrder;
import com.product.entity.Product;
import com.productvary.entity.ProductVary;
import com.userinfo.entity.UserInfo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.grouporder.dao.GroupOrderDAOHibernateImpl;
import com.buildinginfo.entity.BuildingInfo;
import com.dinerinfo.entity.DinerInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GroupOrderServiceImpl implements GroupOrderService {
	// One DAO instance for one service instance
	private GroupOrderDAOHibernateImpl dao;
	// Jedis
	private JedisPool pool;

	public GroupOrderServiceImpl() {
		dao = new GroupOrderDAOHibernateImpl(HibernateUtil.getSessionFactory());
		// Jedis
		pool = JedisUtil.getJedisPool();
	}

	@Override
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

	@Override
	public Map<String, Object> getOneJoinGroupOrder(Integer groupOrderID) {
		// returns a Map in the following format:
		// {
		// groupOrderID: 1,
		// orderStatus: 1,
		// groupOrderCreateTime: 2023-10-08 09:01:00.0,
		// groupOrderSubmitTime: 2023-10-08 11:00:00.0,
		// dinerID: 1,
		// dinerName: John's Cafe,
		// dinerAddress: 台北市羅斯福路三段124巷7樓,
		// dinerType: M
		// dinerOrderThreshold: 100,
		// dinerStatus: Submitted,
		// buildingName: BB大樓,
		// buildingAddress: 台北市松山區2號,
		// userNickName: nickname3,
		// dinerRating: 4.0,
		// menuData: {1飯類: [{productID: 10, productName: 炒飯, productPrice: 65},
		// {productID: 1, productName: 滷肉飯, productPrice: 70},
		// 2麵類: [{productID: 8, productName: 義大利麵, productPrice: 90}],
		// 3飲料: [{productID: 9, productName: 可樂, productPrice: 60}]
		// }
		// }

		Map<String, Object> groupOrderData = new HashMap<>();

		// First query
		List<Object[]> results = dao.getOneJoin(groupOrderID);
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
		List<Object[]> menuResults = dao.getOneJoinMenu((int) result[1]);

		Map<String, List<Map<String, Object>>> menuData = new LinkedHashMap<>();
		for (Object[] menuResult : menuResults) {
			Map<String, Object> productData = new HashMap();

			productData.put("productID", (int) menuResult[0]);
			productData.put("productName", (String) menuResult[1]);
			productData.put("productPrice", (int) menuResult[2]);

			if (menuData.get((String) menuResult[3]) == null) {
				List<Map<String, Object>> productDataList = new ArrayList<>();
				productDataList.add(productData);
				menuData.put((String) menuResult[3], productDataList);
			} else {
				menuData.get((String) menuResult[3]).add(productData);
			}
		}
		System.out
				.println("==== menuData returned by GroupOrderServiceImpl.getOneJoinGroupOrder(int groupOrderID) ====\n"
						+ menuData + "\n====================\n");
		groupOrderData.put("menuData", menuData);

		return groupOrderData;

	}

	@Override
	public String getProductAndVaryOptions(Integer productID) {
		// returns a Map in the following format:
		// {
		// productID: 1,
		// productName: 滷肉飯,
		// productPrice: 70,
		// productRemark: 滷肉飯說明,
		// varyTypes: {
		// 1加辣: [{productVaryID: 8, productVaryDes:大辣, productVaryPrice: 0}
		// {productVaryID: 9, productVaryDes:小辣, productVaryPrice: 0}]
		// 2加飯: [{productVaryID: 10, productVaryDes:加飯, productVaryPrice: 10}]
		// }
		// }
		Map<String, Object> productAndVaryOptionsData = new HashMap<>();

		// Query for product basic info
		Product product = dao.findByPKProduct(productID);
		productAndVaryOptionsData.put("productName", product.getProductName());
		productAndVaryOptionsData.put("productPrice", product.getProductPrice());
		productAndVaryOptionsData.put("productRemark", product.getProductRemark());

		// Query for product vary
		List<Object[]> productVaryResults = dao.getProductJoinProductVary(productID);

		Map<String, List<Map<String, Object>>> varyTypeMap = new LinkedHashMap<>();
		for (Object[] productVaryResult : productVaryResults) {
			// There won't be the "varyTypes" key if the product doesn't have any product
			// vary
			if (productVaryResult[0] != null) {
				Map<String, Object> productVaryMap = new HashMap();

				productVaryMap.put("productVaryID", (int) productVaryResult[0]);
				productVaryMap.put("productVaryDes", (String) productVaryResult[1]);
				productVaryMap.put("productVaryPrice", (int) productVaryResult[2]);

				if (varyTypeMap.get((String) productVaryResult[3]) == null) {
					List<Map<String, Object>> productVaryList = new ArrayList<>();
					productVaryList.add(productVaryMap);
					varyTypeMap.put((String) productVaryResult[3], productVaryList);
				} else {
					varyTypeMap.get((String) productVaryResult[3]).add(productVaryMap);
				}

				productAndVaryOptionsData.put("varyTypes", varyTypeMap);
			}
		}
		System.out.println(
				"==== ProductAndVaryOptionsData generated by GroupOrderServiceImpl.getProductAndVaryOptions(Integer productID) ====\n"
						+ productAndVaryOptionsData + "\n====================\n");

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(productAndVaryOptionsData);

		System.out.println(
				"==== json string returned by GroupOrderServiceImpl.getProductAndVaryOptions(Integer productID) ====\n"
						+ json + "\n====================\n");
		return json;

	}

	@Override
	public Integer getProductPrice(Integer productID) {
		Product product = dao.findByPKProduct(productID);
		if (product != null) {
			int productPrice = product.getProductPrice();
			System.out.println("PRODUCT PRICE: " + productPrice);
			return productPrice;
		} else {
			return null;
		}
	}

	@Override
	public Integer getProductVaryPrice(Integer productVaryID) {
		ProductVary productVary = dao.findByPKProductVary(productVaryID);
		if (productVary != null) {
			int productVaryPrice = productVary.getProductVaryPrice();
			System.out.println("PRODUCTVARYPRICE: " + productVaryPrice);
			return productVaryPrice;
		} else {
			return null;
		}
	}

	@Override
	public byte[] getGroupOrderDinerImage(Integer groupOrderID) {
		DinerInfo dinerInfo = dao.findByPKJoinDiner(groupOrderID);
		if (dinerInfo != null) {
			byte[] imageData = dinerInfo.getDinerBlob();
			return imageData;
		} else {
			return null;
		}
	}

	@Override
	public byte[] getGroupOrderProductImage(Integer productID, Integer no) {
		Product product = dao.findByPKProduct(productID);
		byte[] imageData;
		if (product != null) {
			switch (no) {
			case 1:
				imageData = product.getProductBlob1();
				break;
			case 2:
				imageData = product.getProductBlob2();
				break;
			case 3:
				imageData = product.getProductBlob3();
				break;
			default:
				imageData = null;
			}

			return imageData;

		} else {
			return null;
		}
	}

	@Override
	public Boolean userIsGroupMember(Object userInfo, Integer groupOrderID) {
		String userID = ((UserInfo) userInfo).getUserID().toString();
		try (Jedis jedis = pool.getResource()) {
			jedis.select(6);
			return jedis.sismember("groupOrder:" + groupOrderID.toString(), userID);
		}
	}

	@Override
	public void addUserToGroup(Object userInfo, Integer groupOrderID, String dinerName) {
		// Update the following two types of data in Redis
		// 1. Users belonging to each group order (for group order page)
		// {groupOrder:1 : [12, 11, 14, 13, 15]}
		// {groupOrder:2 : [13, 11]}
		// 2. Each user's group orders (for nav bar)
		// {user:12 : [{groupOrder:1, dinerName: xxx}]}
		// {user:11 : [{groupOrder:1, dinerName: xxx}, {groupOrder:2, dinerName: yyy}]}
		// {user:14 : [{groupOrder:1, dinerName: xxx}]}
		// {user:13 : [{groupOrder:1, dinerName: xxx}, {groupOrder:2, dinerName: yyy}]}
		// {user:15 : [{groupOrder:1, dinerName: xxx}]}
		Integer userID = ((UserInfo) userInfo).getUserID();
		try (Jedis jedis = pool.getResource()) {
			jedis.select(6);
			String groupOrderKey = "groupOrder:" + groupOrderID.toString();
			String userKey = "user:" + userID.toString();

			// Update users belonging to each group order
			jedis.sadd(groupOrderKey, userID.toString());

			// Update each user's group orders
			Map<String, String> userData = new HashMap<>();
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();

			userData.put("groupOrder", groupOrderID.toString());
			userData.put("dinerName", dinerName);
			jedis.hset(userKey, groupOrderID.toString(), gson.toJson(userData));
			
		}
	}

	@Override
	public List<Map<String, Object>> navbarJoinedGroupOrders(Object userInfo) {
	    String userID = ((UserInfo) userInfo).getUserID().toString();
	    List<Map<String, Object>> groupOrders = new ArrayList<>();

	    try (Jedis jedis = pool.getResource()) {
	        jedis.select(6);

	        String userKey = "user:" + userID;
	        Map<String, String> userData = jedis.hgetAll(userKey);

	        for (Map.Entry<String, String> entry : userData.entrySet()) {
	            String groupOrderID = entry.getKey();
	            String jsonGroupOrderInfo = entry.getValue();

	            // Manually parse the JSON data
	            Map<String, Object> groupOrderInfo = new HashMap<>();
	            
	            // Split the JSON data into key-value pairs
	            String[] keyValuePairs = jsonGroupOrderInfo.split(",");
	            for (String keyValuePair : keyValuePairs) {
	                String[] keyValue = keyValuePair.split(":");
	                String key = keyValue[0].trim().replaceAll("[\"{}]", "");
	                String value = keyValue[1].trim().replaceAll("[\"{}]", "");
	                groupOrderInfo.put(key, value);
	            }

	            groupOrders.add(groupOrderInfo);
	        }
	    }
	    System.out.println("~~~~~~groupOrders" + groupOrders);
	    return groupOrders;
	}


}