package com.userinfo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
import com.webempadmin.model.WebempadminService;

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
		String forwardPath = "";
		switch (action) { // 針對請求字串,導去對應的方法或頁面
		case "getAll":
			forwardPath = getAllEmps(req, res);
			break;
		case "compositeQuery":
			forwardPath = getUserInfoByCompositeQuery(req, res);
			break;
		case "modifyuserinfo":
			forwardPath = forwardUserinfoUpdatePage(req, res);
			break;

		case "update":
			forwardPath = userinfoupdate(req, res);
			break;

		default:
			forwardPath = "/background/pages/mem_account.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

//	============= 查詢資料	=============
	private String getAllEmps(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page"); // 取得第幾頁
		int currentPage = (page == null) ? 1 : Integer.parseInt(page); // 若沒帶回值，表示在第一頁
		
		List<UserInfo> userinfoList = userinfoService.getAllUserInfo(currentPage);

		if (req.getSession().getAttribute("empPageQty") == null) {
			int empPageQty = userinfoService.getPageTotal();
			req.getSession().setAttribute("empPageQty", empPageQty);
		}

		req.setAttribute("userinfoList", userinfoList); // setAttribute設定 將結果傳出去
		req.setAttribute("currentPage", currentPage); // setAttribute設定 將結果傳出去

		return "/background/pages/mem_account.jsp"; // 要forward到這裡
	}

//	============= 修改 : 載入修改資料	=============
	private String forwardUserinfoUpdatePage(HttpServletRequest req, HttpServletResponse res) throws IOException {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑

		try {
			Integer userID = Integer.parseInt(req.getParameter("userID"));
			UserInfo userinfo = userinfoService.getUserInfoByuserID(userID);

			//頁碼
//			String page = req.getParameter("page"); // 取得第幾頁
//			int currentPage = (page == null) ? 1 : Integer.parseInt(page); // 若沒帶回值，表示在第一頁
//			if (req.getSession().getAttribute("empPageQty") == null) { // 頁數相關
//				int empPageQty = userinfoService.getPageTotal();
//				req.getSession().setAttribute("empPageQty", empPageQty);
//			}

			req.setAttribute("userinfo", userinfo); // setAttribute設定 將結果傳出去
//			req.setAttribute("currentPage", currentPage); // setAttribute設定 將結果傳出去

			return "/background/pages/mem_account_modify.jsp"; // 要forward到這裡
		} catch (Exception e) {
			errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
			return requestURL;
		}
	}

//	============= 修改資料&送出修改	=============
	private String userinfoupdate(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

			Integer userID = Integer.parseInt(req.getParameter("userID").trim());
			String userAccount = req.getParameter("userAccount").trim();
			String userName = req.getParameter("userName").trim();
			String userPhone = req.getParameter("userPhone").trim();
			String userPassword = req.getParameter("userPassword").trim();
			String userNickName = req.getParameter("userNickName").trim();
			java.sql.Timestamp userRegisterTime = java.sql.Timestamp.valueOf(req.getParameter("userRegisterTime").trim());
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
				userBlob = new UserInfo2ServiceImpl().getImage(userID);
			} else {
				userBlob = new byte[in.available()];
				in.read(userBlob);
				in.close();
			}
			userinfo.setUserBlob(userBlob);

			userinfoService.updateUserInfo(userinfo); 
			UserInfo userinfo2 = userinfoService.getUserInfoByuserID(userID);
			req.getSession().setAttribute("userinfo", userinfo2); // setAttribute設定 將結果傳出去

			//mem_account_results.jsp頁碼處理
//			getAllEmps(req, res);
//			String page = req.getParameter("page"); // 取得第幾頁
//			System.out.println("page"+page);
//			int currentPage = (page == null) ? 1 : Integer.parseInt(page); // 若沒帶回值，表示在第一頁
//		
//			if (req.getSession().getAttribute("empPageQty") == null) {
//				int empPageQty = userinfoService.getPageTotal();
//				req.getSession().setAttribute("empPageQty", empPageQty);
//			}
//			req.setAttribute("currentPage", currentPage); // setAttribute設定 將結果傳出去
			
			//將結果傳入mem_account_results.jsp 總清單頁面 OK
//			getUserInfoByCompositeQuery(req,res);
//			Map<String, String[]> map = req.getParameterMap(); // 拿請求參數,回傳MAP
//			if (map != null) {
//				List<UserInfo> userinfoList = userinfoService.getUserInfoByCompositeQuery(map);
//				req.setAttribute("userinfoList", userinfoList);
//			}
			
			//test 將結果傳入mem_account_results.jsp 總清單頁面+分頁
			//但Update會失敗
			String page = req.getParameter("page"); // 取得第幾頁
			int currentPage = (page == null) ? 1 : Integer.parseInt(page); // 若沒帶回值，表示在第一頁
			List<UserInfo> userinfoList = userinfoService.getAllUserInfo(currentPage);
			
			if (req.getSession().getAttribute("empPageQty") == null) {
				int empPageQty = userinfoService.getPageTotal();
				req.getSession().setAttribute("empPageQty", empPageQty);
			}
			req.setAttribute("userinfoList", userinfoList); // setAttribute設定 將結果傳出去
			req.setAttribute("currentPage", currentPage);
			
			
			return "/background/pages/mem_account_results.jsp"; // 要forward到這裡
		}


//	============= 複合查詢	=============
	private String getUserInfoByCompositeQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap(); // 拿請求參數,回傳MAP
		if (map != null) {
			List<UserInfo> userinfoList = userinfoService.getUserInfoByCompositeQuery(map);
			req.setAttribute("userinfoList", userinfoList);
		} else {
			return "/background/pages/mem_account_results.jsp";
		}
		return "/background/pages/mem_account.jsp";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
