package com.dinerratingcomment.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinerinfo.entity.DinerInfo;
import com.dinerratingcomment.dao.DinerRatingCommentDAO;
import com.dinerratingcomment.entity.DinerRatingComment;
import com.userinfo.entity.UserInfo;

@WebServlet("/comment.do")
@MultipartConfig(maxFileSize = 1073741824)
public class DinerRatingCommentServlet extends HttpServlet {
	private DinerRatingCommentDAO dinerRatingCommentDAO;

	@Override
	public void init() throws ServletException {
		dinerRatingCommentDAO = new DinerRatingCommentDAO();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// ======================================================
		// ==================Insert Comment=======================
		// ======================================================

		if (action.equals("insertComment")) { // 來自Registration.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String dinerIDStr = req.getParameter("dinerID");
			Integer dinerID = (Integer) 1;
			if (dinerIDStr != null && !dinerIDStr.isEmpty()) {
				try {
					dinerID = Integer.valueOf(dinerIDStr);
				} catch (NumberFormatException e) {
					errorMsgs.add("Diner ID format error");
				}
			}
			Integer userID = Integer.valueOf(req.getParameter("userID"));
			String dinerRatingStr = req.getParameter("dinerRating");
			Integer dinerRating = (Integer) 1;
			if (dinerRatingStr != null && !dinerRatingStr.isEmpty()) {
				try {
					dinerRating = Integer.valueOf(dinerRatingStr); // 嘗試轉換
				} catch (NumberFormatException e) {
					errorMsgs.add("評分格式不正確");
				}
			} else {
				errorMsgs.add("評分不能為空");
			}
			String userCommentContent = req.getParameter("userCommentContent");

			DinerRatingComment dinerRatingComment = new DinerRatingComment();

			dinerRatingComment.setDinerRating(dinerRating);

			dinerRatingComment.setUserCommentTime(new java.sql.Timestamp(System.currentTimeMillis()));
			dinerRatingComment.setUserCommentContent(userCommentContent);
			dinerRatingComment.setDinerRatingCommentStatus((Integer) 1);

			UserInfo userInfo = new UserInfo();
			userInfo.setUserID(userID);
			dinerRatingComment.setUserInfo(userInfo);

			DinerInfo dinerInfo = new DinerInfo();
			dinerInfo.setDinerID(dinerID);
			dinerRatingComment.setDinerInfo(dinerInfo);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("dinerRatingComment", dinerRatingComment); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/consumer/protected/DinerComment.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/

			try {
				dinerRatingCommentDAO.insert(dinerRatingComment);
			} catch (Exception e) {
				// FIXME DB有問題時沒有處理
				errorMsgs.add("Database error: " + e.getMessage());
			}

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/consumer/protected/DinerComment.jsp?dinerID="+dinerID;
			String cp = req.getContextPath();
			res.sendRedirect(cp + url);
			
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
		}

	}

}
