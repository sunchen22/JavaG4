package com.dinerinfo.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinerinfo.service.DinerInfoServiceImpl;

@WebServlet("/DinerInfoImg")
public class DinerInfoImg extends HttpServlet {
	
	private DinerInfoServiceImpl dinerInfoServiceImpl;

	@Override
	public void init() throws ServletException {
		dinerInfoServiceImpl = new DinerInfoServiceImpl(); // 實做一個方法物件
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			int id = Integer.parseInt(req.getParameter("id").trim());
			
			byte[] dinerBlob = dinerInfoServiceImpl.getDinerBlob(id);
			
			if (dinerBlob != null) {
				out.write(dinerBlob);
			} else {
				// res.sendError(HttpServletResponse.SC_NOT_FOUND);
				
				InputStream in = getServletContext().getResourceAsStream("/consumer/NoData/null2.jpg");
				byte[] d = new byte[in.available()];
				in.read(d);
				out.write(d);
				in.close();
			}
		} catch (Exception e) {
			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/consumer/NoData/null2.jpg");
			byte[] d = in.readAllBytes(); 
			out.write(d);
			in.close();
		}
	}
	

}
