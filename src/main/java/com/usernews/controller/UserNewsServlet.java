package com.usernews.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.webempadmin.entity.Webempadmin;
import com.webempadmin.model.WebempadminDAO;
import com.webempadmin.model.WebempadminDAOImpl;
import com.webempadmin.model.WebempadminDAO_hibernate;

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
				return;
			}
		}

//		============= 修改資料&送出修改	=============
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer userNewsID = new Integer(req.getParameter("userNewsID").trim());
				String userNewsContent = req.getParameter("userNewsContent");
				String usernewsReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_\s)]{2,30}$";
				if (userNewsContent == null || userNewsContent.trim().length() == 0) {
					errorMsgs.add("最新消息內容: 請勿空白");
				} else if (!userNewsContent.trim().matches(usernewsReg)) {
					errorMsgs.add("最新消息內容: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}
				
				Integer userNewsStatus = new Integer(req.getParameter("userNewsStatus").trim());
				HttpSession session = req.getSession();
				String account = (String)session.getAttribute("account"); // 自動加入存在session內的更新員工

				// 設定回傳資訊
				UserNews usernews = usernewsService.getUserNewsByID(userNewsID); //找原先的usernews
				Webempadmin emp = new Webempadmin();
				WebempadminDAO_hibernate  empdao =new WebempadminDAOImpl();
				emp = empdao.findByPrimaryKey(account);
			
				usernews.setUserNewsID(userNewsID);
				usernews.setUserNewsContent(userNewsContent);
				java.util.Date date = new java.util.Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateStr = sdf.format(date);
				try {
					date = sdf.parse(dateStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				usernews.setUserNewsReviseTime(date);
				usernews.setUserNewsStatus(userNewsStatus);
				emp.setEmpName(account);
				usernews.setWebempadmin(emp);
				
				// 有錯誤提示的的話
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("usernews", usernews); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/background/pages/mem_news_modify.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				usernewsService.updateUserNews(usernews);
				UserNews usernews2 = usernewsService.getUserNewsByID(userNewsID);
				req.setAttribute("usernews", usernews2);

				String url = "/background/pages/mem_news_listone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功轉交
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/background/pages/mem_news.jsp");
				failureView.forward(req, res);
			}
		}

		// ============= 新增=============
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String userNewsContent = req.getParameter("userNewsContent");
				String usernewsReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_\s)]{2,30}$";
				if (userNewsContent == null || userNewsContent.trim().length() == 0) {
					errorMsgs.add("最新消息內容: 請勿空白");
				} else if (!userNewsContent.trim().matches(usernewsReg)) {
					errorMsgs.add("最新消息內容: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}

				Integer userNewsStatus = new Integer(req.getParameter("userNewsStatus"));

				UserNews usernews = new UserNews();
				usernews.setUserNewsContent(userNewsContent);

				java.util.Date date = new java.util.Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateStr = sdf.format(date);
				try {
					date = sdf.parse(dateStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				usernews.setUserNewsReviseTime(date);
				usernews.setUserNewsStatus(userNewsStatus);

				HttpSession session = req.getSession();
				String account = (String)session.getAttribute("account"); // 自動加入存在session內的更新員工
				
				Webempadmin emp = new Webempadmin();
				WebempadminDAO empdao =new WebempadminDAO();
				int empID = empdao.findEmpID(account);	
				System.out.println("empID : " +empID);
				empdao.findByPrimaryKey(empID);
				emp.setEmpID(empID);
				usernews.setWebempadmin(emp);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("usernews", usernews);
					RequestDispatcher failureView = req.getRequestDispatcher("/background/pages/adm_men_add.jsp");
					failureView.forward(req, res);
					return;
				}

				usernewsService.addUserNews(usernews);

				String url = "/background/pages/mem_news.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/background/pages/mem_news_add.jsp");
				failureView.forward(req, res);
			}
		}

//			============= 下架:在客戶頁面不會呈現	=============
		if ("suspend".equals(action)) {
			try {
				Integer userNewsID = new Integer(req.getParameter("userNewsID").trim());
				UserNews usernews = usernewsService.getUserNewsByID(userNewsID);
				usernews.setUserNewsStatus(0);
				usernewsService.updateUserNews(usernews);
				UserNews usernews2 = usernewsService.getUserNewsByID(userNewsID);
				req.setAttribute("usernews", usernews2);

				String url = "/background/pages/mem_news.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
				successView.forward(req, res);

			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/background/pages/mem_news.jsp");
				failureView.forward(req, res);
			}
		}

//		============= 刪除	=============
		if ("delete".equals(action)) {
			try {
				Integer userNewsID = new Integer(req.getParameter("userNewsID").trim());
				UserNews usernews = usernewsService.getUserNewsByID(userNewsID);
				usernews.setUserNewsStatus(2);
				usernewsService.deleteUserNews(userNewsID);
				UserNews usernews2 = usernewsService.getUserNewsByID(userNewsID);
				req.setAttribute("usernews", usernews2);

				String url = "/background/pages/mem_news.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
				successView.forward(req, res);

			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/background/pages/mem_news.jsp");
				failureView.forward(req, res);
			}
		}
		
//		=============取消動作:回到usernews索引頁=============
				if ("cancel".equals(action)) {
						String url = "/background/pages/mem_news.jsp"; // 指定的頁面路徑
			            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
			            dispatcher.forward(req, res);
				}
				
		

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
