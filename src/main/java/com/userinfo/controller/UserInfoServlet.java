package com.userinfo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.buildinginfo.entity.BuildingInfo;
import com.userinfo.entity.UserInfo;
import com.userinfo.service.UserInfoService;
import com.grouporder.service.GroupOrderServiceImpl;


@WebServlet("/user.do")
@MultipartConfig(maxFileSize = 1073741824)
public class UserInfoServlet extends HttpServlet {
	private UserInfoService userInfoService;
	private GroupOrderServiceImpl groupOrderServiceImpl;

	@Override
	public void init() throws ServletException {
		userInfoService = new UserInfoService();
		groupOrderServiceImpl = new GroupOrderServiceImpl();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// ======================================================
		// =======================Registration=======================
		// ======================================================

		if (action.equals("registration")) { // 來自Registration.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String userAccount = req.getParameter("userAccount");
//			String userAccountReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (userAccount == null || userAccount.trim().length() == 0) {
				errorMsgs.add("姓名請勿空白");
//			} else if (!ename.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String userPassword = req.getParameter("userPassword");
			String userPasswordAgain = req.getParameter("userPasswordAgain");
			if (!userPassword.equals(userPasswordAgain)) {
				errorMsgs.add("密碼不相同");
			}

			String userName = req.getParameter("userName");
			String userNickName = req.getParameter("userNickName");
			java.sql.Date userBirthday = java.sql.Date.valueOf(req.getParameter("userBirthday"));
			String userPhone = req.getParameter("userPhone");
			Integer buildingID = Integer.valueOf(req.getParameter("buildingID"));
			Part userBlobPart = req.getPart("userBlob");

			UserInfo userInfo = new UserInfo();
			userInfo.setUserAccount(userAccount);
			userInfo.setUserPassword(userPassword);
			userInfo.setUserPhone(userPhone);
			userInfo.setUserName(userName);
			userInfo.setUserRegisterTime(new java.sql.Timestamp(System.currentTimeMillis()));
			userInfo.setUserNickName(userNickName);
			BuildingInfo buildingInfo = new BuildingInfo();
			buildingInfo.setBuildingID(buildingID);
			userInfo.setBuildinginfo(buildingInfo);
			userInfo.setUserBirthday(userBirthday);
			try {
				InputStream is = userBlobPart.getInputStream();
				;
				byte[] userBlobData = is.readAllBytes();
				userInfo.setUserBlob(userBlobData);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("userInfo", userInfo); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/consumer/Registration.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/

//			userInfoService.addUser(userAccount, userPassword, userPhone,
//					 userName, userNickName, buildingID, 
//					 userBirthday);
			userInfoService.addUser(userInfo);
			
			HttpSession session = req.getSession();
			UserInfo loginUserInfo = userInfoService.getOneByUserAccount(userAccount);
			session.setAttribute("loginUserInfo", loginUserInfo);
			req.setAttribute("isRegistration", true);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/consumer/Registration.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// ======================================================
		// ===========================Login=======================
		// ======================================================

		if (action.equals("login")) { // 來自Login.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String userAccount = req.getParameter("userAccount");
//			String userAccountReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (userAccount == null || userAccount.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
//			} else if (!ename.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String userPassword = req.getParameter("userPassword");
			UserInfo userInfo = new UserInfo();
			userInfo.setUserAccount(userAccount);
			userInfo.setUserPassword(userPassword);
			UserInfo newUserInfo = userInfoService.getOneByUserAccount(userAccount);
			// newUserInfo是從database取出的會員資料

			if (!userPassword.equals(newUserInfo.getUserPassword())) {
				errorMsgs.add("帳號密碼不相同");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("userInfo", userInfo); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/consumer/Login.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始載入資料 ***************************************/

//			userInfoService.addUser(userAccount, userPassword, userPhone,
//					 userName, userNickName, buildingID, 
//					 userBirthday);

			
			HttpSession session = req.getSession();
			session.setAttribute("loginUserInfo", newUserInfo);

			// Also need to set this attribute in action=join of GroupOrderServlet.java
    		// so that the joined group orders data can be updated from Redis upon user joining a new group order
			ArrayList<Map<String, Object>> navbarJoinedGroupOrders = (ArrayList<Map<String, Object>>) groupOrderServiceImpl.navbarJoinedGroupOrders(newUserInfo);
    		req.getSession().setAttribute("navbarJoinedGroupOrders", navbarJoinedGroupOrders);
			
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
    		String contextPath = req.getContextPath(); // 取得當前應用的 context path
    		String defaultURL = contextPath + "/consumer/index.jsp";
			

			Object locationObj = session.getAttribute("location");
			String location = (locationObj != null) ? (String) locationObj : defaultURL;

//			if (location.startsWith(contextPath)) {
//			    location = location.substring(contextPath.length()); // 去除 context path
//			}
			System.out.println(location);
			session.removeAttribute("location");
			res.sendRedirect(location);
//			RequestDispatcher successView = req.getRequestDispatcher(location);
//			
//			successView.forward(req, res);
		}

		// ======================================================
		// ======================UpdateUserInfo==================
		// ======================================================

		if (action.equals("update")) { // 來自Login.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer userID = Integer.valueOf(req.getParameter("userID"));
			UserInfo userInfo = userInfoService.getOneUserID(userID);
			String userName = req.getParameter("userName");
			String userNickName = req.getParameter("userNickName");
			String userPhone = req.getParameter("userPhone");
			Integer buildingID = Integer.valueOf(req.getParameter("buildingID"));
			Part userBlobPart = req.getPart("userBlob");

			userInfo.setUserName(userName);
			userInfo.setUserNickName(userNickName);
			userInfo.setUserPhone(userPhone);
			BuildingInfo buildingInfo = new BuildingInfo();
			buildingInfo.setBuildingID(buildingID);
			userInfo.setBuildinginfo(buildingInfo);

			// userBlob非null才取
			if (userBlobPart != null && userBlobPart.getSize() > 0) {
				try {
					InputStream is = userBlobPart.getInputStream();
					;
					byte[] userBlobData = is.readAllBytes();
					userInfo.setUserBlob(userBlobData);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("userInfo", userInfo); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/consumer/Login.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始更新資料 ***************************************/
			userInfoService.updateUserInfo(userInfo);
			HttpSession session = req.getSession();
			UserInfo loginUserInfo = userInfoService.getOneUserID(userID);
			session.setAttribute("loginUserInfo", loginUserInfo);
			req.setAttribute("isUpdate", true);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/consumer/protected/UserInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// ======================================================
		// ======================resetPwd=======================
		// ======================================================

		if (action.equals("resetPwd")) { // 來自resetPwd的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			HttpSession session = req.getSession();
			UserInfo userInfo = (UserInfo) session.getAttribute("loginUserInfo");

			String oldPwd = req.getParameter("oldPwd");

			if (!oldPwd.equals(userInfo.getUserPassword())) {
				errorMsgs.add("原密碼錯誤");
				return;
			}
			String newPwd = req.getParameter("newPwd");
			String newPwdAgain = req.getParameter("newPwdAgain");
			if (!newPwd.equals(newPwdAgain)) {
				errorMsgs.add("新密碼不相同");
				return;
			}

			userInfo.setUserPassword(newPwd);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("userInfo", userInfo); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/consumer/Login.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始更新資料 ***************************************/
			userInfoService.updateUserInfo(userInfo);

			session.setAttribute("loginUserInfo", userInfo);
			req.setAttribute("isUpdate", true);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/consumer/protected/UserInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		//--------------Logout  登出------------------------
		//--------------Logout  登出------------------------
		//--------------Logout  登出------------------------
		
		if(action.equals("logout")) {
			req.getSession().removeAttribute("loginUserInfo");
			req.getSession().invalidate();  // Remove all session attributes
			req.getRequestDispatcher("/consumer/index.jsp").forward(req, res);	
		}
	}
}
