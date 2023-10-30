package com.userorderdetail.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.userinfo.entity.UserInfo;
import com.userorderdetail.dao.UserOrderDetailDAO_Tz;


public class UserOrderDetailServlet_Tz extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("loginUserInfo");
		Integer userId = userInfo.getUserID();
		UserOrderDetailDAO_Tz dao = new UserOrderDetailDAO_Tz();
		List<Integer> groupOrdersID = dao.getUserGroupOrdersID(userId);
		Map<Integer, List<Object[]>> allDetails = new HashMap<>();
		
		for(Integer groupId : groupOrdersID) {
            List<Object[]> details = dao.getUserOrderDetails(userId, groupId);
            allDetails.put(groupId, details);
        }
		
		req.setAttribute("allDetails", allDetails);
        RequestDispatcher successView = req.getRequestDispatcher("/consumer/protected/ConsumerOrder.jsp");
        successView.forward(req, res);

	}

}
