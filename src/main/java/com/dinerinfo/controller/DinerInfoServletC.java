package com.dinerinfo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinerinfo.dao.DinerInfoDAOImplC;
import com.dinerinfo.entity.DinerInfo;

@WebServlet("/cproject/pages/difs.do")
public class DinerInfoServletC extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("go_for_mer_data".equals(action)) {
			
			String str = req.getParameter("dinerID");
			Integer dinerID = Integer.valueOf(str);
			
			DinerInfoDAOImplC didi = new DinerInfoDAOImplC();
			DinerInfo  dif =didi.findByPK(dinerID);
			req.setAttribute("dif",dif);
			
			String url = "/cproject/pages/mer_list_data.jsp";	
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			
			successView.forward(req, res);
			
			return;			
			
		}
		
		if("go_for_check".equals(action)) {
			
			String str = req.getParameter("dinerID");
			Integer dinerID = Integer.valueOf(str);
			
			DinerInfoDAOImplC didi = new DinerInfoDAOImplC();
			DinerInfo  dif =didi.findByPK(dinerID);
			req.setAttribute("dif",dif);
			
			String url = "/cproject/pages/mer_application_check.jsp";	
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			
			successView.forward(req, res);
			
			return;			
			
		}
	
	
	
	}
}
