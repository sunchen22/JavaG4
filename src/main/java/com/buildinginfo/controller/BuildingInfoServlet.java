package com.buildinginfo.controller;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.buildinginfo.dao.BuildingInfoDAO;
import com.buildinginfo.dao.BuildingInfoDAOHibernateImpl;
import com.buildinginfo.entity.BuildingInfo;

public class BuildingInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("get_Data_Display".equals(action)) { // 來自select_page.jsp的請求
			
			String str = req.getParameter("bdgname");
			
																
			if((str.equals("")|| (str.trim()).length() == 0)){ 
				
				RequestDispatcher successView = req.getRequestDispatcher("/cproject/pages/bldg_queryAll_data.jsp");
				
				successView.forward(req, res);
				
			}

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			if (!(str.equals("") || (str.trim()).length() == 0)) {
				
				Integer buildingID = null;
				
				try {
					buildingID = Integer.valueOf(str);
					
				} catch (Exception e) {
					errorMsgs.add("格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/cproject/pages/bldg_query.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				BuildingInfoDAOHibernateImpl bdhi = new BuildingInfoDAOHibernateImpl();
				BuildingInfo bif = bdhi.findByPK(buildingID);
				System.out.println(bif);
//				BuildingInfo bif1 = bdhi.getOne(buildingName);
				

				if (bif == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/cproject/pages/bldg_query.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				req.setAttribute("bif", bif); // 資料庫取出的buildininfo物件,存入req
//				req.setAttribute("bif1", bif1);
				String url = "/cproject/pages/bldg_queryOne_data.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			}

		}

		if ("delete".equals(action)) {
			
			Integer buildingID = Integer.valueOf(req.getParameter("buildingID"));
			
			/*************************** 2.開始刪除資料 ***************************************/
			BuildingInfoDAO dao = new BuildingInfoDAOHibernateImpl();
			dao.down(buildingID);
			

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/cproject/pages/bldg_queryAll_data.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);

		}
		
		
		res.setContentType("text/plain ; charset=UTF-8");
		
		String name = req.getParameter("name");
	    String address = req.getParameter("address");
	    if(name != null && address != null) {

	    BuildingInfoDAOHibernateImpl bi = new BuildingInfoDAOHibernateImpl();
	    bi.add(name,address);
	    
	    }
		
	    return;
		
	}

}
