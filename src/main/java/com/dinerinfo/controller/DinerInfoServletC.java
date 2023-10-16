package com.dinerinfo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		
		
		if("go_for_check_changed".equals(action)) {
			String str = req.getParameter("dinerID");
			Integer dinerID = Integer.valueOf(str);
			DinerInfoDAOImplC didi = new DinerInfoDAOImplC();
			DinerInfo  dif =didi.findByPK(dinerID);
			req.setAttribute("dif",dif);
			
			
//			String str1 = req.getParameter("action1");
//			String str2 = req.getParameter("action2");
//			String str3 = req.getParameter("action3");
//			String str4 = req.getParameter("action4");
//			String str5 = req.getParameter("action5");
//			String str6 = req.getParameter("action6");
//			String str7 = req.getParameter("action7");
//			String str8 = req.getParameter("action8");
//			String str9 = req.getParameter("action9");
//			String str10 = req.getParameter("action10");
//			
//			List list = new ArrayList();
//			list.add(req.getParameter("action1"));
//			list.add(str2);
//			list.add(str3);
//			list.add(str4);
//			list.add(str5);
//			list.add(str6);
//			list.add(str7);
//			list.add(str8);
//			list.add(str9);
//			list.add(str10);
//			System.out.println(list);
//			req.setAttribute("list", list);
			
			
			
	
			
			String url = "/cproject/pages/mer_details_check.jsp";	
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			
			successView.forward(req, res);
			
			return;
			
			
		}
	
	
	}
}
