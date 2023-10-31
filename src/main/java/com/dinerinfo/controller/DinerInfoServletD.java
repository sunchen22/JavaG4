package com.dinerinfo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
			String dinerNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9')]+(\\s[(\u4e00-\u9fa5)(a-zA-Z0-9')]{1,15}+)?$";
			if (dinerName == null || dinerName.trim().length() == 0) {
				errorMsgs.add("商家名稱 : 請勿空白");
			} else if (!dinerName.trim().matches(dinerNameReg)) {
				errorMsgs.add("商家名稱 : 只能是中、英文字母、數字和'符號,中間可加一個空格，字串長度必需在1到15之間");
			}
			dinerInfoRg.setDinerName(dinerName); // 存下註冊者輸入的資訊

//					密碼預設註冊時為EMAIL通知 , 這裡就不寫

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
			dinerInfoRg.setDinerAccount(dinerAccount);
			; // 存下註冊者輸入的資訊

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

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("dinerInfo", dinerInfoRg); // 含有輸入格式錯誤的 dinerInfo 物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/register/register-form.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/

			DinerInfo newDinerInfo = new DinerInfo(); // 創建一個容器，儲存與資料庫比對後，無重複的、可新增進資料庫的新註冊商家
			newDinerInfo = dinerInfoServiceImpl.registerCheckDinerInfo(dinerInfoRg);
			if (newDinerInfo != null) {
				dinerInfoServiceImpl.registerDinerInfo(newDinerInfo);
				HttpSession session = req.getSession();
				session.setAttribute("NewDinerInfo", newDinerInfo);
				req.setAttribute("isRegistration", true);
			}

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/dinerbackground/pages/Team/register/registerSuccess.jsp";
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
			System.out.println(dinerInfoAlg.getDinerID() + "===========這是取得的dinerID===========");
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			String url = "/dinerbackground/pages/Team/dashboard/index.jsp"; // 登入完成後跳轉的頁面
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 info-change.jsp
			successView.forward(req, res);
		}

//		=========================== 送出商家資料修改 ======================================================			

		if ("dinerInfoChange".equals(action)) { // 如果接收到的是 dinerInfoChange，代表來自 info-change.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();

			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			DinerInfo editInfo = new DinerInfo(); // 創建一個 dinerInfo 對象，來儲存商家輸入的資料
			DinerInfo oldInfo = new DinerInfo(); // 創建一個 dinerInfo 對象，來儲存商家原始的資料
			DinerInfo newInfo = new DinerInfo(); // 創建一個 dinerInfo 對象，來儲存商家輸入，且經確認，格式無誤、與資料庫其他商家不重複的資料

			int dinerID = Integer.parseInt(req.getParameter("dinerID")); // 從隱藏欄位取得送出此請求的商家id
			oldInfo = dinerInfoServiceImpl.getDinerInfoByDinerID(dinerID); // 查詢出商家資料，放入商家原始的資料的容器

			String dinerName = req.getParameter("dinerName");
			String dinerNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9')]+(\\s[(\u4e00-\u9fa5)(a-zA-Z0-9')]{1,15}+)?$";
			if (dinerName == null || dinerName.trim().length() == 0) {
				errorMsgs.add("商家名稱 : 請勿空白");
			} else if (!dinerName.trim().matches(dinerNameReg)) {
				errorMsgs.add("商家名稱 : 只能是中、英文字母、數字和'符號,中間可加一個空格，字串長度必需在1到15之間");
			}
			editInfo.setDinerName(dinerName); // 存下註冊者輸入的資訊

			String dinerPassword = req.getParameter("dinerPassword");
			String dinerPasswordReg = "^[(a-zA-Z0-9_)]{6,10}$";
			if (dinerPassword == null || dinerPassword.trim().length() == 0) {
				errorMsgs.add("密碼 : 請勿空白");
			} else if (!dinerPassword.trim().matches(dinerPassword)) {
				errorMsgs.add("密碼 : 只能是英文字母、數字和_ , 且長度必需在6到10之間");
			}
			editInfo.setDinerPassword(dinerPassword); // 存下註冊者輸入的資訊

			String dinerTaxID = req.getParameter("dinerTaxID");
			String dinerTaxIDReg = "^[(0-9)]{8}$";
			if (dinerTaxID == null || dinerTaxID.trim().length() == 0) {
				errorMsgs.add("商家統編 : 請勿空白");
			} else if (!dinerTaxID.trim().matches(dinerTaxIDReg)) {
				errorMsgs.add("商家統編 : 只能是8個數字");
			} else {
				// 比對資料庫第一次 : 記錄錯誤選項
				String oldDinerTaxID = dinerInfoServiceImpl.checkDinerTaxID(dinerTaxID, dinerID);
				if (oldDinerTaxID != null) {
					errorMsgs.add("商家統編 : 此統編已被註冊");
				}
			}
			editInfo.setDinerTaxID(dinerTaxID); // 存下註冊者輸入的資訊

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
			editInfo.setDinerContact(dinerContact); // 存下註冊者輸入的資訊

			String dinerPhone = req.getParameter("dinerPhone");
			String dinerPhoneReg = "^09[0-9]{8}$";
			if (dinerPhone == null || dinerPhone.trim().length() == 0) {
				errorMsgs.add("商家手機號碼 : 請勿空白");
			} else if (!dinerPhone.trim().matches(dinerPhoneReg)) {
				errorMsgs.add("商家手機號碼 : 必須是09開頭的10個數字");
			} else {
				// 比對資料庫第一次 : 記錄錯誤選項
				String oldDinerPhone = dinerInfoServiceImpl.checkDinerPhone(dinerPhone, dinerID);
				if (oldDinerPhone != null) {
					errorMsgs.add("商家手機號碼 : 此手機號碼已被註冊");
				}
			}
			editInfo.setDinerPhone(dinerPhone); // 存下註冊者輸入的資訊

			String dinerEmail = req.getParameter("dinerEmail");
			String dinerEmailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
			if (dinerEmail == null || dinerEmail.trim().length() == 0) {
				errorMsgs.add("商家Email : 請勿空白");
			} else if (!dinerEmail.trim().matches(dinerEmailReg)) {
				errorMsgs.add("商家Email : 輸入的不是有效的Email地址 ");
			} else {
				// 比對資料庫第一次 : 記錄錯誤選項
				String oldDinerEmail = dinerInfoServiceImpl.checkDinerEmail(dinerEmail, dinerID);
				if (oldDinerEmail != null) {
					errorMsgs.add("商家Email : 此Email已被註冊");
				}
			}
			editInfo.setDinerEmail(dinerEmail); // 存下註冊者輸入的資訊

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
			editInfo.setDinerAddress(dinerAddress); // 存下註冊者輸入的資訊

			String dinerBank = req.getParameter("dinerBank");
			String dinerBankReg = "^[(0-9)]{3}$";
			if (dinerBank == null || dinerBank.trim().length() == 0) {
				errorMsgs.add("商家銀行代號 : 請勿空白");
			} else if (!dinerBank.trim().matches(dinerBankReg)) {
				errorMsgs.add("商家銀行代號 : 僅能為3碼的數字");
			}
			// 這裡可以做 redius，目前先這樣
			editInfo.setDinerBank(dinerBank); // 存下註冊者輸入的資訊

			String dinerAccount = req.getParameter("dinerAccount");
			String dinerAccountReg = "^[(0-9)]{10,16}$";
			if (dinerAccount == null || dinerAccount.trim().length() == 0) {
				errorMsgs.add("商家銀行帳號 : 請勿空白");
			} else if (!dinerAccount.trim().matches(dinerAccountReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("商家銀行帳號 : 僅能為10~16碼的數字");
			}
			editInfo.setDinerAccount(dinerAccount);
			; // 存下註冊者輸入的資訊

			String dinerAccountName = req.getParameter("dinerAccountName");
			String dinerAccountNameReg = "^[(\u4e00-\u9fa5)]{1,25}$";
			if (dinerAccountName == null || dinerAccountName.trim().length() == 0) {
				errorMsgs.add("商家銀行戶名 : 請勿空白");
			} else if (!dinerAccountName.trim().matches(dinerAccountNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("商家銀行戶名 : 僅能使用中文");
			}
			editInfo.setDinerAccountName(dinerAccountName); // 存下註冊者輸入的資訊

			String dinerType = req.getParameter("dinerType");
			if (dinerType == null || dinerType.trim().length() == 0) {
				errorMsgs.add("商家種類 : 請勿空白");
			} else if (!"M".equals(dinerType.trim()) && !"D".equals(dinerType.trim())
					&& !"X".equals(dinerType.trim())) {
				errorMsgs.add("商家種類 : 選項不合法");
			}
			// 雖然前端選項寫死，還是稍微做個判定，增加安全性
			editInfo.setDinerType(dinerType); // 存下註冊者輸入的資訊

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("modifiedData", editInfo); // 含有輸入格式錯誤的 dinerInfo 物件,也存入req，存入"modifiedData"
				// 特別注意 : account 是登入後就預設好的dinerInfo，不能存在這裡，
				// 不然servlet再跑第二次的時候就會讀不到資料庫的資料

				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/dashboard/info-change.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/

			newInfo = dinerInfoServiceImpl.checkEditDinerInfo(oldInfo, editInfo);
			// 確認過與資料庫無重複的資料才可被寫入
			System.out.println(newInfo);

			// 存入前先檢查資料庫裡的 dinerUpdate 欄位是空的，而且dinerStatus不能是"changed"
			if (oldInfo.getDinerUpdate() != null || oldInfo.getDinerStatus().equals("changed")) {

				req.setAttribute("alreadyApplyMsg", "您已提交過修改申請，請靜待管理員審核");

			} else {

				oldInfo = dinerInfoServiceImpl.compareDinerInfo(oldInfo, newInfo);
				System.out.println(oldInfo);
				// oldInfo : dinerUpdate 欄位被寫入、dinerStatus 欄位備置換成"changed"
				HttpSession session = req.getSession();

				if (oldInfo.getDinerUpdate() != null) {

					session.setAttribute("account", oldInfo);
					req.setAttribute("isChange", true);

					req.setAttribute("successMsg", "修改申請已送出，請靜待審核");

				} else {
					req.setAttribute("successMsg", "您沒有變更任何資料");
				}

			}

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/dinerbackground/pages/Team/dashboard/info-change.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交 registerSuccess.html
			successView.forward(req, res);

		}
		
		

//		=========================== 修改成團訂單金額 ======================================================	

		if ("dinerOrderThresholdChange".equals(action)) { // 來自business-set.jsp的請求 

			List<String> orderThresholdErrorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("orderThresholdErrorMsgs", orderThresholdErrorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			// Send the use back to the form, if there were errors
			String dinerOrderThreshold = req.getParameter("dinerOrderThreshold");
			String dinerOrderThresholdReg = "^(?!0$)([1-9][0-9]{0,3}|0)$";
			if (dinerOrderThreshold == null || dinerOrderThreshold.trim().length() == 0) {
				orderThresholdErrorMsgs.add("成團訂單金額 : 請勿空白");
			} else if (!dinerOrderThreshold.trim().matches(dinerOrderThresholdReg)) {
				orderThresholdErrorMsgs.add("成團訂單金額 : 最多只能是4位數，不得小於0");
			}

			if (!orderThresholdErrorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/dashboard/business-set.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			

			/*************************** 2.開始載入資料 *****************************************/

			HttpSession session = req.getSession();
			
			int dinerID = Integer.parseInt(req.getParameter("dinerID"));
			DinerInfo dinerInfo = dinerInfoServiceImpl.setDinerOrderThreshold(dinerID,dinerOrderThreshold); 
			
			session.setAttribute("account", dinerInfo); 
			
			/*************************** 3.新增完成,準備轉交(Send the Success view) *************/

			String url = "/dinerbackground/pages/Team/dashboard/business-set.jsp"; // 登入完成後跳轉的頁面
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 info-change.jsp
			successView.forward(req, res);
		}
		
		
//		=========================== 上傳商家照片 ======================================================	
	
		
		if ("addDinerBlob".equals(action)) { // 來自business-set.jsp的請求 

			List<String> dinerBloberrorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("dinerBloberrorMsgs", dinerBloberrorMsgs);
			
			

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			// Send the use back to the form, if there were errors
			
			int dinerID = Integer.parseInt(req.getParameter("dinerID"));
			DinerInfo dinerInfo = dinerInfoServiceImpl.getDinerInfoByDinerID(dinerID);
			
			Part dinerBlobPart  = req.getPart("dinerBlob");
			byte[] dinerBlob = null;
			try {
				InputStream in = dinerBlobPart.getInputStream();
				
				dinerBlob = in.readAllBytes();
				dinerInfo.setDinerBlob(dinerBlob);
			} catch (IOException e) {
				e.printStackTrace();
			}


//			if (!dinerBloberrorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/dinerbackground/pages/Team/dashboard/business-set.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}

			

			/*************************** 2.開始新增資料 *****************************************/

			HttpSession session = req.getSession();
			
			
			session.setAttribute("account", dinerInfo); 
			
			/*************************** 3.新增完成,準備轉交(Send the Success view) *************/

			String url = "/dinerbackground/pages/Team/dashboard/business-set.jsp"; // 完成後跳轉的頁面
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 business-set.jsp
			successView.forward(req, res);
		}
		
		

	}

}