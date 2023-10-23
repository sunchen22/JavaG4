package com.varytype.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.varytype.service.VaryTypeService;

public class VaryTypeServlet extends HttpServlet {

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
			String varyType = req.getParameter("varyType");
			if (varyType == null || (varyType.trim()).length() == 0) {
				errorMsgs.put("varyType", "*請勿空白");

			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/shelve/type_setting.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			VaryTypeService varytypeSvc = new VaryTypeService();

			varytypeSvc.addVaryType(varyType);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/dinerbackground/pages/Team/shelve/type_setting.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer varyTypeID = Integer.valueOf(req.getParameter("varyTypeID"));

			/*************************** 2.開始刪除資料 ***************************************/
			VaryTypeService VTSvc = new VaryTypeService();
			VTSvc.deleteVaryType(varyTypeID);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/dinerbackground/pages/Team/shelve/type_setting.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

	}

}
