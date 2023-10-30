package com.dinerinfo.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dinerinfo.dao.DinerInfoDAOImplC;
import com.dinerinfo.entity.DinerInfo;

import test.MailService;

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
			

			
			

			
			String url = "/cproject/pages/mer_details_check.jsp";	
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			
			successView.forward(req, res);
			
			return;
		
		}
		
		if("go_for_payment".equals(action)) {
			
			String str = req.getParameter("dinerID");
			Integer dinerID = Integer.valueOf(str);
			
			DinerInfoDAOImplC didi = new DinerInfoDAOImplC();
			DinerInfo  dif =didi.findByPK(dinerID);
			req.setAttribute("dif",dif);
			
			String url = "/cproject/pages/mer_payment_data.jsp";	
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			
			successView.forward(req, res);
			
			return;			
			
		}
		
		if("go_for_deactivated".equals(action)) {
			
			String str = req.getParameter("dinerID");
			Integer dinerID = Integer.valueOf(str);
			
			DinerInfoDAOImplC didi = new DinerInfoDAOImplC();
			DinerInfo dif = didi.DeactivatedByPK(dinerID);
			if(dif!=null) {
				
				dif.setDinerStatus("Deactivated");
				//密碼亂數產生器 傳入6
				dif.setDinerPassword(DinerPasswordGenerator.generateTemporaryPassword(6));
				MailService m = new MailService();
				m.sendMail(dif.getDinerEmail(), "樓頂揪樓咖通知", "您因違反平台條款而遭到停權或資料不正確而遭到拒絕");
				
				
			}
			String url = "/cproject/pages/mer_list.jsp";	
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			
			successView.forward(req, res);
			return;
		}
		
		
		if("go_for_active".equals(action)) {
			
			
			String str = req.getParameter("dinerID");
			Integer dinerID = Integer.valueOf(str);
			
			DinerInfoDAOImplC didi = new DinerInfoDAOImplC();
			DinerInfo dif = didi.ActiveByPK(dinerID);
			if(dif!=null) {
				
				dif.setDinerStatus("Active");
				String password = DinerPasswordGenerator.generateTemporaryPassword(6);
				dif.setDinerPassword(password);
				
				MailService m = new MailService();
				m.sendMail(dif.getDinerEmail(), "樓頂揪樓咖通知", "您的資料已通過審核,請用email中的密碼登入    " + password);
				
			
			}
			String url = "/cproject/pages/mer_application.jsp";	
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			
			successView.forward(req, res);
			
			return;
		}
		
		
		if("go_for_deactivatedJ".equals(action)) {
			
			
			String str = req.getParameter("dinerID");
			Integer dinerID = Integer.valueOf(str);
			DinerInfoDAOImplC didi = new DinerInfoDAOImplC();
			DinerInfo dif = didi.DeactivatedByPK(dinerID);
			if(dif!=null) {
				
				dif.setDinerStatus("Active");
				
				MailService m = new MailService();
				m.sendMail(dif.getDinerEmail(), "樓頂揪樓咖通知", "您因違反平台條款或資料不正確而遭到拒絕");
				
				
			}
			String url = "/cproject/pages/mer_list.jsp";	
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			
			successView.forward(req, res);
			return;
			
			
		};
		
		
		
		res.setContentType("text/plain ; charset=UTF-8");
		System.out.println("我收到了");
		BufferedReader reader = req.getReader();
		StringBuilder jsonRequest = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
		    jsonRequest.append(line);
		    
		}
	    JSONObject j = new JSONObject(jsonRequest.toString());
	    Integer dinerID = Integer.parseInt(j.getString("dinerID")); 
	    DinerInfoDAOImplC dic = new DinerInfoDAOImplC();
	    DinerInfo dif = dic.update(dinerID);
	    if(dif != null) {
	    	dif.setDinerName(j.getString("dinerName"));
	    	dif.setDinerPassword(j.getString("dinerPassword"));
	    	dif.setDinerTaxID(j.getString("dinerTaxID"));
	    	dif.setDinerContact(j.getString("dinerContact"));
	    	dif.setDinerPhone(j.getString("dinerPhone"));
	    	dif.setDinerEmail(j.getString("dinerEmail"));
	    	dif.setDinerAddress(j.getString("dinerAddress"));
	    	dif.setDinerBank(j.getString("dinerBank"));
	    	dif.setDinerAccount(j.getString("dinerAccount"));
	    	dif.setDinerAccountName(j.getString("dinerAccountName"));
	    	dif.setDinerType(j.getString("dinerTypeJ"));
	    	dif.setDinerStatus("Active");
	    	dif.setDinerUpdate(null);
	    	
	    	MailService m = new MailService();
			m.sendMail(dif.getDinerEmail(), "樓頂揪樓咖通知", "您的資料已修改,請用新密碼登入");
	    };
	    
	    
	}
}
