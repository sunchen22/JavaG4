package com.product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinerinfo.dao.DinerInfoDAOImplC;
import com.dinerinfo.entity.DinerInfo;
@WebServlet("/cproject/pages/pdsc.do")
public class ProductServletC extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("go_for_product_check".equals(action)) {
			Integer dinerID = Integer.parseInt(req.getParameter("dinerID"));
			DinerInfoDAOImplC didi = new DinerInfoDAOImplC();
			DinerInfo dif = didi.findByPK(dinerID);
			req.setAttribute("dif",dif);
			
			
			
			String url = "/cproject/pages/mer_product_check.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			
			
			
		}
	}
}
