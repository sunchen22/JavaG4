package com.grouporder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grouporder.dao.GroupOrderDAOHibernateImplC;
@WebServlet("/cproject/pages/gosimg.do")
public class GroupOrderServletImgC extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		Integer groupOrderID = Integer.parseInt(req.getParameter("groupOrderID"));
		
		
		byte[] b = new GroupOrderDAOHibernateImplC().getImg(groupOrderID);
		
		out.write(b);
		
}
}