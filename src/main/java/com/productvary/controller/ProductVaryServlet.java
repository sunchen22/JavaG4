package com.productvary.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.product.entity.Product;
import com.product.service.ProductService;
import com.productvary.entity.ProductVary;
import com.productvary.service.ProductVaryService;
import com.varytype.service.VaryTypeService;

public class ProductVaryServlet extends HttpServlet {

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
			Integer productID = Integer.valueOf(req.getParameter("productID").trim());
			String productVaryDes = req.getParameter("productVaryDes");
			if (productVaryDes == null || (productVaryDes.trim()).length() == 0) {
				errorMsgs.put("productVaryDes", "*請輸入客製內容");
			}
			// Send the use back to the form, if there were errors

			Integer productVaryPrice = null;
			try {
				productVaryPrice = Integer.valueOf(req.getParameter("productVaryPrice").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("productVaryPrice", "*請輸入客製價格");
			}
			
			Integer varyTypeID = Integer.valueOf(req.getParameter("varyTypeID").trim());
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/shelve/shelve_PV.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			ProductVaryService PVSvc = new ProductVaryService();

			PVSvc.addProductVary(productID, productVaryDes, productVaryPrice, varyTypeID);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/dinerbackground/pages/Team/shelve/shelve_PV.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

		}
		if ("insert2".equals(action)) { // 來自type_setting.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer productID = Integer.valueOf(req.getParameter("productID").trim());
			String productVaryDes = req.getParameter("productVaryDes");
			if (productVaryDes == null || (productVaryDes.trim()).length() == 0) {
				errorMsgs.put("productVaryDes", "*請輸入客製內容");
			}
			// Send the use back to the form, if there were errors

			Integer productVaryPrice = null;
			try {
				productVaryPrice = Integer.valueOf(req.getParameter("productVaryPrice").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("productVaryPrice", "*請輸入客製價格");
			}
			
			Integer varyTypeID = Integer.valueOf(req.getParameter("varyTypeID").trim());
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/shelve/update_shelve_PV.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			ProductVaryService PVSvc = new ProductVaryService();

			PVSvc.addProductVary(productID, productVaryDes, productVaryPrice, varyTypeID);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/dinerbackground/pages/Team/shelve/update_shelve_PV.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

		}
		
		if ("getOne_For_Update".equals(action)) { // 來自p_list.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer productVaryID = Integer.valueOf(req.getParameter("productVaryID"));
		

			/*************************** 2.開始查詢資料 ****************************************/
			ProductVaryService PVSvc = new ProductVaryService();
			ProductVary productVary = PVSvc.getOneProductVary(productVaryID);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param =  
							"?&productVaryID=" + productVary.getProductVaryID() +
							"&productID=" + productVary.getProductID() +
							"&productVaryDes=" + productVary.getProductVaryDes() +
							"&productVaryPrice=" + productVary.getProductVaryPrice() +
							"&varyTypeID=" + productVary.getVaryTypeID();
			
			System.out.println(productVaryID);

				
			String url = "/dinerbackground/pages/Team/shelve/update_shelve_PV2.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_product.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer productVaryID = Integer.valueOf(req.getParameter("productVaryID").trim());

			System.out.println(productVaryID);

			
			Integer productID = Integer.valueOf(req.getParameter("productID").trim());
			String productVaryDes = req.getParameter("productVaryDes");
			Integer productVaryPrice = Integer.valueOf(req.getParameter("productVaryPrice").trim());
			Integer varyTypeID = Integer.valueOf(req.getParameter("varyTypeID").trim());

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/shelve/update_shelve_PV2.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ProductVaryService PVSvc = new ProductVaryService();
			ProductVary productVary = PVSvc.updateProductVary(productVaryID, productID, productVaryDes,
					productVaryPrice, varyTypeID);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			
			req.setAttribute("productVary", productVary); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/dinerbackground/pages/Team/shelve/update_shelve_PV.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		
		

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer productVaryID = Integer.valueOf(req.getParameter("productVaryID"));

			/*************************** 2.開始刪除資料 ***************************************/
			ProductVaryService VTSvc = new ProductVaryService();
			VTSvc.deleteProductVary(productVaryID);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/dinerbackground/pages/Team/shelve/type_setting.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
		
		if ("getbyid".equals(action)) { 

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer productID = Integer.valueOf(req.getParameter("productID"));
				
System.out.println(productID);


				/***************************2.開始查詢資料***************************************/
				ProductVaryService pvSvc = new ProductVaryService();
				pvSvc.getByPID(productID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/dinerbackground/pages/Team/shelve/type_setting.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
		
		if ("getbyType".equals(action)) { // 來自listAllEmp.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer priductID = Integer.valueOf(req.getParameter("priductID"));
				
System.out.println(priductID);


				/***************************2.開始查詢資料***************************************/
				ProductVaryService pvSvc = new ProductVaryService();
				pvSvc.getByPID(priductID);
				
				/***************************3.準備轉交(Send the Success view)***********/		
				req.setAttribute("priductID", priductID);
				String url = "/dinerbackground/pages/Team/shelve/shelvePV2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
		
	}

}
