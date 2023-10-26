package com.grouporder.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinerinfo.dao.DinerInfoDAOImplC;
import com.dinerinfo.entity.DinerInfo;
import com.grouporder.dao.GroupOrderDAOHibernateImpl;
import com.grouporder.dao.GroupOrderDAOHibernateImplC;
import com.grouporder.entity.GroupOrder;

@WebServlet("/cproject/pages/gos.do")
public class GroupOrderServletC extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
		String action = req.getParameter("action");

		if ("go_for_data".equals(action)) {
			
			String str = req.getParameter("dinerID");
			
			Integer dinerID = Integer.valueOf(str);
			
			DinerInfoDAOImplC didic = new DinerInfoDAOImplC();
			DinerInfo dif = didic.findByPK(dinerID);
			req.setAttribute("dif", dif);
			

			
			String url = "/cproject/pages/order_query_data.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}

	}
}
