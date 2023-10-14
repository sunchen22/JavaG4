package com.grouporder.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinerinfo.dao.DinerInfoDAOImplC;
import com.dinerinfo.entity.DinerInfo;
import com.grouporder.dao.GroupOrderDAOHibernateImpl;
import com.grouporder.dao.GroupOrderDAOHibernateImplC;
import com.grouporder.entity.GroupOrder;

@WebServlet("/cproject/pages/gos.do")
public class GroupOrderServletC extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
		String action = req.getParameter("action");

		if ("go_one_data".equals(action)) {

			String ordnum = req.getParameter("ordnum");
			String ordname = req.getParameter("ordname");
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			if ((ordnum.equals("") || (ordnum.trim()).length() == 0)
					&& (ordname.equals("") || (ordname.trim()).length() == 0)) {

				errorMsgs.add("請輸入資料");

				RequestDispatcher failureView = req.getRequestDispatcher("/cproject/pages/ord_query.jsp");

				failureView.forward(req, res);

				return;
			}
			if (!(ordnum.equals("") || (ordnum.trim()).length() == 0)
					|| !(ordname.equals("") || (ordname.trim()).length() == 0)) {

				Integer input = null;
				String input1;
				try {
					input = Integer.valueOf(ordnum);
					input1 = ordname;
				} catch (Exception e) {
					errorMsgs.add("格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/cproject/pages/ord_query.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				
				GroupOrderDAOHibernateImplC godhi = new GroupOrderDAOHibernateImplC();
				GroupOrder gor = godhi.findByPK(input);
				
				DinerInfoDAOImplC didic = new DinerInfoDAOImplC();
				DinerInfo dif = didic.findByPK(input);
												

				if (gor == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/cproject/pages/ord_query.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				req.setAttribute("gor", gor); // 
				req.setAttribute("dif", dif); // 
				String url = "/cproject/pages/ord_queryOne_data.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				return;
			}

		}

			

		

		if ("go_for_data".equals(action)) {
			
			String str = req.getParameter("dinerID");
			
			Integer dinerID = Integer.valueOf(str);
			
			DinerInfoDAOImplC didic = new DinerInfoDAOImplC();
			DinerInfo dif = didic.findByPK(dinerID);
			req.setAttribute("dif", dif);
			
//			
			
			
//			GroupOrderDAOHibernateImpl godhi = new GroupOrderDAOHibernateImpl();
//			GroupOrder gor = godhi.findByPK(dinerID);
//			req.setAttribute("gor", gor);

			
			
			
			String url = "/cproject/pages/order_query_data.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}

	}
}
