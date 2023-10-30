package com.userinfo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.buildinginfo.entity.BuildingInfo;
import com.userinfo.entity.UserInfo;
import com.userinfo.service.UserInfo2Service;
import com.userinfo.service.UserInfo2ServiceImpl;

@MultipartConfig(maxFileSize = 1073741824)
public class UserInfo2Servlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private UserInfo2Service userinfoService;

	@Override
	public void init() throws ServletException { // 初始化就設定好service
		userinfoService = new UserInfo2ServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//	============= 查詢資料	=============
		if ("getAll".equals(action)) {
			String page = req.getParameter("page"); // 取得第幾頁
			int currentPage = (page == null) ? 1 : Integer.parseInt(page); // 若沒帶回值，表示在第一頁

			List<UserInfo> userinfoList = userinfoService.getAllUserInfo(currentPage);

			if (req.getSession().getAttribute("empPageQty") == null) {
				int empPageQty = userinfoService.getPageTotal();
				req.getSession().setAttribute("empPageQty", empPageQty);
				System.out.println("我在getall查詢內 empPageQty:"+empPageQty);
			}

			req.setAttribute("userinfoList", userinfoList); // setAttribute設定 將結果傳出去
			req.setAttribute("currentPage", currentPage); // setAttribute設定 將結果傳出去
			System.out.println("我在getall查詢內 currentPage:"+currentPage);

			String url = "/background/pages/mem_account.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
		}

//	============= 修改 : 載入修改資料	=============
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("=======================");
			System.out.println("我在getOne_For_Update");
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer userID = new Integer(req.getParameter("userID"));

				/*************************** 2.開始查詢資料 ****************************************/
				UserInfo userinfo = userinfoService.getUserInfoByuserID(userID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("userinfo", userinfo);
				String url = "/background/pages/mem_account_modify.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				String url = "/background/pages/mem_account.jsp";
				RequestDispatcher failView = req.getRequestDispatcher(url);
				failView.forward(req, res);
			}
		}

//	============= 修改資料&送出修改	=============
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer userID = new Integer(req.getParameter("userID").trim());
			String userAccount = req.getParameter("userAccount").trim();
			String userName = req.getParameter("userName").trim();
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_\s)]{2,10}$";
			if (userName == null || userName.trim().length() == 0) {
				errorMsgs.add("會員姓名: 請勿空白");
			} else if (!userName.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String userPhone = req.getParameter("userPhone").trim();
			String phoneReg = "^09\\d{2}-?\\d{3}-?\\d{3}$";
			if (userPhone == null || userPhone.trim().length() == 0) {
				errorMsgs.add("手機號碼請勿空白");
			} else if (!userPhone.trim().matches(phoneReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("請直接輸入09開頭，長度10的手機號碼");
			}

			String userPassword = req.getParameter("userPassword").trim();
			String userNickName = req.getParameter("userNickName").trim();
			java.sql.Timestamp userRegisterTime = java.sql.Timestamp
					.valueOf(req.getParameter("userRegisterTime").trim());
			java.sql.Date userBirthday = java.sql.Date.valueOf(req.getParameter("userBirthday").trim());
			Integer buildingID = Integer.valueOf(req.getParameter("buildingID").trim());

			// 設定回傳資訊
			UserInfo userinfo = new UserInfo();
			BuildingInfo buildingInfo = new BuildingInfo();
			userinfo.setUserID(userID);
			userinfo.setUserAccount(userAccount);
			userinfo.setUserName(userName);
			userinfo.setUserPhone(userPhone);
			userinfo.setUserPassword(userPassword);
			userinfo.setUserNickName(userNickName);
			userinfo.setUserRegisterTime(userRegisterTime);
			userinfo.setUserBirthday(userBirthday);
			buildingInfo.setBuildingID(buildingID); // 關聯表格的欄位要隨之調整
			userinfo.setBuildinginfo(buildingInfo);

			// 圖片
			byte[] userBlob = null;
			InputStream in = req.getPart("userBlob").getInputStream();
			if (in.available() == 0) { // 取原本資料內的照片
				UserInfo userinfoorg = userinfoService.getUserInfoByuserID(userID);
				userBlob = userinfoorg.getUserBlob();
				userinfo.setUserBlob(userBlob);
			} else {
				userBlob = new byte[in.available()];
				in.read(userBlob);
				in.close();
				userinfo.setUserBlob(userBlob);
			}

			// 有錯誤提示的的話
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("userinfo", userinfo); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/background/pages/mem_account_modify.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			userinfoService.updateUserInfo(userinfo);
			UserInfo userinfo2 = userinfoService.getUserInfoByuserID(userID);
			req.getSession().setAttribute("userinfo", userinfo2);


			// mem_account_results.jsp頁碼處理
			String page = req.getParameter("page"); // 取得第幾頁
			System.out.println("page" + page);
			int currentPage = (page == null) ? 1 : Integer.parseInt(page); // 若沒帶回值，表示在第一頁

			if (req.getSession().getAttribute("empPageQty") == null) {
				int empPageQty = userinfoService.getPageTotal();
				req.getSession().setAttribute("empPageQty", empPageQty);
				System.out.println("empPageQty"+empPageQty);
			}
			req.setAttribute("currentPage", currentPage); // setAttribute設定 將結果傳出去
			System.out.println("currentPage"+currentPage);

			String url = "/background/pages/mem_account_listone.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功轉交
			successView.forward(req, res);
		}

//	============= 複合查詢	=============
		if ("compositeQuery".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Map<String, String[]> map = req.getParameterMap(); // 拿請求參數,回傳MAP
				if (map != null) {
					List<UserInfo> userinfoList = userinfoService.getUserInfoByCompositeQuery(map);
					req.setAttribute("userinfoList", userinfoList);
					
				} else {
					String url = "/background/pages/mem_account.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功轉交
					successView.forward(req, res);
				}
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/background/pages/mem_account.jsp");
				failureView.forward(req, res);
			}
			String url = "/background/pages/mem_account_results.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功轉交
			successView.forward(req, res);
			
		}
		
//		=============取消動作:回到索引頁=============
				if ("cancel".equals(action)) {
						String url = "/background/pages/mem_account.jsp"; // 指定的頁面路徑
			            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
			            dispatcher.forward(req, res);
				}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
