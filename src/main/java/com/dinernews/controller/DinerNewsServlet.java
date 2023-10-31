package com.dinernews.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinernews.dao.DinerNewsDAOHibernateImpl;
import com.dinernews.entity.DinerNews;
import com.webempadmin.entity.Webempadmin;
@WebServlet("/cproject/pages/dns.do")
public class DinerNewsServlet extends HttpServlet {
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	
		
		req.setCharacterEncoding("UTF-8");
		
		String news1 = req.getParameter("news1");
		String news2 = req.getParameter("news2");
		String news3 = req.getParameter("news3");
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		
		String emp = req.getParameter("emp");
		Integer empid = Integer.parseInt(emp);
		
		DinerNewsDAOHibernateImpl dndi = new DinerNewsDAOHibernateImpl();
		DinerNews dn = dndi.update();
		if(dn!=null) {
		
		Webempadmin w = new Webempadmin();
		w.setEmpID(empid);//new一個Webempadmin,透過映射關係set 49行
	
		
		dn.setDinerNewsContent1(news1);
		dn.setDinerNewsContent2(news2);
		dn.setDinerNewsContent3(news3);
		dn.setWebempadmin(w);
		dn.setDinerNewsStatus(1);
		
		try {
			
			dn.setDinerNewsReleaseTime(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));
			
		
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		try {
			dn.setDinerNewsReviseTime(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
	
		
		}
		
		

		String url = "/cproject/pages/mer_news.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
		successView.forward(req, res);

	
	}
	

	
}