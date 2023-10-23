package com.product.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.dao.ProductDAOImplC;
@WebServlet("/cproject/pages/psimg.do")

public class ProductServletImgC extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		
		Integer productID = Integer.parseInt(req.getParameter("productID"));
		
		
		List<byte[]> b = new ProductDAOImplC().getImg(productID);
//		
		byte[] a = b.get(0);
//		System.out.println(a);
//		byte[] c = b.get(1);
//		byte[] d = b.get(2);
		
		out.write(a);
//		out.write(c);
//		
//		
//		
//		out.write(d);
		
		
}
}
