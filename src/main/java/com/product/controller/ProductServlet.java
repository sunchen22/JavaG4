package com.product.controller;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.entity.Product;
import com.product.service.ProductService;
import com.product.service.ProductServiceImpl;

public class ProductServlet extends HttpServlet{

	private ProductService productService;

//	@Override
//	public void init() throws ServletException {
//		productService = new ProductServiceImpl();
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
			case "getAll":
				forwardPath = getAllProducts(req, res);
				break;
			case "compositeQuery":
				forwardPath = getCompositeProductsQuery(req, res);
				break;
			default:
				forwardPath = "/p_list.jsp";
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	private String getAllProducts(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		
		List<Product> productList = productService.getAllProducts(currentPage);

		if (req.getSession().getAttribute("productPageQty") == null) {
			int productPageQty = productService.getPageTotal();
			req.getSession().setAttribute("productPageQty", productPageQty);
		}
		
		req.setAttribute("productList", productList);
		req.setAttribute("currentPage", currentPage);
		
		return "/dinerbackground/pages/Team/p_list/p_list.jsp";
	}
	
	private String getCompositeProductsQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();
		if (map != null) {
			List<Product> productList = productService.getProductsByCompositeQuery(map);
			req.setAttribute("productList", productList);
		} else {
			return "/p_list.jsp";
		}
		return "/p_list.jsp";
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
