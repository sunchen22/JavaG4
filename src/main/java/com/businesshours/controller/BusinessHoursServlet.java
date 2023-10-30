package com.businesshours.controller;

import java.io.IOException;
import java.util.LinkedList;
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
public class BusinessHoursServlet  extends HttpServlet {
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

		if ("dayOfWeekStatus[Monday]".equals(action)) { // 如果接收到的是openStatusChange，代表來自 business-set.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			
			String openStatus = req.getParameter("openStatus");
			int dinerID = Integer.parseInt(req.getParameter("dinerID"));
			DinerInfo dinerInfo = new DinerInfo();
			dinerInfo.setDinerID(dinerID);
			String dayOfWeek = "Monday";
			
			BusinessHours bha = businessHoursServiceImpl.setOpenStatus(dinerID, dayOfWeek, openStatus);
			
			
			
			
//			String[] daysInEnglish = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
//			int dinerID = Integer.parseInt(req.getParameter("dinerID"));
//
//			String openStatus = null ;
//			String dayOfWeek = null ;
//			
//			List<BusinessHours> businessHoursList = new ArrayList<>();
//			
//			for (String dayInEnglish : daysInEnglish) {
//				openStatus = req.getParameter("dayOfWeekStatus[" + dayInEnglish + "]");
//				dayOfWeek = req.getParameter("dayOfWeek[" + dayInEnglish + "]");
//				
//				   if (openStatus != null && dayOfWeek != null) {
//				        BusinessHours bha = new BusinessHours();
//				        bha.setOpenStatus(openStatus);
//				        bha.setDayOfWeek(dayOfWeek);
//				        businessHoursList.add(bha);
//				    }
//	                  
//			}
//			BusinessHours bha = businessHoursServiceImpl.setOpenStatus(dinerID, dayOfWeek, openStatus);
////			DinerInfo dinerInfo = bha.getDinerInfo();
			
//			String dayOfWeek = req.getParameter("Monday");
//			int dinerID = Integer.parseInt(req.getParameter("dinerID"));
//			String openStatus = req.getParameter("dayOfWeekStatus");
//			
//			        BusinessHours bha = new BusinessHours();
//			        bha.setOpenStatus(openStatus);
//			        bha.setDayOfWeek(dayOfWeek);
			        
		
			
			/*************************** 2.開始載入資料 *****************************************/

			HttpSession session = req.getSession(); // 用一個會話來儲存現在已經登入成功的物件
			session.setAttribute("dinerInfo", dinerInfo); 
//			session.setAttribute("account", dinerInfo); // 將 account 標記為資料庫的 dinerInfo 相對物件，當filter在過濾時就會知道這是已經登錄的帳號

			
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/dinerbackground/pages/Team/dashboard/business-set.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交 business-set.jsp
			successView.forward(req, res);
			
		}
		
	}
	
}