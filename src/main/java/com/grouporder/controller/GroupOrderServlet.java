package com.grouporder.controller;

import java.io.*;
import java.util.Map;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.grouporder.entity.GroupOrder;
import com.grouporder.service.GroupOrderServiceImpl;

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
		String forwardPath = "";
		
		switch (action) {
			case "getAll":
				getAllGroupOrder(req, res);
				break;
			case "getOne":
				getOneGroupOrder(req, res);
				break;
			case "openOrderDetailModal":
				openOrderDetailModal(req, res);
				break;
			case "calculateSubtotal":
				calculateSubtotal(req, res);
				break;
			default:
//				forwardPath = "/index.jsp";
		}
       
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
    
    private void getAllGroupOrder(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("request via AJAX comes in");

        try {
            // Process the request: get the JSON data from the request body
            BufferedReader reader = req.getReader();
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();

            // Request: Parse JSON into the Java object using Gson
            Gson gson = new Gson();
            AJAXRequestData requestData = gson.fromJson(jsonBuilder.toString(), AJAXRequestData.class);

            // Request: Access requestData and its fields
            String keyword = requestData.getKeyword();
            String address = requestData.getAddress();

            // Process the data
            System.out.println("Keyword: " + keyword);
            System.out.println("Address: " + address);
            
            // Prepare the response and send it
            if (keyword.isBlank() && address.isBlank()) {
            	String json = groupOrderServiceImpl.getAllJoinGroupOrder(1);
            	System.out.println(json);
            	
            	// Send a JSON response
            	res.setContentType("application/json; charset=UTF-8"); // Set content type to JSON
            	res.getWriter().write(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set 400 Bad Request status on error
        }
    }
    
    private void getOneGroupOrder(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	int groupOrderID = Integer.valueOf(req.getParameter("groupOrderID"));
    	// Call service to get the data needed and store it in request attribute
    	Map<String, Object> groupOrderData = groupOrderServiceImpl.getOneJoinGroupOrder(groupOrderID);
    	req.setAttribute("groupOrderData", groupOrderData);
    	req.setAttribute("menuData", groupOrderData.get("menuData"));
   
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher("consumer/oneGroupOrder.jsp");
		dispatcher.forward(req, res);
    }
    
    private void openOrderDetailModal(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	// JavaScript AJAX request url: 
    	// contextPath + "/GroupOrder.do?action=openOrderDetailModal&productID=" + productID
    	// "contextPath" is a var declarared in <script></script> of oneGroupOrder.jsp
    	System.out.println("request action=openOrderDetailModal via AJAX comes in");

        try {
            // Process the request: get the productID parameter
        	int productID = Integer.valueOf(req.getParameter("productID"));

            // Prepare the response and send it
        	String json = groupOrderServiceImpl.getProductAndVaryOptions(productID);
            if (json != null) {
            	// Send a JSON response
            	res.setContentType("application/json; charset=UTF-8"); // Set content type to JSON
            	res.getWriter().write(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set 400 Bad Request status on error
        }
    }
    
    private void calculateSubtotal(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	// JavaScript AJAX request url: 
    	// contextPath + "/GroupOrder.do?action=calculateSubtotal"
    	// "contextPath" is a var declarared in <script></script> of oneGroupOrder.jsp
    	System.out.println("request action=calculateSubtotal via AJAX comes in");

        try {
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

            // Prepare the response and send it
    		int subtotal = groupOrderServiceImpl.getProductPrice(productID);
        	for (JsonElement element: productVaryIDList) {
        		int productVaryID = element.getAsInt();
        		if (productVaryID != 0) {
	        		int productVaryPrice = groupOrderServiceImpl.getProductVaryPrice(productVaryID);
	        		System.out.println("ProductVaryPrice: " + productVaryPrice);
	        		subtotal += productVaryPrice;
        		}
        	}
        	subtotal = subtotal * quantity;
        
        	String json = String.valueOf(subtotal); 
            if (json != null) {
            	// Send a JSON response
            	res.setContentType("application/json; charset=UTF-8"); // Set content type to JSON
            	res.getWriter().write(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set 400 Bad Request status on error
        }
    }
}
