package com.grouporder.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.grouporder.entity.GroupOrderVO;
import com.grouporder.service.GroupOrderServiceN;

public class GroupOrderServletN extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		

		if ("go_by_status".equals(action)) {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer orderStatus = Integer.valueOf(req.getParameter("orderStatus").trim());

			/*************************** 2.開始查詢資料 *****************************************/

			GroupOrderServiceN goSvc = new GroupOrderServiceN();
			goSvc.getAll(orderStatus);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			String url = "/dinerbackground/pages/Team/ord_query/ord_query.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		

		if ("status".equals(action)) { 

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer groupOrderID = Integer.valueOf(req.getParameter("groupOrderID").trim());

			Integer orderStatus = Integer.valueOf(req.getParameter("orderStatus").trim());

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/ord_query/ord_query.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			GroupOrderServiceN goSvc = new GroupOrderServiceN();
			GroupOrderVO grouporder = goSvc.gostatus(groupOrderID, orderStatus);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("grouporder", grouporder); // 資料庫update成功後,正確的的VO物件,存入req
			String url = "/dinerbackground/pages/Team/ord_query/ord_query.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交p_list.jsp
			successView.forward(req, res);
		}

		
		if ("getOne_For_Display".equals(action)) { 

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("groupOrderID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("groupOrderID","請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/dinerbackground/pages/Team/ord_query/ord_query.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer groupOrderID = null;
				try {
					groupOrderID = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("groupOrderID","訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/dinerbackground/pages/Team/ord_query/ord_query.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
System.out.println("1");		
				/***************************2.開始查詢資料*****************************************/
				GroupOrderServiceN goSvc = new GroupOrderServiceN();
				GroupOrderVO grouporder = goSvc.getOneGO(groupOrderID);
				if (grouporder == null) {
					errorMsgs.put("groupOrderID","*查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/dinerbackground/pages/Team/ord_query/ord_query.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
System.out.println("2");		
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("grouporder", grouporder); // 資料庫取出的empVO物件,存入req
				String url = "/dinerbackground/pages/Team/ord_query/ord_query.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 
				successView.forward(req, res);
System.out.println("3");		
				
		}
	}
	
	
	
}
