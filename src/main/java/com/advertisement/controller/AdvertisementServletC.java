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
		
		
//		DinerInfoDAOImplC dic = new DinerInfoDAOImplC();
//		DinerInfo dif = dic.findByPK(dinerid);
//		
//		req.setAttribute("dif", dif);
		
		AdvertisementDAOHibernateImplC adc = new AdvertisementDAOHibernateImplC();
		
		Advertisement ad = adc.update(advertisementID);
		
		if(ad!= null) {
			ad.setAdvertisementStatus("Approved");
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
				
				
				
			}		
					

			
			
			
			
			
			String url = "/cproject/pages/mer_ad.jsp";	
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			
			successView.forward(req, res);
			
			return;			
		}
		
		
		
		
		
}
	
	
	
	
	
	
	
	
	
	
}