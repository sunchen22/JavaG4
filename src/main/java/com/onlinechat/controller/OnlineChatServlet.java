package com.onlinechat.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OnlineChatServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String userName = req.getParameter("userName"); 

		req.setAttribute("userName", userName);
			//cs就導去客服頁面
		if ("cs".equals(userName) ) {
			System.out.println(userName);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/background/pages/svr_cust.jsp");
			dispatcher.forward(req, res);
		} else {
			//改成消費者頁面
			System.out.println(userName);
			RequestDispatcher dispatcher = req.getRequestDispatcher("consumer/protected/Chatroomchat.jsp");
			dispatcher.forward(req, res); 
		}
	}
}
