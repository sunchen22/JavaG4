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
import com.product.dao.ProductDAOImplC;
import com.product.entity.Product;

import test.MailService;
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

			return;
			
			
		}
		
		if ("go_for_down".equals(action)) {
			
			Integer productID = Integer.parseInt(req.getParameter("productID"));
			
			Product pdt = new ProductDAOImplC().down(productID);
			
			if( pdt!= null) {
				
				pdt.setProductStatus("下架中");
				MailService m = new MailService();
				m.sendMail(pdt.getDinerinfo().getDinerEmail(), "樓頂揪樓咖通知", "您的商品資訊有誤而下架，若有需要再次上架請注意上架資訊是否有誤");
				
				
			}
			
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
