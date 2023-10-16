package com.dinerinfo.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.dinerinfo.dao.DinerInfoDAOHibernateImpl;
import com.dinerinfo.entity.DinerInfo;


public class DinerInfoServletD extends HttpServlet {
	
	private DinerInfoDAOHibernateImpl dinerDAOImpl;

	@Override
	public void init(ServletConfig config) throws ServletException {
		dinerDAOImpl = new DinerInfoDAOHibernateImpl();    // 實做一個方法物件
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//		=========================== 註冊 =============================

		if ("insert".equals(action)) { // 如果接收到的是insert，代表來自 register-form.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String dinerName = req.getParameter("dinerName");
			String dinerNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,15}$";
			if (dinerName == null || dinerName.trim().length() == 0) {
				errorMsgs.add("商家名稱 : 請勿空白");
			} else if (!dinerName.trim().matches(dinerNameReg)) {
				errorMsgs.add("商家名稱 : 只能是中、英文字母、數字, 且長度必需在1到15之間");
			}

//					密碼預設註冊時為EMAIL通知 , 這裡就不寫
//					String dinerPassword = req.getParameter("dinerPassword");
//					String dinerPasswordReg = "^[(a-zA-Z0-9_)]{6,10}$";
//					if (dinerPassword == null || dinerPassword.trim().length() == 0) {
//						errorMsgs.add("密碼 : 請勿空白");
//					} else if(!dinerPassword.trim().matches(dinerPassword)) { 
//						errorMsgs.add("密碼 : 只能是英文字母、數字和_ , 且長度必需在6到10之間");
//		            }

//					調用寫好的密碼產生器，產生一組預設的密碼
			String temporaryPassword = DinerPasswordGenerator.generateTemporaryPassword(6);

			String dinerTaxID = req.getParameter("dinerTaxID");
			String dinerTaxIDReg = "^[(0-9)]{8}$";
			if (dinerTaxID == null || dinerTaxID.trim().length() == 0) {
				errorMsgs.add("商家統編 : 請勿空白");
			} else if (!dinerTaxID.trim().matches(dinerTaxIDReg)) {
				errorMsgs.add("商家統編 : 只能是8個數字");
			}

			String dinerContact = req.getParameter("dinerContact");
			String chineseNameReg = "^[\u4e00-\u9fa5]+$"; // 只能是中文，不含空格
			String englishNameReg = "^[a-zA-Z]+(\\s[a-zA-Z]+)?$"; // 只能是英文，允許一個空格

			if (dinerContact == null || dinerContact.trim().length() == 0) {
				errorMsgs.add("聯絡人姓名 : 請勿空白");
			} else {
				if (dinerContact.matches(chineseNameReg)) {
					// 中文名稱，不含空格
				} else if (dinerContact.matches(englishNameReg)) {
					// 英文名稱，允許一個空格
				} else {
					errorMsgs.add("聯絡人姓名 : 中文名稱不可有空格，英文名稱可有一個空格分隔姓與名");
				}
			}

			String dinerPhone = req.getParameter("dinerPhone");
			String dinerPhoneReg = "^09[0-9]{8}$";
			if (dinerPhone == null || dinerPhone.trim().length() == 0) {
				errorMsgs.add("商家手機號碼 : 請勿空白");
			} else if (!dinerPhone.trim().matches(dinerPhoneReg)) {
				errorMsgs.add("商家手機號碼 : 必須是09開頭的10個數字");
			}

			String dinerEmail = req.getParameter("dinerEmail");
			String dinerEmailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
			if (dinerEmail == null || dinerEmail.trim().length() == 0) {
				errorMsgs.add("商家Email : 請勿空白");
			} else if (!dinerEmail.trim().matches(dinerEmailReg)) {
				errorMsgs.add("商家Email : 輸入的不是有效的Email地址 ");
			}

			String dinerAddress = req.getParameter("dinerAddress");
			if (dinerAddress == null || dinerAddress.trim().length() == 0) {
				errorMsgs.add("商家地址 : 請勿空白");
			} else {
				// 判定是否為台北市或新北市
				if (!dinerAddress.trim().matches(".*(台北市|新北市|臺北市).*")) {
					errorMsgs.add("商家地址 : 目前僅開放台北市和新北市");
				}
				// 判定地址長度是否在6個字以上
				if (dinerAddress.trim().length() < 6) {
					errorMsgs.add("商家地址 : 長度必須在6個字以上");
				}
			}

			String dinerBank = req.getParameter("dinerBank");
			String dinerBankReg = "^[(0-9)]{3}$";
			if (dinerBank == null || dinerBank.trim().length() == 0) {
				errorMsgs.add("商家銀行代號 : 請勿空白");
			} else if (!dinerBank.trim().matches(dinerBankReg)) {
				errorMsgs.add("商家銀行代號 : 僅能為3碼的數字");
			}
			// 這裡可以做 redius，目前先這樣

			String dinerAccount = req.getParameter("dinerAccount");
			String dinerAccountReg = "^[(0-9)]{10,16}$";
			if (dinerAccount == null || dinerAccount.trim().length() == 0) {
				errorMsgs.add("商家銀行帳號 : 請勿空白");
			} else if (!dinerAccount.trim().matches(dinerAccountReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("商家銀行帳號 : 僅能為10~16碼的數字");
			}

			String dinerAccountName = req.getParameter("dinerAccountName");
			String dinerAccountNameReg = "^[(\u4e00-\u9fa5)]{1,25}$";
			if (dinerAccountName == null || dinerAccountName.trim().length() == 0) {
				errorMsgs.add("商家銀行戶名 : 請勿空白");
			} else if (!dinerAccountName.trim().matches(dinerAccountNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("商家銀行戶名 : 僅能使用中文");
			}

			String dinerType = req.getParameter("dinerType");
			if (dinerType == null || dinerType.trim().length() == 0) {
				errorMsgs.add("商家種類 : 請勿空白");
			} else if (!"M".equals(dinerType.trim()) && !"D".equals(dinerType.trim())
					&& !"X".equals(dinerType.trim())) {
				errorMsgs.add("商家種類 : 選項不合法");
			}
			// 雖然前端選項寫死，還是稍微做個判定，增加安全性

			DinerInfo dinerInfo = new DinerInfo();

			// Timestamp的當前時間設置
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());

			dinerInfo.setDinerName(dinerName);
			dinerInfo.setDinerPassword(temporaryPassword);
			dinerInfo.setDinerRegisterTime(currentTime);
			dinerInfo.setDinerTaxID(dinerTaxID);
			dinerInfo.setDinerContact(dinerContact);
			dinerInfo.setDinerPhone(dinerPhone);
			dinerInfo.setDinerEmail(dinerEmail);
			dinerInfo.setDinerAddress(dinerAddress);
			dinerInfo.setDinerBank(dinerBank);
			dinerInfo.setDinerAccount(dinerAccount);
			dinerInfo.setDinerAccountName(dinerAccountName);
			dinerInfo.setDinerType(dinerType);
			dinerInfo.setDinerStatus("Submitted");

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("dinerInfo", dinerInfo); // 含有輸入格式錯誤的 dinerInfo 物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/register/register-form.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/


//			DinerInfoDAOHibernateImpl dinerInfoSvc = new DinerInfoDAOHibernateImpl();
			dinerDAOImpl.add(dinerInfo);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/dinerbackground/pages/Team/register/registerSuccess.html";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交 registerSuccess.html
			successView.forward(req, res);
		}
		
		

//			=========================== 登入 =============================	

		if ("dinerLogin".equals(action)) { // 來自login-form.jsp的請求 ， 相當於整個表單送出後的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			// Send the use back to the form, if there were errors
			String dinerTaxID = req.getParameter("dinerTaxID");
			String dinerTaxIDReg = "^[(0-9)]{8}$";
			if (dinerTaxID == null || dinerTaxID.trim().length() == 0) {
				errorMsgs.add("商家帳號 : 請勿空白");
			} else if (!dinerTaxID.trim().matches(dinerTaxIDReg)) {
				errorMsgs.add("商家帳號 : 只能是8個數字");
			}
			
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/login/login-form.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			String dinerPassword = req.getParameter("dinerPassword"); // 用一個字串容器儲存diner密碼
			
			
			DinerInfo dinerInfoBlg = new DinerInfo(); // 創建一個新的 dinerInfo 容器儲存使用者輸入的帳密
			dinerInfoBlg.setDinerTaxID(dinerTaxID);
			dinerInfoBlg.setDinerPassword(dinerPassword);
			
			
			DinerInfo dinerInfoAlg = dinerDAOImpl.findByTaxID(dinerTaxID);  // 創建一個新的 dinerInfo 容器引入使用者在資料庫裡的舊密碼

			if (dinerInfoAlg == null) {
				errorMsgs.add("查無該帳號");
			} else if (!dinerPassword.equals(dinerInfoAlg.getDinerPassword())) {
				errorMsgs.add("密碼錯誤");
			}
			
			//密碼存儲和驗證可以用雜湊函式BCrypt來提升安全性，有空的話再做

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("dinerInfo", dinerInfoBlg); // 含有輸入格式錯誤的dinerInfo物件,也存入req
															 // 這樣重新登錄的時候，填過的資料就不會消失
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/login/login-form.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始載入資料 *****************************************/

		
			HttpSession session = req.getSession(); // 用一個會話來儲存現在已經登入成功的物件
			session.setAttribute("account", dinerInfoAlg);  // 將 account 標記為資料庫的 dinerInfo 相對物件，當filter在過濾時就會知道這是已經登錄的帳號

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			String url = "/dinerbackground/pages/Team/dashboard/info-change.jsp"; // 登入完成後跳轉的頁面
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 info-change.jsp
			successView.forward(req, res);
		}
	}

}
