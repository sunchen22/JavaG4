package com.dinerratingcomment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinerinfo.dao.DinerInfoDAOC;
import com.dinerinfo.dao.DinerInfoDAOImplC;
import com.dinerinfo.entity.DinerInfo;
import com.dinerratingcomment.dao.DinerRatingCommentDAOImplC;
import com.dinerratingcomment.entity.DinerRatingComment;

@WebServlet("/cproject/pages/drcs.do")
public class DinerRatingCommentServletC extends HttpServlet {

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("go_for_review".equals(action)) {
			
			String str = req.getParameter("dinerID");
			Integer dinerID = Integer.valueOf(str);
				
			DinerInfoDAOImplC didi = new DinerInfoDAOImplC();
			DinerInfo  dif =didi.findByPK(dinerID);
			req.setAttribute("dif",dif);
			
//			DinerRatingCommentDAOImplC drcdi = new DinerRatingCommentDAOImplC();
//			List<DinerRatingComment> list = drcdi.getAll(dinerID);
//			req.setAttribute("drc",list);
			
			
			
			
			String url = "/cproject/pages/order_review_data.jsp";	
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			
			successView.forward(req, res);
			
			return;			
			
		}
		
		if("go_for_delete".equals(action)) {
			String str = req.getParameter("commentID");
			Integer commentID = Integer.valueOf(str);
			
			String str1 = req.getParameter("dinerID");
			Integer dinerID = Integer.valueOf(str1);
			DinerInfoDAOImplC didi = new DinerInfoDAOImplC();
			DinerInfo  dif =didi.findByPK(dinerID);
			req.setAttribute("dif",dif);
			
			
			
			DinerRatingCommentDAOImplC drcoi = new DinerRatingCommentDAOImplC();
			drcoi.delete(commentID);
			
			
			
			
			String url = "/cproject/pages/order_review_data.jsp";	
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			
			successView.forward(req, res);
			
			return;			
			
		}
		
		
}
}