package com.dinerinfo.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinerinfo.service.*;

@WebServlet("/consumer/dinerDBGifReader")
public class DinerDBGifReaderServlet_Tz extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Integer dinerID = Integer.valueOf(req.getParameter("dinerID"));
			DinerInfoService dinerInfoService = new DinerInfoServiceImpl();
			out.write(dinerInfoService.getDinerInfoByDinerID(dinerID).getDinerBlob());
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/consumer/NoData/null2.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();

		}
	}

}
