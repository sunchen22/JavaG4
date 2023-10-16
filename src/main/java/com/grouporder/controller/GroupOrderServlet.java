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
    	req.setAttribute("menuData", groupOrderData.get("menuData"));
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher("consumer/oneGroupOrder.jsp");
		dispatcher.forward(req, res);
   
    }
}
