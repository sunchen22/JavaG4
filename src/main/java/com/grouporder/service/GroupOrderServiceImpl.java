package com.grouporder.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Arrays;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import util.HibernateUtil;
import util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.grouporder.entity.GroupOrder;
import com.product.entity.Product;
import com.productvary.entity.ProductVary;
import com.userinfo.entity.UserInfo;
import com.grouporder.dao.GroupOrderDAOHibernateImpl;
import com.buildinginfo.entity.BuildingInfo;
import com.dinerinfo.entity.DinerInfo;
import com.userorderdetail.dao.UserOrderDetailDAOHibernateImpl;
import com.userorderdetail.entity.*;
import com.userorderdetailvary.entity.*;
import com.userorderdetailvary.dao.UserOrderDetailVaryDAOHibernateImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

public class GroupOrderServiceImpl implements GroupOrderService {
	// One DAO instance for one service instance
	private GroupOrderDAOHibernateImpl dao;
	private UserOrderDetailDAOHibernateImpl userOrderDetailDao;
	private UserOrderDetailVaryDAOHibernateImpl userOrderDetailVaryDao;
	
	// Jedis
	private JedisPool pool;

	public GroupOrderServiceImpl() {
		dao = new GroupOrderDAOHibernateImpl(HibernateUtil.getSessionFactory());
		userOrderDetailDao = new UserOrderDetailDAOHibernateImpl(HibernateUtil.getSessionFactory());
		userOrderDetailVaryDao = new UserOrderDetailVaryDAOHibernateImpl(HibernateUtil.getSessionFactory());
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
			groupOrderData.put("holderID", groupOrder.getUserInfo().getUserID());
			groupOrderData.put("groupTotalPrice", groupOrder.getGroupTotalPrice());

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
		// 麵類: [{productID: 8, productName: 義大利麵, productPrice: 90}],
		// 飲料: [{productID: 9, productName: 可樂, productPrice: 60}]
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
		groupOrderData.put("groupTotalPrice", groupOrder.getGroupTotalPrice());

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
		// 加辣: [{productVaryID: 8, productVaryDes:大辣, productVaryPrice: 0}
		// {productVaryID: 9, productVaryDes:小辣, productVaryPrice: 0}]
		// 加飯: [{productVaryID: 10, productVaryDes:加飯, productVaryPrice: 10}]
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
			return productVaryPrice;
		} else {
			return null;
		}
	}

	@Override
	public Integer calculateSubtotal(Integer productID, Integer quantity, List<Integer> productVaryIDList) {
		int subtotal = getProductPrice(productID);
		System.out.println("~~~~productVaryIDList" + productVaryIDList);
		if (productVaryIDList != null) {
			for (Integer productVaryID : productVaryIDList) {
				if (productVaryID != 0) {
					int productVaryPrice = getProductVaryPrice(productVaryID);
					subtotal += productVaryPrice;
				}
			}
		}

		return subtotal * quantity;
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
	public void addUserToGroup(Object userInfo, Integer groupOrderID) {
		// Update the following two types of data in Redis
		// 1. Users belonging to each group order (for single group order page)
		// SET: groupOrder:1 | value: 3
		//                     value: 4
		// SET: groupOrder:2 | value: 3
		// SET: groupOrder:5 | value: 4

		// 2. Each user's group orders (for nav bar)
		// HASH: user:3 | key: 1 | value: {"groupOrder": "1", "dinerID": "1",
		//                                 "dinerName": "美味小館"}
		//                key: 2 | value: {"groupOrder": "2", "dinerID": "2", "dinerName": "鮮味屋"}
		// HASH: user:4 | key: 1 | value: {"groupOrder": "1", "dinerID": "1",
		//                                 "dinerName": "美味小館"}
		//                key: 5 | value: {"groupOrder": "5", "dinerID": "5", 
		//                                 "dinerName": "Happy Hours"}

		Integer userID = ((UserInfo) userInfo).getUserID();
		try (Jedis jedis = pool.getResource()) {
			jedis.select(6);
			String groupOrderKey = "groupOrder:" + groupOrderID.toString();
			String userKey = "user:" + userID.toString();

			// 1. Update users belonging to each group order
			jedis.sadd(groupOrderKey, userID.toString());

			// 2. Update each user's group orders
			Map<String, String> userData = new HashMap<>();
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();

			userData.put("groupOrder", groupOrderID.toString());
			String dinerID = dao.getOneJoin(groupOrderID).get(0)[1].toString();
			String dinerName = (String) dao.getOneJoin(groupOrderID).get(0)[2];
			userData.put("dinerID", dinerID);
			userData.put("dinerName", dinerName);
			jedis.hset(userKey, groupOrderID.toString(), gson.toJson(userData));

		}
	}
	
	@Override
	public Integer createGroupOrder(Integer dinerID, Integer buildingID, String groupOrderSubmitTime, Object userInfo) {
		try {
			Timestamp groupOrderSubmitTimeToTimestamp = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			groupOrderSubmitTimeToTimestamp = new Timestamp(dateFormat.parse(groupOrderSubmitTime).getTime());
			GroupOrder groupOrder = new GroupOrder();
			groupOrder.setDinerInfo(dao.findByPKDiner(dinerID));
			groupOrder.setBuildingInfo(dao.findByPKBuilding(buildingID));
			groupOrder.setOrderStatus("1");
			groupOrder.setGroupOrderCreateTime(new Timestamp(System.currentTimeMillis()));
			groupOrder.setGroupOrderSubmitTime(groupOrderSubmitTimeToTimestamp);
			groupOrder.setUserInfo((UserInfo) userInfo);
			groupOrder.setGroupTotalPrice(0);
			
			Integer groupOrderID = dao.add(groupOrder);
			
			return groupOrderID;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Boolean userIsGroupMember(Object userInfo, Integer groupOrderID) {
		// SET: groupOrder:1 | value: 3
		// value: 4
		// SET: groupOrder:2 | value: 3
		// SET: groupOrder:5 | value: 4

		String userID = ((UserInfo) userInfo).getUserID().toString();
		try (Jedis jedis = pool.getResource()) {
			jedis.select(6);
			return jedis.sismember("groupOrder:" + groupOrderID.toString(), userID);
		}
	}

	@Override
	public List<Map<String, Object>> navbarJoinedGroupOrders(Object userInfo) {
		// HASH: user:3 | key: 1 | value: {"groupOrder": "1", "dinerID": "1",
		//                                 "dinerName": "美味小館"}
		//                key: 2 | value: {"groupOrder": "2", "dinerID": "2",
		//                                 "dinerName": "鮮味屋"}
		// HASH: user:4 | key: 1 | value: {"groupOrder": "1", "dinerID": "1",
		//                                 "dinerName": "美味小館"}
		//                key: 5 | value: {"groupOrder": "5", "dinerID": "5",
		//                                 "dinerName": "Happy Hours"}

		String userID = ((UserInfo) userInfo).getUserID().toString();
		List<Map<String, Object>> groupOrders = new ArrayList<>();

		try (Jedis jedis = pool.getResource()) {
			jedis.select(6);

			String userKey = "user:" + userID;

			// Get all fields and their values from the Redis Hash
			Map<String, String> userData = jedis.hgetAll(userKey);

			for (Map.Entry<String, String> entry : userData.entrySet()) {
				String field = entry.getKey();
				String jsonGroupOrderInfo = entry.getValue();

				// Parse the JSON data using Gson
				Gson gson = new Gson();
				LinkedTreeMap<String, Object> linkedTreeMap = gson.fromJson(jsonGroupOrderInfo, LinkedTreeMap.class);

				// Convert the LinkedTreeMap to a standard Map
				Map<String, Object> groupOrderInfo = new HashMap<>(linkedTreeMap);

				groupOrders.add(groupOrderInfo);
			}
		}
		return groupOrders;
	}
	
	@Override
	public void addProductToCart(Object userInfo, String groupOrderID, String dinerID, String productID, List<String> varyTypeIDs, String quantity) {
		// LIST: user:3:groupOrder:1:diner:1:cart | key: {"productID": "1",
		//                                                "varyTypeIDs": ["1","4"],
		//                                                 "cartOrder": "1"}
		//                                          value: 2
		//                                        | key: {"productID": "1", 
		//                                                "varyTypeIDs": ["1","3"]
		//                                                "cartOrder": "1"}
		//                                      	value: 3
		// The value in LIST is the quantity added to cart. 
		// When a second "productID": "1", "varyTypeIDs": ["1","4"] is added to cart,
		// it shall add up to the value(quantity), instead of overwriting the value.     
		
		Integer userID = ((UserInfo) userInfo).getUserID();
    
	    int newQuantity = Integer.valueOf(quantity);

       	// Prepare data to store in Redis as a Hash
        Map<String, Object> productData = new HashMap<>();
        productData.put("productID", productID);
        productData.put("varyTypeIDs", varyTypeIDs);

        try (Jedis jedis = pool.getResource()) {
            jedis.select(6);
            String cartKey = "user:" + userID + ":groupOrder:" + groupOrderID + ":diner:" + dinerID + ":cart";

            // Add the "cartOrder" field to the product data
            productData.put("cartOrder", String.valueOf(jedis.hlen(cartKey) + 1));

            // Convert the product data to JSON
            String productDataJson = new Gson().toJson(productData);

            // Check if a similar entry exists and update quantity if found
            for (Map.Entry<String, String> entry : jedis.hgetAll(cartKey).entrySet()) {
                String existingProductDataJson = entry.getKey();

                // Convert the existing entry to a map for comparison
                Map<String, Object> existingProductData = new Gson().fromJson(existingProductDataJson,
                        new TypeToken<Map<String, Object>>() {}.getType());

                // Compare the productID and varyTypeIDs
                if (existingProductData.get("productID").equals(productID.toString())
                        && existingProductData.get("varyTypeIDs").equals(varyTypeIDs)) {
                    // If a similar entry is found, update its quantity
                    int existingQuantity = Integer.parseInt(entry.getValue());
                    int totalQuantity = existingQuantity + newQuantity;

                    // Update the Redis HASH with the new total quantity
                    jedis.hset(cartKey, existingProductDataJson, String.valueOf(totalQuantity));
                    return;
                }
            }

            // If no similar entry is found, add a new entry to the Redis HASH
            jedis.hset(cartKey, productDataJson, String.valueOf(newQuantity));
        }
	    
	}
	@Override
	public List<Map<String,Object>> getCart(Object userInfo, Integer groupOrderID, Integer dinerID) {
		// From Redis:
		// LIST: user:3:groupOrder:1:diner:1:cart | key: {"productID": "1",
		//                                                "varyTypeIDs": ["1","4"],
		//                                                 "cartOrder": "1"}
		//                                          value: 2
		//                                        | key: {"productID": "1", 
		//                                                "varyTypeIDs": ["1","3"]
		//                                                "cartOrder": "1"}
		//                                      	value: 3
		// To Java List:
	    // [
	    //  {"productID": 1, "varyTypeIDs": [1,4], "quantity": 2}, 
	    //  {"productID": 1, "varyTypeIDs": [1,3], "quantity": 3}
	    // ]

		Integer userID = ((UserInfo) userInfo).getUserID();
	    List<Map<String, Object>> cartDataList = new ArrayList<>();

	    try (Jedis jedis = pool.getResource()) {
	        jedis.select(6);
	        String cartKey = "user:" + userID + ":groupOrder:" + groupOrderID + ":diner:" + dinerID + ":cart";

	        // Get the product data for the cart using HGETALL
	        Map<String, String> cartDataMap = jedis.hgetAll(cartKey);

	        for (Map.Entry<String, String> entry : cartDataMap.entrySet()) {
	            // Parse the product data JSON
	            String productDataJson = entry.getKey();
	            Map<String, Object> productData = new Gson().fromJson(productDataJson, new TypeToken<Map<String, Object>>() {}.getType());

	            // Extract quantity from the Redis value
	            int quantity = Integer.parseInt(entry.getValue());

	            // Add quantity to the product data
	            productData.put("quantity", quantity);

	            // Add the product data to the cart
	            cartDataList.add(productData);
	        }
	    }
	    for (Map<String, Object> cartData : cartDataList) {
	    	Integer productID = Integer.valueOf((String) cartData.get("productID"));
	    	Product product = dao.findByPKProduct(productID);
	    	
	    	cartData.put("productName", dao.findByPKProduct(productID).getProductName());
	    	
	    	
	    	ArrayList<Integer> productVaryIDs = (ArrayList<Integer>) cartData.get("varyTypeIDs");
	    	ArrayList<Integer> productVaryList = new ArrayList();
	    	for (Object productVaryID : productVaryIDs) {
	    		productVaryList.add(Integer.parseInt((String)productVaryID));
	    		System.out.println(productVaryID.getClass());
	    	}
	    	
	    	List<String> productVaryDess = new ArrayList();
	    	for (Integer productVaryID : productVaryList) {
	    		String productVaryDes = dao.findByPKProductVary(productVaryID).getProductVaryDes();
	    		productVaryDess.add(productVaryDes);
	    	}
	    	cartData.put("productVaryDess", productVaryDess);
	    	
	    	Integer quantity = (Integer) cartData.get("quantity");
	    	cartData.put("productAndVaryPrice", calculateSubtotal(productID, Integer.valueOf(quantity), productVaryList));
	    }
	    
	    return cartDataList;
	}
	
	@Override
	public void checkoutCart(Object userInfo, Integer groupOrderID, Integer dinerID) {
		Integer userID = ((UserInfo) userInfo).getUserID();
		List<Map<String,Object>> cartDataList = getCart(userInfo, groupOrderID, dinerID);
		System.out.println("~~~~~checkoutCart cartData " + cartDataList);
		
		// 1. Add rows to userOrderDetail and userOrderDetailVary
		for (Map<String,Object> cartData : cartDataList) {
			int productID = Integer.valueOf((String) cartData.get("productID"));
			int quantity = (Integer) cartData.get("quantity");
			
			ArrayList<Integer> productVaryIDs = (ArrayList<Integer>) cartData.get("varyTypeIDs");
	    	ArrayList<Integer> productVaryList = new ArrayList();
	    	for (Object productVaryID : productVaryIDs) {
	    		productVaryList.add(Integer.parseInt((String)productVaryID));
	    	}
			
	    	int subtotal = calculateSubtotal(productID, quantity, productVaryList);
			
			// Add a row to userOrderDetail
			UserOrderDetail userOrderDetail = new UserOrderDetail();
			userOrderDetail.setProductID(productID);
			userOrderDetail.setProductQuantity(quantity);
			userOrderDetail.setUserItemPrice(subtotal);
			userOrderDetail.setUserID(userID);
			userOrderDetail.setGroupOrder(dao.findByPK(groupOrderID));
			userOrderDetail.setUserPaymentTime(new Timestamp(System.currentTimeMillis()));
			Integer userOrderDetailID = userOrderDetailDao.add(userOrderDetail);
			
			// Update groupTotalPrice in groupOrder
			int groupTotalPrice = dao.findByPK(groupOrderID).getGroupTotalPrice();
			groupTotalPrice += subtotal;
			dao.findByPK(groupOrderID).setGroupTotalPrice(groupTotalPrice);
			
			// Add a row to userOrderDetailVary
			if (productVaryList.size() > 0) {
				UserOrderDetailVary userOrderDetailVary = new UserOrderDetailVary();
				userOrderDetailVary.setUserOrderDetail(userOrderDetailDao.findByPK(userOrderDetailID));
				
				int i = 1;
				for(Integer productVaryID : productVaryList) {
					if (productVaryID != null) {
						if (i == 1) {
							userOrderDetailVary.setProductVaryID1(productVaryID);
							i++;
						} else if (i == 2) {
							userOrderDetailVary.setProductVaryID2(productVaryID);
							i++;
						} else if (i == 3) {
							userOrderDetailVary.setProductVaryID3(productVaryID);
							i++;
						} else {
							userOrderDetailVary.setProductVaryID4(productVaryID);
						}
					}
				}
				
				userOrderDetailVaryDao.add(userOrderDetailVary);
			}
			
		}
		
		// 2. Remove Redis cart data
		try (Jedis jedis = pool.getResource()) {
		    jedis.select(6);
		    
		    String cartKey = "user:" + userID + ":groupOrder:" + groupOrderID + ":diner:" + dinerID + ":cart";
		    jedis.del(cartKey);
		}
		
		// 3. Check if groupTotalPrice reaches dinerOrderThreshold
		int dinerOrderThreshold = dao.findByPKJoinDiner(groupOrderID).getDinerOrderThreshold();
		int groupTotalPrice = dao.findByPK(groupOrderID).getGroupTotalPrice();
		if (groupTotalPrice > dinerOrderThreshold) {
			dao.findByPK(groupOrderID).setOrderStatus("2");
		}
	}
	
	@Override
	public List<Map<String,Object>> getUserOrderDetailOnThisGroupOrder(Integer groupOrderID, Object userInfo) {
		// [
		//  {productName=陽春麵, productQuantity=1, userItemPrice=70, productVaryList=[大份, null, null, null]}, 
		//  {productName=雞腿飯, productQuantity=2, userItemPrice=230, productVaryList=[加荷包蛋, null, null, null]}, 
		//  {productName=綠茶, productQuantity=1, userItemPrice=40, productVaryList=[正常冰, 正常糖, null, null]}
		// ]
		
		Integer userID = ((UserInfo) userInfo).getUserID();
		List<Object[]> results = userOrderDetailDao.findByGroupOrderAndUser(groupOrderID, userID);
		
		List<Map<String, Object>> resultList = new ArrayList<>();

        // Iterate through the results
        for (Object[] result : results) {
            Map<String, Object> rowMap = new LinkedHashMap<>();
            rowMap.put("productName", (String) result[0]);
            rowMap.put("productQuantity", (int) result[1]);
            rowMap.put("userItemPrice", (int) result[2]);

            // Create a list to store productVary1 to productVary4
            List<String> productVaryList = new ArrayList<>();
            for (int i = 3; i <= 6; i++) {
                productVaryList.add((String) result[i]);
            }
            rowMap.put("productVaryList", productVaryList);

            resultList.add(rowMap);
        }
		
		return resultList;
	}
	
	@Override
	public List<Map<String, Object>> searchGroupOrder(String nameKeyword, String addressKeyword) {
		List<Object[]> results = dao.getGroupOrderByKeywords(nameKeyword, addressKeyword);
		
		if (results != null) {
//			List<Map<String, Object>> resultList = new ArrayList<>();
//			for (Object[] result : results) {
//				Map<String, Object> groupOrderData = new LinkedHashMap<>();
//				GroupOrder groupOrder = (GroupOrder) result[0]; // Access the entity object
//				groupOrderData.put("groupOrderID", groupOrder.getGroupOrderID());
//				groupOrderData.put("orderStatus", groupOrder.getOrderStatus());
//				groupOrderData.put("groupOrderCreateTime", groupOrder.getGroupOrderCreateTime());
//				groupOrderData.put("groupOrderSubmitTime", groupOrder.getGroupOrderSubmitTime());
//				groupOrderData.put("holderID", groupOrder.getUserInfo().getUserID());
//				groupOrderData.put("groupTotalPrice", groupOrder.getGroupTotalPrice());
//	
//				groupOrderData.put("dinerID", (int) result[1]);
//				groupOrderData.put("dinerName", (String) result[2]);
//				groupOrderData.put("dinerAddress", (String) result[3]);
//				groupOrderData.put("dinerType", (String) result[4]);
//				groupOrderData.put("dinerOrderThreshold", (Integer) result[5]);
//				groupOrderData.put("dinerStatus", (String) result[6]);
//				groupOrderData.put("buildingName", (String) result[7]);
//				groupOrderData.put("buildingAddress", (String) result[8]);
//				groupOrderData.put("userNickName", (String) result[9]);
//				groupOrderData.put("dinerRating", (double) result[10]);
//				
//				resultList.add(groupOrderData);
//			}
			
			List<Map<String, Object>> resultList = new ArrayList<>();


	        for (Object[] row : results) {
	            Map<String, Object> groupOrderData = new LinkedHashMap<>();
	            groupOrderData.put("groupOrderID", row[0]);
	            groupOrderData.put("dinerID", row[1]);
	            groupOrderData.put("buildingID", row[2]);
	            groupOrderData.put("orderStatus", row[3]);
	            groupOrderData.put("groupOrderCreateTime", row[4]);
	            groupOrderData.put("groupOrderSubmitTime", row[5]);
	            groupOrderData.put("holderID", row[6]);
	            groupOrderData.put("groupTotalPrice", row[7]);
	            groupOrderData.put("dinerName", row[8]);
	            groupOrderData.put("dinerAddress", row[9]);
	            groupOrderData.put("dinerType", row[10]);
	            groupOrderData.put("dinerOrderThreshold", row[11]);
	            groupOrderData.put("dinerStatus", row[12]);
	            groupOrderData.put("buildingName", row[13]);
	            groupOrderData.put("buildingAddress", row[14]);
	            groupOrderData.put("userNickName", row[15]);
	            groupOrderData.put("dinerRating", row[16]);

	            resultList.add(groupOrderData);
	        }
			
			return resultList;
			
		}
		return null;
	}
	
	@Override
	public void changeAllGroupOrderStatus() {
		
		// 1. Change all group orders' status from 1 to 4, and from 2 to 3
		List<GroupOrder> results = dao.getAllStatusOneTwo();
		for (GroupOrder groupOrder : results) {
			if (groupOrder.getOrderStatus().equals("1")) {
				groupOrder.setOrderStatus("4");
			} else {
				groupOrder.setOrderStatus("3");
			}
		}
		
		// 2. Remove Redis data(cart, joined groups, group members)
		try (Jedis jedis = pool.getResource()) {
		    jedis.select(6);
		    jedis.flushDB();
		}
	}
	
	@Override
	public void clearCart(Object userInfo, Integer groupOrderID, Integer dinerID) {
		Integer userID = ((UserInfo) userInfo).getUserID();
			
		// Remove Redis cart data
		try (Jedis jedis = pool.getResource()) {
		    jedis.select(6);
		    
		    String cartKey = "user:" + userID + ":groupOrder:" + groupOrderID + ":diner:" + dinerID + ":cart";
		    jedis.del(cartKey);
		}
		
	}
	
	
}