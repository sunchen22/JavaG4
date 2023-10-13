package com.dinerinfo.controller;

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

import com.dinerinfo.entity.DinerInfo;
import com.dinerinfo.service.DinerInfoService;
import com.dinerinfo.service.DinerInfoServiceImpl;



@WebServlet("/diner.do")
public class DinerloginServlet extends HttpServlet  {
	
	// 一個 servlet 實體對應一個 service 實體
	private DinerInfoService dinerInfoService;
	
	@Override
	public void init() throws ServletException {
		dinerInfoService = new DinerInfoServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

	//====================================登入開始============================================	
		
		if (action.equals("dinerLogin")) { // 來自login-form.jsp的請求 ， 相當於整個表單送出後的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String dinerTaxID = req.getParameter("dinerTaxID");  //對應input的name
				if (dinerTaxID == null || (dinerTaxID.trim()).length() == 0) {
					errorMsgs.add("商家帳號請勿空白");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/dinerbackground/pages/Team/login/login-form.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String dinerPW = req.getParameter("dinerPW");  //用一個字串容器儲存diner密碼
				DinerInfo dinerInfo_before_login = new DinerInfo();   //創建一個新的容器儲存使用者輸入的密碼
				dinerInfo_before_login.setDinerTaxID(dinerTaxID);
				dinerInfo_before_login.setDinerPassword(dinerPW);
				DinerInfo dinerInfo_oldinfo = dinerInfoService.getDinerInfoByDinerTaxID(dinerTaxID);  //創建一個新的容器引入使用者在資料庫裡的舊密碼
				
				if(!dinerPW.equals(dinerInfo_oldinfo.getDinerPassword())) {
					errorMsgs.add("密碼錯誤");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("dinerInfo", dinerInfo_before_login); // 含有輸入格式錯誤的dinerInfo物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/dinerbackground/pages/Team/login/login-form.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				
				/***************************2.開始載入資料*****************************************/
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//緩存載入
				
				HttpSession session = req.getSession();   //用一個會話來儲存現在已經登入成功的物件
				session.setAttribute("loginUserInfo", dinerInfo_oldinfo);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				String url = "/dinerbackground/pages/Team/dashboard/info-change.jsp";   //登入完成後跳轉的頁面
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		//====================================登入結束============================================	



	
	}
}
