package com.product.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.dao.ProductDAOImpl_Tz;

@WebServlet("/consumer/productDBGifReader")
public class ProductDBGifReaderServlet_Tz extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Integer productID = Integer.valueOf(req.getParameter("productID"));
			ProductDAOImpl_Tz productDAOImpl_Tz = new ProductDAOImpl_Tz();
			out.write(productDAOImpl_Tz.getProductByPK(productID).getProductBlob1());
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/consumer/NoData/null2.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}
}
