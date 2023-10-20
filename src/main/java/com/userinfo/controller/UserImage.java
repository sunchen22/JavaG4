package com.userinfo.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.userinfo.service.UserInfo2ServiceImpl;

//@WebServlet("/UserImage")
public class UserImage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("image/jpg");
		ServletOutputStream out = res.getOutputStream();

		int ID = Integer.parseInt(req.getParameter("userID").trim()); // 獲得請求的使用者編號
		byte[] imageData = new UserInfo2ServiceImpl().getImage(ID);
		if (imageData != null) {
			out.write(imageData);
		} else {
			System.out.println("沒有資料");
			InputStream in = getServletContext().getResourceAsStream("/background/dist/img/user00.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}
}
