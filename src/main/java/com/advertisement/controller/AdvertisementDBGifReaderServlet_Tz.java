package com.advertisement.controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.advertisement.dao.*;

@WebServlet("/consumer/adDBGifReader")
public class AdvertisementDBGifReaderServlet_Tz extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Integer adID = Integer.valueOf(req.getParameter("adID"));
			AdvertisementDAO_Tz dao = new AdvertisementDAO_Tz();
			out.write(dao.getImg(adID));
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/consumer/NoData/adnull.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}
}
