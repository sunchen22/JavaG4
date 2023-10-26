package com.dinerinfo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.businesshours.dao.BusinessHoursDAOImpl_Tz;
import com.businesshours.entity.BusinessHours_Tz;
import com.dinerinfo.dao.*;
import com.dinerinfo.entity.*;

@WebServlet("/diner.search")
public class DinerInfoServlet_Tz extends HttpServlet {

	private DinerInfoDAOImpl_Tz dinerInfoDAO;

	@Override
	public void init() throws ServletException {
		dinerInfoDAO = new DinerInfoDAOImpl_Tz(); // 實做一個方法物件
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		//搜尋商家
		String nameKeyword = req.getParameter("keyword");
        String addressKeyword = req.getParameter("address");
        nameKeyword = (nameKeyword != null) ? nameKeyword.trim() : null;
        addressKeyword = (addressKeyword != null) ? addressKeyword.trim() : null;
        List<DinerInfo> diners = dinerInfoDAO.searchDiners(nameKeyword, addressKeyword);
        req.setAttribute("diners", diners);

        
        
        String url = "/consumer/search_diner.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);


	}

}