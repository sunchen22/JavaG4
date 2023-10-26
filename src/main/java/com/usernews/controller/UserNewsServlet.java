package com.usernews.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.usernews.entity.UserNews;
import com.usernews.service.UserNewsService;
import com.usernews.service.UserNewsServiceImpl;

@MultipartConfig(maxFileSize = 1073741824)

public class UserNewsServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private UserNewsService usernewsService;

	@Override
	public void init() throws ServletException { // 初始化就設定好service
		usernewsService = new UserNewsServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//	============= 查詢資料	=============
		if ("getAll".equals(action)) {

			List<UserNews> usernewsList = usernewsService.getAllUserNews();

			req.setAttribute("usernewsList", usernewsList); // setAttribute設定 將結果傳出去

			String url = "/background/pages/mem_news.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
		}

// ============= 修改:載入資料	=============
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer userNewsID = new Integer(req.getParameter("userNewsID"));

				/*************************** 2.開始查詢資料 ****************************************/
				UserNews usernews = usernewsService.getUserNewsByID(userNewsID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("usernews", usernews);
				String url = "/background/pages/mem_news_modify.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				String url = "/background/pages/mem_news.jsp";
				RequestDispatcher failView = req.getRequestDispatcher(url);
				failView.forward(req, res);
			}
		}

//		============= 修改資料&送出修改	=============
		if ("update".equals(action)) {
			// 檢查可否編修的狀態
			if (Integer.parseInt(req.getParameter("userNewsStatus").trim()) != 2) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				Integer userNewsID = new Integer(req.getParameter("userNewsID").trim());
				String userNewsContent = req.getParameter("userNewsContent").trim();
				String usernewsReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_\s)]{2,30}$";
				if (userNewsContent == null || userNewsContent.trim().length() == 0) {
					errorMsgs.add("最新消息內容: 請勿空白");
				} else if (!userNewsContent.trim().matches(usernewsReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("最新消息內容: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}
				// 自動加入存在session內的更新員工
				String account = null;
				HttpSession session = req.getSession();
				session.getAttribute(account);
				System.out.println("account : " + account);

				// 設定回傳資訊
				UserNews usernews = new UserNews();
				usernews.setUserNewsID(userNewsID);
				usernews.setUserNewsContent(userNewsContent);
				java.util.Date date = new java.util.Date();
				Calendar cal = Calendar.getInstance(); // 取得現在時間
				cal.setTime(date);
				usernews.setUserNewsReviseTime(date);

				// 有錯誤提示的的話
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("usernews", usernews); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/background/pages/mem_news_modify.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				usernewsService.updateUserNews(usernews);
				UserNews usernews2 = usernewsService.getUserNewsByID(userNewsID);
				req.setAttribute("userNews", usernews2);

				String url = "/background/pages/mem_news_listone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功轉交
				successView.forward(req, res);
			} else {
				String url = "/background/pages/mem_news.jsp";
				RequestDispatcher failView = req.getRequestDispatcher(url); // 修改失敗轉交
				failView.forward(req, res);
			}
		}

		// ============= 修改:取消 =============
//			if ("cancel".equals(action)) {			
//				try {
//					/*************************** 1.接收請求參數 ****************************************/
//					Integer userNewsID= new Integer(req.getParameter("userNewsID"));
////
////					/*************************** 2.開始查詢資料 ****************************************/
//					UserNews usernews = usernewsService.getUserNewsByID(userNewsID);
////
////					/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//					req.setAttribute("usernews", usernews);
//					String url = "/background/pages/mem_news.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
//					successView.forward(req, res);
//				} catch (Exception e) {
//					String url = "/background/pages/mem_news.jsp";
//					RequestDispatcher failView = req.getRequestDispatcher(url);
//					failView.forward(req, res);
//				}
//			}

//			============= 下架	=============
		if ("".equals(action)) {
			// 檢查可否編修的狀態
//			Integer.parseInt(req.getParameter("userNewsStatus").trim()) == 2);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
