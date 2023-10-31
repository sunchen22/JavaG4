package com.grouporder.controller;

import java.io.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.grouporder.entity.GroupOrder;
import com.grouporder.service.GroupOrderServiceImpl;
import com.userorderdetail.entity.UserOrderDetail;

@WebServlet("/GroupOrder.do")
public class GroupOrderServlet extends HttpServlet {
	// One service instance for one servlet instance
	private GroupOrderServiceImpl groupOrderServiceImpl;
	@Override
	public void init() throws ServletException {
		groupOrderServiceImpl = new GroupOrderServiceImpl();
	}
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
		
		switch (action) {
//			case "getAll":
//				getAllGroupOrder(req, res);
//				break;
			case "getOne":
				getOneGroupOrder(req, res);
				break;
			case "openOrderDetailModal":
				openOrderDetailModal(req, res);
				break;
			case "calculateSubtotal":
				calculateSubtotal(req, res);
				break;
			case "join":
				joinThisGroupOrder(req, res);
				break;
			case "create":
				createAndJoinGroupOrder(req, res);
				break;
			case "addToCart":
				addToCart(req, res);
				break;
			case "openCart":
				openCart(req, res);
				break;
			case "checkout":
				checkout(req, res);
				break;
			case "searchGroupOrder":
				searchGroupOrder(req, res);
				break;
			case "Timeup":
				timeUpAllGroupOrder(req, res);
				break;
			case "clearCart":
				clearCart(req, res);
				break;
			default:
//				forwardPath = "/index.jsp";
		}
       
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
    
//    private void getAllGroupOrder(HttpServletRequest req, HttpServletResponse res) {
//    	System.out.println("request via AJAX comes in");
//
//        try {
//            // Process the request: get the JSON data from the request body
//            BufferedReader reader = req.getReader();
//            StringBuilder jsonBuilder = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                jsonBuilder.append(line);
//            }
//            reader.close();
//
//            // Request: Parse JSON into the Java object using Gson
//            Gson gson = new Gson();
//            AJAXRequestData requestData = gson.fromJson(jsonBuilder.toString(), AJAXRequestData.class);
//
//            // Request: Access requestData and its fields
//            String keyword = requestData.getKeyword();
//            String address = requestData.getAddress();
//
//            // Process the data
//            System.out.println("Keyword: " + keyword);
//            System.out.println("Address: " + address);
//            
//            // Prepare the response and send it
//            if (keyword.isBlank() && address.isBlank()) {
//            	String json = groupOrderServiceImpl.getAllJoinGroupOrder(1);
//            	System.out.println(json);
//            	
//            	// Send a JSON response
//            	res.setContentType("application/json; charset=UTF-8"); // Set content type to JSON
//            	res.getWriter().write(json);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set 400 Bad Request status on error
//        }
//    }
    
    private void getOneGroupOrder(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	// JS: href=${contextPath}/GroupOrder.do?action=getOne&groupOrderID=${item.groupOrderID}
    	try {
	    	Integer groupOrderID = Integer.valueOf(req.getParameter("groupOrderID"));
	    	// Call service to get the data needed and store it in request attribute
	    	Map<String, Object> groupOrderData = groupOrderServiceImpl.getOneJoinGroupOrder(groupOrderID);
	    	req.setAttribute("groupOrderData", groupOrderData);
	    	req.setAttribute("menuData", groupOrderData.get("menuData"));
	   
	    	Object userInfo = req.getSession().getAttribute("loginUserInfo");
	    	if (userInfo != null) {
	    		Boolean userIsGroupMember = groupOrderServiceImpl.userIsGroupMember(userInfo, groupOrderID);
	    		req.getSession().setAttribute("userIsGroupMember", userIsGroupMember);
		    	
		    	
	    		 List<Map<String,Object>> userOrderDetailData = groupOrderServiceImpl.getUserOrderDetailOnThisGroupOrder(groupOrderID, userInfo);
	    		if (userOrderDetailData != null) {
	    			req.setAttribute("userOrderDetailData", userOrderDetailData);
	    		}
		    	
	    	}
	    	
			res.setContentType("text/html; charset=UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher("consumer/oneGroupOrder.jsp");		
			dispatcher.forward(req, res);
		
	    } catch (Exception e) {
	        e.printStackTrace();
	        res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set 400 Bad Request status on error
	    }
    }
    
    private void openOrderDetailModal(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	// JavaScript AJAX request url: 
    	// contextPath + "/GroupOrder.do?action=openOrderDetailModal&productID=" + productID
    	// "contextPath" is a var declarared in <script></script> of oneGroupOrder.jsp
    	System.out.println("request action=openOrderDetailModal via AJAX comes in");

        // Process the request: get the productID parameter
    	int productID = Integer.valueOf(req.getParameter("productID"));

        // Prepare the response and send it
    	String json = groupOrderServiceImpl.getProductAndVaryOptions(productID);
        if (json != null) {
        	// Set content type to JSON
        	res.setContentType("application/json; charset=UTF-8");
        	res.getWriter().write(json);
        }
    }
    
    private void calculateSubtotal(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	// JavaScript AJAX request url: 
    	// contextPath + "/GroupOrder.do?action=calculateSubtotal"
    	// "contextPath" is a var declarared in <script></script> of oneGroupOrder.jsp
    	System.out.println("request action=calculateSubtotal via AJAX comes in");

    	// Process the request: get the JSON data from the request body
        BufferedReader reader = req.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        reader.close();
        String jsonStr = jsonBuilder.toString();
        Gson gson = new GsonBuilder().create();
        JsonObject jsonObject = gson.fromJson(jsonStr, JsonObject.class);
        int productID = jsonObject.get("productID").getAsInt();
        int quantity = jsonObject.get("quantity").getAsInt();

        JsonArray productVaryIDList = jsonObject.get("productVaryIDList").getAsJsonArray();

        // Convert JsonArray to List<Integer>
        List<Integer> productVaryIDs = new ArrayList<>();
        for (JsonElement element : productVaryIDList) {
            productVaryIDs.add(element.getAsInt());
        }

        // Call the service layer method to calculate the subtotal
        int subtotal = groupOrderServiceImpl.calculateSubtotal(productID, quantity, productVaryIDs);

        String json = String.valueOf(subtotal);
        if (json != null) {
            // Send a JSON response
            res.setContentType("application/json; charset=UTF-8"); // Set content type to JSON
            res.getWriter().write(json);
        }
        
    }
    
    private void joinThisGroupOrder(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	// EL: href="${pageContext.request.contextPath}/GroupOrder.do?action=join&groupOrderID=${groupOrderData.groupOrderID}"
	    try {
    		Integer groupOrderID = Integer.valueOf(req.getParameter("groupOrderID"));
	    	
	    	Object userInfo = req.getSession().getAttribute("loginUserInfo");
	    	if (userInfo != null && groupOrderID != null) {
	    		groupOrderServiceImpl.addUserToGroup(userInfo, groupOrderID);
	    		
	    		// Also need to set this attribute in action=login of UserInfoServlet.java
	    		// so that the joined group orders data can be loaded from Redis upon user logging in
	    		ArrayList<Map<String, Object>> navbarJoinedGroupOrders = (ArrayList<Map<String, Object>>) groupOrderServiceImpl.navbarJoinedGroupOrders(userInfo);
	    		req.getSession().setAttribute("navbarJoinedGroupOrders", navbarJoinedGroupOrders);
	    		
	    	}
	    	res.setContentType("text/html; charset=UTF-8");
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/GroupOrder.do?action=getOne&groupOrderID=" + String.valueOf(groupOrderID));		
//			dispatcher.forward(req, res);
	    	String redirectURL = req.getContextPath() + "/GroupOrder.do?action=getOne&groupOrderID=" + String.valueOf(groupOrderID);
    		res.sendRedirect(redirectURL);
		
	    } catch (Exception e) {
	        e.printStackTrace();
	        res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set 400 Bad Request status on error
	    }
    }
    
    private void createAndJoinGroupOrder(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	// EL: href="${pageContext.request.contextPath}/GroupOrder.do?action=create"
    	try {
	    	Object userInfo = req.getSession().getAttribute("loginUserInfo");
	    	RequestDispatcher dispatcher;
	    	if (userInfo != null) {
	    		Integer dinerID = Integer.valueOf(req.getParameter("dinerID"));
	    		Integer buildingID = Integer.valueOf(req.getParameter("buildingID"));
	    		String groupOrderSubmitTime = req.getParameter("groupOrderSubmitTime");
	    		
	    		Integer groupOrderID = groupOrderServiceImpl.createGroupOrder(dinerID, buildingID, groupOrderSubmitTime, userInfo);
	    		
	    		groupOrderServiceImpl.addUserToGroup(userInfo, groupOrderID);
	    		ArrayList<Map<String, Object>> navbarJoinedGroupOrders = (ArrayList<Map<String, Object>>) groupOrderServiceImpl.navbarJoinedGroupOrders(userInfo);
	    		req.getSession().setAttribute("navbarJoinedGroupOrders", navbarJoinedGroupOrders);
	    		
	    		res.setContentType("text/html; charset=UTF-8");
//	    		dispatcher = req.getRequestDispatcher("/GroupOrder.do?action=getOne&groupOrderID=" + String.valueOf(groupOrderID));		
//	    		dispatcher.forward(req, res);
	    		String redirectURL = req.getContextPath() + "/GroupOrder.do?action=getOne&groupOrderID=" + String.valueOf(groupOrderID);
	    		res.sendRedirect(redirectURL);
	    	} 
	    } catch (Exception e) {
	        e.printStackTrace();
	        res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set 400 Bad Request status on error
	    }
    }
    
    private void addToCart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	// JavaScript AJAX request url: 
    	// contextPath + "/GroupOrder.do?action=addToCart"
    	
    	// Get parameters from the request.
        Object userInfo = req.getSession().getAttribute("loginUserInfo");
        String groupOrderID = req.getParameter("groupOrderID");
        String dinerID = req.getParameter("dinerID");
        String productID = req.getParameter("productID");
        List<String> varyTypeIDs = new ArrayList<>(); // Adjust the array size as needed
        String quantity = req.getParameter("quantity");

        // Collect the productVaryIDs from parameters that start with "productVaryCount"
        for (int i = 1; i <= 4; i++) {
            String paramName = "varyTypeCount" + i;
            String varyTypeCount = req.getParameter(paramName);
            if (varyTypeCount != null && !varyTypeCount.isEmpty()) {
            	varyTypeIDs.add(varyTypeCount);
            }
        }

        if (userInfo != null) {
            groupOrderServiceImpl.addProductToCart(userInfo, groupOrderID, dinerID, productID, varyTypeIDs, quantity);
            System.out.println("Added to cart: " + groupOrderID + ", " + dinerID + ", " + productID);
        }
    
    }
    
    private void openCart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	// JavaScript AJAX request url: 
    	// contextPath + "/GroupOrder.do?action=openCart&groupOrderID=" + groupOrderID + "&dinerID=" + dinerID
    	System.out.println("request action=openCart via AJAX comes in");
    	
    	try {
	    	Object userInfo = req.getSession().getAttribute("loginUserInfo");
	    	if (userInfo != null) {
	    		Integer groupOrderID = Integer.valueOf(req.getParameter("groupOrderID"));
	    		Integer dinerID = Integer.valueOf(req.getParameter("dinerID"));
	    		
	    		// Prepare the response and send it
	    		List<Map<String,Object>> cartData = groupOrderServiceImpl.getCart(userInfo, groupOrderID, dinerID);
	    		if (cartData != null) {
	    			Gson gson = new Gson();
	    			String json = gson.toJson(cartData);
	    			// Set content type to JSON
	    			res.setContentType("application/json; charset=UTF-8");
	    			res.getWriter().write(json);
	            }
	    	} 
	    } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set 400 Bad Request status on error
        }
    }
    
    private void checkout(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	// EL: href="${pageContext.request.contextPath}/GroupOrder.do?action=checkout"
    	try {
    		Object userInfo = req.getSession().getAttribute("loginUserInfo");
    		Integer groupOrderID = Integer.valueOf(req.getParameter("groupOrderID"));
    		Integer dinerID = Integer.valueOf(req.getParameter("dinerID"));
    		groupOrderServiceImpl.checkoutCart(userInfo, groupOrderID, dinerID);
    	
    		res.setContentType("text/html; charset=UTF-8");
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/GroupOrder.do?action=getOne&groupOrderID=" + groupOrderID.toString());		
//			dispatcher.forward(req, res);
    		String redirectURL = req.getContextPath() + "/GroupOrder.do?action=getOne&groupOrderID=" + String.valueOf(groupOrderID);
    		res.sendRedirect(redirectURL);
    		
    	} catch (Exception e) {
	        e.printStackTrace();
	        res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set 400 Bad Request status on error
    	}
    }
    
    private void searchGroupOrder(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	// EL: action="${pageContext.request.contextPath}/GroupOrder.do?action=searchGroupOrder"
    	try {
    		String nameKeyword = req.getParameter("keyword");
            String addressKeyword = req.getParameter("address");
                        
            List<Map<String, Object>> groupOrderList = groupOrderServiceImpl.searchGroupOrder(nameKeyword, addressKeyword);
            req.setAttribute("groupOrderList", groupOrderList);
            
    		res.setContentType("text/html; charset=UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/consumer/search.jsp");		
			dispatcher.forward(req, res);
    	} catch (Exception e) {
	        e.printStackTrace();
	        res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set 400 Bad Request status on error
    	}
    }
    
    private void timeUpAllGroupOrder(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	// EL: href="${pageContext.request.contextPath}/GroupOrder.do?action=Timeup"
    	try {
            groupOrderServiceImpl.changeAllGroupOrderStatus();
            req.getSession().removeAttribute("navbarJoinedGroupOrders");
//            req.getSession().removeAttribute("userIsGroupMember");
            
    		res.setContentType("text/html; charset=UTF-8");
			String redirectURL = req.getContextPath() + "/GroupOrder.do?action=searchGroupOrder";
    		res.sendRedirect(redirectURL);
    	} catch (Exception e) {
	        e.printStackTrace();
	        res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set 400 Bad Request status on error
    	}
    }
    
    private void clearCart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	// EL: href="${pageContext.request.contextPath}/GroupOrder.do?action=clearCart&groupOrderID=${groupOrderData.groupOrderID}&dinerID=${groupOrderData.dinerID}"
    	try {
    		Object userInfo = req.getSession().getAttribute("loginUserInfo");
	    	if (userInfo != null) {
	    		Integer groupOrderID = Integer.valueOf(req.getParameter("groupOrderID"));
	    		Integer dinerID = Integer.valueOf(req.getParameter("dinerID"));
	    		groupOrderServiceImpl.clearCart(userInfo, groupOrderID, dinerID);
	    		
	    		res.setContentType("text/html; charset=UTF-8");
	    		String redirectURL = req.getContextPath() + "/GroupOrder.do?action=getOne&groupOrderID=" + String.valueOf(groupOrderID);
	    		res.sendRedirect(redirectURL);
	    	}
            
    	} catch (Exception e) {
	        e.printStackTrace();
	        res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set 400 Bad Request status on error
    	}
    }
        
}
