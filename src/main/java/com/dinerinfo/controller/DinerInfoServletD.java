package com.dinerinfo.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dinerinfo.entity.DinerInfo;
import com.dinerinfo.service.DinerInfoServiceImpl;
import com.google.gson.Gson;

public class DinerInfoServletD extends HttpServlet {

	private DinerInfoServiceImpl dinerInfoServiceImpl;

	@Override
	public void init() throws ServletException {
		dinerInfoServiceImpl = new DinerInfoServiceImpl(); // 實做一個方法物件
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//		=========================== 註冊 ======================================================	

		if ("insert".equals(action)) { // 如果接收到的是insert，代表來自 register-form.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			
			DinerInfo dinerInfoRg = new DinerInfo(); // 創建一個 dinerInfo 對象，來儲存註冊者輸入的資料
			
			String dinerName = req.getParameter("dinerName");
			String dinerNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,15}$";
			if (dinerName == null || dinerName.trim().length() == 0) {
				errorMsgs.add("商家名稱 : 請勿空白");
			} else if (!dinerName.trim().matches(dinerNameReg)) {
				errorMsgs.add("商家名稱 : 只能是中、英文字母、數字, 且長度必需在1到15之間");
			}
			dinerInfoRg.setDinerName(dinerName); // 存下註冊者輸入的資訊
			
//					密碼預設註冊時為EMAIL通知 , 這裡就不寫
//					String dinerPassword = req.getParameter("dinerPassword");
//					String dinerPasswordReg = "^[(a-zA-Z0-9_)]{6,10}$";
//					if (dinerPassword == null || dinerPassword.trim().length() == 0) {
//						errorMsgs.add("密碼 : 請勿空白");
//					} else if(!dinerPassword.trim().matches(dinerPassword)) { 
//						errorMsgs.add("密碼 : 只能是英文字母、數字和_ , 且長度必需在6到10之間");
//		            }

//					調用寫好的密碼產生器，產生一組預設的密碼
//			String temporaryPassword = DinerPasswordGenerator.generateTemporaryPassword(6);
//			dinerInfoRg.setDinerPassword(temporaryPassword); // 存下密碼產生器產生的密碼
			
			String dinerTaxID = req.getParameter("dinerTaxID");
			String dinerTaxIDReg = "^[(0-9)]{8}$";

			if (dinerTaxID == null || dinerTaxID.trim().length() == 0) {
				errorMsgs.add("商家統編 : 請勿空白");
			} else if (!dinerTaxID.trim().matches(dinerTaxIDReg)) {
				errorMsgs.add("商家統編 : 只能是8個數字");
			} else {
				// 比對資料庫第一次 : 記錄錯誤選項
				DinerInfo oldDiner = dinerInfoServiceImpl.getDinerInfoByDinerTaxID(dinerTaxID);
				if (oldDiner != null) {
					errorMsgs.add("商家統編 : 此統編已被註冊");
				}
			}
			dinerInfoRg.setDinerTaxID(dinerTaxID); // 存下註冊者輸入的資訊

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
			dinerInfoRg.setDinerContact(dinerContact); // 存下註冊者輸入的資訊

			String dinerPhone = req.getParameter("dinerPhone");
			String dinerPhoneReg = "^09[0-9]{8}$";
			if (dinerPhone == null || dinerPhone.trim().length() == 0) {
				errorMsgs.add("商家手機號碼 : 請勿空白");
			} else if (!dinerPhone.trim().matches(dinerPhoneReg)) {
				errorMsgs.add("商家手機號碼 : 必須是09開頭的10個數字");
			} else {
				// 比對資料庫第一次 : 記錄錯誤選項
				DinerInfo oldDiner = dinerInfoServiceImpl.getDinerInfoByDinerPhone(dinerPhone);
				if (oldDiner != null) {
					errorMsgs.add("商家手機號碼 : 此手機號碼已被註冊");
				}
			}
			dinerInfoRg.setDinerPhone(dinerPhone); // 存下註冊者輸入的資訊

			String dinerEmail = req.getParameter("dinerEmail");
			String dinerEmailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
			if (dinerEmail == null || dinerEmail.trim().length() == 0) {
				errorMsgs.add("商家Email : 請勿空白");
			} else if (!dinerEmail.trim().matches(dinerEmailReg)) {
				errorMsgs.add("商家Email : 輸入的不是有效的Email地址 ");
			} else if (dinerInfoServiceImpl.getDinerInfoByDinerEmail(dinerEmail) != null) {
				errorMsgs.add("商家Email  : 此Email已被註冊");
			} else {
				// 比對資料庫第一次 : 記錄錯誤選項
				DinerInfo oldDiner = dinerInfoServiceImpl.getDinerInfoByDinerEmail(dinerEmail);
				if (oldDiner != null) {
					errorMsgs.add("商家Email : 此Email已被註冊");
				}
			}
			dinerInfoRg.setDinerEmail(dinerEmail); // 存下註冊者輸入的資訊

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
			dinerInfoRg.setDinerAddress(dinerAddress); // 存下註冊者輸入的資訊

			String dinerBank = req.getParameter("dinerBank");
			String dinerBankReg = "^[(0-9)]{3}$";
			if (dinerBank == null || dinerBank.trim().length() == 0) {
				errorMsgs.add("商家銀行代號 : 請勿空白");
			} else if (!dinerBank.trim().matches(dinerBankReg)) {
				errorMsgs.add("商家銀行代號 : 僅能為3碼的數字");
			}
			// 這裡可以做 redius，目前先這樣
			dinerInfoRg.setDinerBank(dinerBank); // 存下註冊者輸入的資訊

			String dinerAccount = req.getParameter("dinerAccount");
			String dinerAccountReg = "^[(0-9)]{10,16}$";
			if (dinerAccount == null || dinerAccount.trim().length() == 0) {
				errorMsgs.add("商家銀行帳號 : 請勿空白");
			} else if (!dinerAccount.trim().matches(dinerAccountReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("商家銀行帳號 : 僅能為10~16碼的數字");
			}
			dinerInfoRg.setDinerAccount(dinerAccount);; // 存下註冊者輸入的資訊

			String dinerAccountName = req.getParameter("dinerAccountName");
			String dinerAccountNameReg = "^[(\u4e00-\u9fa5)]{1,25}$";
			if (dinerAccountName == null || dinerAccountName.trim().length() == 0) {
				errorMsgs.add("商家銀行戶名 : 請勿空白");
			} else if (!dinerAccountName.trim().matches(dinerAccountNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("商家銀行戶名 : 僅能使用中文");
			}
			dinerInfoRg.setDinerAccountName(dinerAccountName); // 存下註冊者輸入的資訊

			String dinerType = req.getParameter("dinerType");
			if (dinerType == null || dinerType.trim().length() == 0) {
				errorMsgs.add("商家種類 : 請勿空白");
			} else if (!"M".equals(dinerType.trim()) && !"D".equals(dinerType.trim())
					&& !"X".equals(dinerType.trim())) {
				errorMsgs.add("商家種類 : 選項不合法");
			}
			// 雖然前端選項寫死，還是稍微做個判定，增加安全性
			dinerInfoRg.setDinerType(dinerType); // 存下註冊者輸入的資訊

			
			// Timestamp的當前時間設置
//			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
//			dinerInfoRg.setDinerRegisterTime(currentTime);// 存下註冊者的註冊時間
//			
//			String dinerStatus = "Submitted";
			// 註冊如果失敗，就不需要存下註冊者的商家狀態
			// 這格應該是會資料庫自己新增，但是可以之後再試試看

//			dinerInfoRg = dinerInfoServiceImpl.registerCheckDinerInfo(dinerName, temporaryPassword, currentTime,
//					dinerTaxID, dinerContact, dinerPhone, dinerEmail, dinerAddress, dinerBank, dinerAccount,
//					dinerAccountName, dinerType, dinerStatus);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("dinerInfo", dinerInfoRg); // 含有輸入格式錯誤的 dinerInfo 物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/register/register-form.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			
			DinerInfo newDinerInfo = new DinerInfo();   // 創建一個容器，儲存與資料庫比對後，無重複的、可新增進資料庫的新註冊商家
			newDinerInfo = dinerInfoServiceImpl.registerCheckDinerInfo(dinerInfoRg);
			if (newDinerInfo != null) {
				dinerInfoServiceImpl.registerDinerInfo(newDinerInfo);
				HttpSession session = req.getSession();
				session.setAttribute("NewDinerInfo", newDinerInfo);
				req.setAttribute("isRegistration", true);
			} 
//			else {
//				req.setAttribute("dinerInfo", dinerInfoRg); // 含有輸入格式錯誤的 dinerInfo 物件,也存入req
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/dinerbackground/pages/Team/register/register-form.jsp");
//				failureView.forward(req, res);
//				return;
//			}

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/dinerbackground/pages/Team/register/registerSuccess.html";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交 registerSuccess.html
			successView.forward(req, res);
		}

//			=========================== 登入 ======================================================	

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

			DinerInfo dinerInfoAlg = dinerInfoServiceImpl.getDinerInfoByDinerTaxID(dinerTaxID); // 創建一個新的 dinerInfo
																								// 容器引入使用者在資料庫裡的舊密碼

			if (dinerInfoAlg == null) {
				errorMsgs.add("查無該帳號");
			} else if (!dinerPassword.equals(dinerInfoAlg.getDinerPassword())) {
				errorMsgs.add("密碼錯誤");
			}

			// 密碼存儲和驗證可以用雜湊函式BCrypt來提升安全性，有空的話再做

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
			session.setAttribute("account", dinerInfoAlg); // 將 account 標記為資料庫的 dinerInfo 相對物件，當filter在過濾時就會知道這是已經登錄的帳號

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			String url = "/dinerbackground/pages/Team/dashboard/info-change.jsp"; // 登入完成後跳轉的頁面
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 info-change.jsp
			successView.forward(req, res);
		}

//		=========================== 送出商家資料修改 ======================================================			

//		if ("dinerInfoChange".equals(action)) { // 如果接收到的是 dinerInfoChange，代表來自 info-change.jsp 的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//
//			String dinerName = req.getParameter("dinerName");
//			String dinerNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,15}$";
//			if (dinerName == null || dinerName.trim().length() == 0) {
//				errorMsgs.add("商家名稱 : 請勿空白");
//			} else if (!dinerName.trim().matches(dinerNameReg)) {
//				errorMsgs.add("商家名稱 : 只能是中、英文字母、數字, 且長度必需在1到15之間");
//			}
//
//			String dinerPassword = req.getParameter("dinerPassword");
//			String dinerPasswordReg = "^[(a-zA-Z0-9@#*+.)]{6,15}$";
//			if (dinerPassword == null || dinerPassword.trim().length() == 0) {
//				errorMsgs.add("密碼 : 請勿空白");
//			} else if (!dinerPassword.trim().matches(dinerPassword)) {
//				errorMsgs.add("密碼 : 僅能為英文大小寫、數字、和 @ # * + . 五個符號所組成 , 且長度必需在6到15之間");
//			}	
//			
//			String dinerTaxID = req.getParameter("dinerTaxID");
//			String dinerTaxIDReg = "^[(0-9)]{8}$";
//			if (dinerTaxID == null || dinerTaxID.trim().length() == 0) {
//				errorMsgs.add("商家統編 : 請勿空白");
//			} else if (!dinerTaxID.trim().matches(dinerTaxIDReg)) {
//				errorMsgs.add("商家統編 : 只能是8個數字");
//			}
//
//			String dinerContact = req.getParameter("dinerContact");
//			String chineseNameReg = "^[\u4e00-\u9fa5]+$"; // 只能是中文，不含空格
//			String englishNameReg = "^[a-zA-Z]+(\\s[a-zA-Z]+)?$"; // 只能是英文，允許一個空格
//
//			if (dinerContact == null || dinerContact.trim().length() == 0) {
//				errorMsgs.add("聯絡人姓名 : 請勿空白");
//			} else {
//				if (dinerContact.matches(chineseNameReg)) {
//					// 中文名稱，不含空格
//				} else if (dinerContact.matches(englishNameReg)) {
//					// 英文名稱，允許一個空格
//				} else {
//					errorMsgs.add("聯絡人姓名 : 中文名稱不可有空格，英文名稱可有一個空格分隔姓與名");
//				}
//			}
//
//			String dinerPhone = req.getParameter("dinerPhone");
//			String dinerPhoneReg = "^09[0-9]{8}$";
//			if (dinerPhone == null || dinerPhone.trim().length() == 0) {
//				errorMsgs.add("商家手機號碼 : 請勿空白");
//			} else if (!dinerPhone.trim().matches(dinerPhoneReg)) {
//				errorMsgs.add("商家手機號碼 : 必須是09開頭的10個數字");
//			}
//
//			String dinerEmail = req.getParameter("dinerEmail");
//			String dinerEmailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
//			if (dinerEmail == null || dinerEmail.trim().length() == 0) {
//				errorMsgs.add("商家Email : 請勿空白");
//			} else if (!dinerEmail.trim().matches(dinerEmailReg)) {
//				errorMsgs.add("商家Email : 輸入的不是有效的Email地址 ");
//			}
//
//			String dinerAddress = req.getParameter("dinerAddress");
//			if (dinerAddress == null || dinerAddress.trim().length() == 0) {
//				errorMsgs.add("商家地址 : 請勿空白");
//			} else {
//				// 判定是否為台北市或新北市
//				if (!dinerAddress.trim().matches(".*(台北市|新北市|臺北市).*")) {
//					errorMsgs.add("商家地址 : 目前僅開放台北市和新北市");
//				}
//				// 判定地址長度是否在6個字以上
//				if (dinerAddress.trim().length() < 6) {
//					errorMsgs.add("商家地址 : 長度必須在6個字以上");
//				}
//			}
//
//			String dinerBank = req.getParameter("dinerBank");
//			String dinerBankReg = "^[(0-9)]{3}$";
//			if (dinerBank == null || dinerBank.trim().length() == 0) {
//				errorMsgs.add("商家銀行代號 : 請勿空白");
//			} else if (!dinerBank.trim().matches(dinerBankReg)) {
//				errorMsgs.add("商家銀行代號 : 僅能為3碼的數字");
//			}
//			// 這裡可以做 redius，目前先這樣
//
//			String dinerAccount = req.getParameter("dinerAccount");
//			String dinerAccountReg = "^[(0-9)]{10,16}$";
//			if (dinerAccount == null || dinerAccount.trim().length() == 0) {
//				errorMsgs.add("商家銀行帳號 : 請勿空白");
//			} else if (!dinerAccount.trim().matches(dinerAccountReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("商家銀行帳號 : 僅能為10~16碼的數字");
//			}
//
//			String dinerAccountName = req.getParameter("dinerAccountName");
//			String dinerAccountNameReg = "^[(\u4e00-\u9fa5)]{1,25}$";
//			if (dinerAccountName == null || dinerAccountName.trim().length() == 0) {
//				errorMsgs.add("商家銀行戶名 : 請勿空白");
//			} else if (!dinerAccountName.trim().matches(dinerAccountNameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("商家銀行戶名 : 僅能使用中文");
//			}
//
//			String dinerType = req.getParameter("dinerType");
//			if (dinerType == null || dinerType.trim().length() == 0) {
//				errorMsgs.add("商家種類 : 請勿空白");
//			} else if (!"M".equals(dinerType.trim()) && !"D".equals(dinerType.trim())
//					&& !"X".equals(dinerType.trim())) {
//				errorMsgs.add("商家種類 : 選項不合法");
//			}
//			// 雖然前端選項寫死，還是稍微做個判定，增加安全性
//			
//			// 查詢使用者在資料庫的原始資料，並轉為 Json 格式
//			Integer dinerID = Integer.parseInt(req.getParameter("dinerID"));
//			DinerInfo oldInfo = dinerInfoServiceImpl.getDinerInfoByDinerID(dinerID);
//			// 創建一個 dinerInfo 對象，來儲存使用者輸入的資料
//			DinerInfo newInfo = new DinerInfo();
//			newInfo.setDinerName(dinerName);
//			newInfo.setDinerName(dinerPassword);
//			newInfo.setDinerName(dinerTaxID);
//			newInfo.setDinerName(dinerContact);
//			newInfo.setDinerName(dinerPhone);
//			newInfo.setDinerName(dinerEmail);
//			newInfo.setDinerName(dinerAddress);
//			newInfo.setDinerName(dinerBank);
//			newInfo.setDinerName(dinerAccount);
//			newInfo.setDinerName(dinerAccountName);
//			newInfo.setDinerName(dinerType);
//			
//			// 將原始資料、使用者填寫的欄位送進資料庫比對哪個欄位有做修改
//			// 用 dao 裡的方法回傳一個 JSON 格式，存入 dinerUpdate 欄位中
//			String difference = dinerInfoServiceImpl.compareDinerInfo(oldInfo, newInfo);
//			oldInfo.setDinerUpdate(difference);
//			oldInfo = dinerInfoServiceImpl.updateDinerInfo(oldInfo);
//			
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("dinerEdit", newInfo); // 含有輸入格式錯誤的 dinerInfo 物件,存入req
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/dinerbackground/pages/Team/dashboard/info-change.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/*************************** 2.開始新增資料 ***************************************/
//
//			
//			HttpSession session = req.getSession();
//			session.setAttribute("DinerInfoChange", difference);
//			req.setAttribute("isChange", true);
//			req.setAttribute("successMsg", "修改申請已送出，請靜待審核");
//
//			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			String url = "/dinerbackground/pages/Team/dashboard/info-change.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交 registerSuccess.html
//			successView.forward(req, res);
//		}

	}

}