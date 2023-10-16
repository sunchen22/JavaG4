package com.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinerinfo.dao.DinerInfoDAOImplC;
import com.dinerinfo.entity.DinerInfo;
import com.product.dao.ProductDAOImplC;
@WebServlet("/cproject/pages/pdsc.do")
public class ProductServletC extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
//		res.setContentType("image/gif");
//		ServletOutputStream out = res.getOutputStream();
		
		
		String action = req.getParameter("action");
		
		if ("go_for_product_check".equals(action)) {
			Integer dinerID = Integer.parseInt(req.getParameter("dinerID"));
			DinerInfoDAOImplC didi = new DinerInfoDAOImplC();
			DinerInfo dif = didi.findByPK(dinerID);
			req.setAttribute("dif",dif);
			
			
//			List<byte[]> b = new ProductDAOImplC().getImg1(dinerID);
//			
//			byte[] a = b.get(0);			
////			byte[] c = b.get(1);
////			byte[] d = b.get(2);
//			System.out.println(a);
//			
//			out.write(a);
////			out.write(c);
////			out.write(d);
//			out.flush();
//			out.close();
			
			

			String url = "/cproject/pages/mer_product_check.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			
			
			
		}
	}
}
