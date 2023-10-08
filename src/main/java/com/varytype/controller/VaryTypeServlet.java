package com.varytype.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.varytype.dao.VaryTypeService;

public class VaryTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");



			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/


			if ("insert".equals(action)) { // 來自addEmp.jsp的請求

				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
				req.setAttribute("errorMsgs", errorMsgs);

				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String varyType = req.getParameter("varyType");

			

				/*************************** 2.開始新增資料 ***************************************/
				VaryTypeService varytypeSvc = new VaryTypeService();
				varytypeSvc.addVaryType(varyType);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

			}

		}

	}
}
