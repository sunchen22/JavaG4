package com.businesshours.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.businesshours.entity.BusinessHours;
import com.businesshours.service.BusinessHoursServiceImpl;
import com.dinerinfo.entity.DinerInfo;

@WebServlet("/businessHours.do")
public class BusinessHoursServlet extends HttpServlet {
	// One service instance for one servlet instance
	private BusinessHoursServiceImpl businessHoursServiceImpl;

	@Override
	public void init() throws ServletException {
		businessHoursServiceImpl = new BusinessHoursServiceImpl(); // 實做一個方法物件
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//		=========================== 改變營業狀態 ======================================================	

		if ("dayOfWeekStatus[Monday]".equals(action)) { // 如果接收到的是changeStatus，代表來自 business-set.jsp 的請求

		

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String inputDayOfWeek = "Monday";
			String inputOpenStatus = req.getParameter("openStatus");
			Integer inputDinerID = Integer.parseInt(req.getParameter("dinerID"));
			System.out.println("inputOpenStatus:"+inputOpenStatus);
			System.out.println("inputDinerID:"+inputDinerID);
			
			//拿到資料庫裡的dinerInfo
			//確認資料庫的dinerInfo有沒有dayOfWeek是Monday
			//如果有，重設該BusinessHours
			//如果沒有，新增一個BusinessHours(但是基本上不會發生，因為前端已經寫死)
			BusinessHours businessHours = businessHoursServiceImpl.setOpenStatus(inputDinerID, inputDayOfWeek, inputOpenStatus);
			System.out.println("businessHours :"+businessHours);
			
			List<BusinessHours> updatedBusinessHours = businessHoursServiceImpl.getBusinessHoursByDinerID(inputDinerID);
//			System.out.println("BH:"+BH);
			
			DinerInfo updatedDinerInfo  = businessHoursServiceImpl.getDinerInfoByDinerID(inputDinerID);
			System.out.println("dinerInfo:"+updatedDinerInfo);

			/*************************** 2.開始載入資料 *****************************************/

			HttpSession session = req.getSession();// 用一個會話來儲存現在已經登入成功的物件
			session.setAttribute("businessHours", updatedBusinessHours);
			session.setAttribute("account", updatedDinerInfo); // 將 account 標記為資料庫的 dinerInfo 相對物件，當filter在過濾時就會知道這是已經登錄的帳號
			session.setAttribute("dinerID", inputDinerID); // 將 account 標記為資料庫的 dinerInfo 相對物件，當filter在過濾時就會知道這是已經登錄的帳號

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/dinerbackground/pages/Team/dashboard/business-set.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交 business-set.jsp
			successView.forward(req, res);
//			res.sendRedirect(req.getContextPath() + url);

		}

	}

}