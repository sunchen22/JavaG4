package com.businesshours.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.businesshours.service.BusinessHoursServiceImpl;

public class BusinessHoursSet extends HttpServlet {
	// One service instance for one servlet instance
	private BusinessHoursServiceImpl businessHoursServiceImpl;

	@Override
	public void init() throws ServletException {
		businessHoursServiceImpl = new BusinessHoursServiceImpl(); // 實做一個方法物件
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//			=========================== 改變營業狀態 ======================================================	

	}
}
