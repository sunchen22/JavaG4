package com.dinerinfo.controller;

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
import com.google.gson.JsonObject;









@WebServlet("/cproject/pages/difs1.do")
public class DinerInfoServletC1 extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
	
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
//			JSONObject j = new JSONObject();
//			
//			j.put("a",str1);
//			j.put("b",str2);
//			j.put("c",str3);
//			j.put("d",str4);
//			j.put("e",str5);
//			j.put("f",str6);
//			j.put("g",str7);
//			j.put("h",str8);
//			j.put("i",str9);
//			j.put("j",str10);
//			
//			
//			
//			didi.update(dinerID);
//			if(didi!= null) {
//				dif.setDinerUpdate(j.toString());
//			}
			
			
			

			
			String url = "/cproject/pages/mer_details_check.jsp";	
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			
			successView.forward(req, res);
			
			return;
			
			
	
			

			
			
		}
	
	
	}

