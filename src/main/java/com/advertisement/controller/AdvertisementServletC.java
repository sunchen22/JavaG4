package com.advertisement.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advertisement.dao.AdvertisementDAOHibernateImplC;
import com.advertisement.entity.Advertisement;
import com.dinerinfo.dao.DinerInfoDAOImplC;
import com.dinerinfo.entity.DinerInfo;

import test.MailService;



@WebServlet("/cproject/pages/ads.do")
public class AdvertisementServletC extends HttpServlet{
	
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		
		if("go_for_check".equals(action)) {
		
		String str = req.getParameter("check");
		
		Integer advertisementID = Integer.valueOf(str);
		
		

		
		AdvertisementDAOHibernateImplC adc = new AdvertisementDAOHibernateImplC();
		
		Advertisement ad = adc.update(advertisementID);
		
		if(ad!= null) {
			ad.setAdvertisementStatus("Approved");
			MailService m = new MailService();
			m.sendMail(ad.getDinerid().getDinerEmail(), "樓頂揪樓咖通知", "您的廣告已通過審核,請靜待上架");
			
		}
		
		
		
		String url = "/cproject/pages/mer_ad.jsp";
			
		
		
		RequestDispatcher successView = req.getRequestDispatcher(url);
		
		successView.forward(req, res);
		
		return;			
		
		}
		
		
		
		if("go_for_rejected".equals(action)) {
			

			
			
					
			String str = req.getParameter("check");
			
			Integer advertisementid = Integer.valueOf(str);
			
			
			AdvertisementDAOHibernateImplC adc = new AdvertisementDAOHibernateImplC();
			
			Advertisement ad = adc.update(advertisementid);
			
			if(ad!=null) {
				
				
				
				ad.setAdvertisementStatus("Rejected");
				
				MailService m = new MailService();
				m.sendMail(ad.getDinerid().getDinerEmail(), "樓頂揪樓咖通知", "您的廣告上架區間因已有其他廣告，而未通過審核,若有需要請再次申請");
				
			}		
					

			
			
			
			
			
			String url = "/cproject/pages/mer_ad.jsp";	
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			
			successView.forward(req, res);
			
			return;			
		}
		
		
		
		
		
}
	
	
	
	
	
	
	
	
	
	
}