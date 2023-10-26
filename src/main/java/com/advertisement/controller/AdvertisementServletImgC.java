package com.advertisement.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advertisement.dao.AdvertisementDAOHibernateImplC;
import com.product.dao.ProductDAOImplC;
@WebServlet("/cproject/pages/adc.do")

public class AdvertisementServletImgC extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		
		Integer advertisementID = Integer.parseInt(req.getParameter("advertisementID"));
		
		
		byte[] b = new AdvertisementDAOHibernateImplC().getImg(advertisementID);
		
		
		
		
		out.write(b);
	
		
		
}
}
