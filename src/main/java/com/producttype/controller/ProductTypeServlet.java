package com.producttype.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.producttype.service.ProductTypeService;

public class ProductTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
	  

		String action = req.getParameter("action");

		if ("insert".equals(action)) { // 來自type_setting.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String productTypeDes = req.getParameter("productTypeDes");
			if (productTypeDes == null || (productTypeDes.trim()).length() == 0) {
				errorMsgs.put("productTypeDes", "*請輸入商品類型");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/shelve/shelve_PT.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			ProductTypeService productService = new ProductTypeService();

			productService.addProductType(productTypeDes);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/dinerbackground/pages/Team/shelve/shelve_PT.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer productTypeID = Integer.valueOf(req.getParameter("productTypeID"));

			/*************************** 2.開始刪除資料 ***************************************/
			ProductTypeService PTSvc = new ProductTypeService();
			PTSvc.deleteProductType(productTypeID);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/dinerbackground/pages/Team/shelve/shelve_PT.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

	}

}
