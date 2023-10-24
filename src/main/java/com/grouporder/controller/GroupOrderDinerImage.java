package com.grouporder.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.grouporder.service.GroupOrderServiceImpl;

@WebServlet("/GroupOrderDinerImage")
public class GroupOrderDinerImage extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			// EL: <img	src="${pageContext.request.contextPath}/GroupOrderDinerImage?entity=yyy&id=x&no=z>
			String entity = req.getParameter("entity").trim();
			int id = Integer.parseInt(req.getParameter("id").trim());
			
			byte[] imageData;

			if (entity.equals("GroupOrder")){
				imageData = new GroupOrderServiceImpl().getGroupOrderDinerImage(id);
			}else if (entity.equals("Product")) {
				int no = Integer.parseInt(req.getParameter("no").trim());
				imageData = new GroupOrderServiceImpl().getGroupOrderProductImage(id, no);
			}else {
				imageData = null;
			}
			
			if (imageData != null) {
				out.write(imageData);
			} else {
				// res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/consumer/NoData/null2.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}

		} catch (Exception e) {
			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/consumer/NoData/null2.jpg");
			byte[] b = in.readAllBytes(); // Java 9
//			byte[] b = new byte[in.available()];
//			in.read(b);
			out.write(b);
			in.close();
		}
	}
}