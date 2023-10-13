package com.product.model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProductServlet extends HttpServlet {
	

		public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			doPost(req, res);
		}

		public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");

			if ("getAll".equals(action)) {
				/***************************開始查詢資料 ****************************************/
				ProductJNDIDAO dao = new ProductJNDIDAO();
				List<ProductVO> list = dao.getAll();

				/***************************查詢完成,準備轉交(Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("list", list);    // 資料庫取出的list物件,存入session
				// Send the Success view
				String url = "/p_test/p_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listAllEmp2_getFromSession.jsp
				successView.forward(req, res);
				return;
			}


			if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String str = req.getParameter("product");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入員工編號");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/p_test/p_list.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					Integer productID = null;
					try {
						productID = Integer.valueOf(str);
					} catch (Exception e) {
						errorMsgs.add("員工編號格式不正確");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/p_test/p_list.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************2.開始查詢資料*****************************************/
					ProductJNDIDAO dao = new ProductJNDIDAO();
					ProductVO productVO = dao.findByPrimaryKey(productID);
					if (productVO == null) {
						errorMsgs.add("查無資料");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/p_test/p_list.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
					String url = "/p_test/p_list.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);
			}
		}
	}


