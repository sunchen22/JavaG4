package com.grouporder.controller;

import java.io.*;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.google.gson.Gson;
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
    	res.setContentType("application/json; charset=UTF-8"); // Set content type to JSON

        try {
            // Get the JSON data from the request body
            BufferedReader reader = req.getReader();
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();

            // Parse JSON into the Java object using Gson
            Gson gson = new Gson();
            AJAXRequestData requestData = gson.fromJson(jsonBuilder.toString(), AJAXRequestData.class);

            // Access requestData and its fields
            String keyword = requestData.getKeyword();
            String address = requestData.getAddress();

            // Process the data as needed
            System.out.println("Keyword: " + keyword);
            System.out.println("Address: " + address);
            
            
            if (keyword.isBlank() && address.isBlank()) {
            	String json = groupOrderServiceImpl.getAllJoinGroupOrder(1);
            	System.out.println(json);
//            	String json1 = "[{\"groupOrderID\":1,\"orderStatus\":\"1\",\"groupOrderCreateTime\":\"2023-10-08 09:01:00\",\"groupOrderSubmitTime\":\"2023-10-08 11:00:00\",\"dinerName\":\"John\\u0027s Cafe\",\"dinerAddress\":\"台北市羅斯福路三段124巷7樓\",\"dinerType\":\"M\",\"dinerOrderThreshold\":100,\"dinerStatus\":\"Submitted\",\"buildingName\":\"BB大樓\",\"buildingAddress\":\"台北市松山區2號\",\"userNickName\":\"nickname3\",\"dinerRating\":2.5},{\"groupOrderID\":2,\"orderStatus\":\"2\",\"groupOrderCreateTime\":\"2023-10-08 09:09:00\",\"groupOrderSubmitTime\":\"2023-10-08 11:30:00\",\"dinerName\":\"Mia\\u0027s Pizzeria\",\"dinerAddress\":\"台北市中山區南京西路12巷13弄9號\",\"dinerType\":\"D\",\"dinerOrderThreshold\":150,\"dinerStatus\":\"Submitted\",\"buildingName\":\"AA大樓\",\"buildingAddress\":\"台北市松山區1號\",\"userNickName\":\"nickname2\",\"dinerRating\":5.0},{\"groupOrderID\":3,\"orderStatus\":\"1\",\"groupOrderCreateTime\":\"2023-10-08 10:15:00\",\"groupOrderSubmitTime\":\"2023-10-08 12:00:00\",\"dinerName\":\"Tony\\u0027s Sushi\",\"dinerAddress\":\"台北市大同區南京西路18巷6弄8之1號\",\"dinerType\":\"X\",\"dinerOrderThreshold\":200,\"dinerStatus\":\"Submitted\",\"buildingName\":\"ee大樓\",\"buildingAddress\":\"台北市松山區5號\",\"userNickName\":\"nickname1\",\"dinerRating\":3.0}]";
            	// Send a JSON response 
            	res.getWriter().write(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set 400 Bad Request status on error
        }
    }
    
    private void getOneGroupOrder(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	int groupOrderID = Integer.valueOf(req.getParameter("ID"));
    	// Call service to get the entity and store it in request attribute
    	Map<String, Object> groupOrderData = groupOrderServiceImpl.getOneJoinGroupOrder(groupOrderID);
    	req.setAttribute("groupOrderData", groupOrderData);
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher("consumer/oneGroupOrder.jsp");
		dispatcher.forward(req, res);
   
    }
}
