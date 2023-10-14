package com.advertisement.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advertisement.service.AdvertisementService;
import com.advertisement.service.AdvertisementServiceImpl;
import com.advertisement.entity.Advertisement;



public class AdvertisementServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體 git test
	private AdvertisementService advertisementService;


	@Override
	public void init() throws ServletException {
		advertisementService = new AdvertisementServiceImpl();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
			case "getAll":
				forwardPath = getAllAdvertisements(req, res);
				break;
			case "compositeQuery":
				forwardPath = getCompositeAdvertisementsQuery(req, res);
				break;
			default:
				forwardPath = "/index.jsp";
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	private String getAllAdvertisements(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		
		List<Advertisement> advertisementList = advertisementService.getAllAdvertisements(currentPage);

		if (req.getSession().getAttribute("advertisementPageQty") == null) {
			int advertisementPageQty = advertisementService.getPageTotal();
			req.getSession().setAttribute("advertisementPageQty", advertisementPageQty);
		}
		
		req.setAttribute("advertisementList", advertisementList);
		req.setAttribute("currentPage", currentPage);
		
		return "/advertisement/listAllAdvertisements.jsp";
	}
	
	private String getCompositeAdvertisementsQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();
		if (map != null) {
			List<Advertisement> advertisementList = advertisementService.getAdvertisementsByCompositeQuery(map);
			req.setAttribute("advertisementList", advertisementList);
		} else {
			return "/index.jsp";
		}
		return "/advertisement/listCompositeQueryAdvertisements.jsp";
	}
	

	
	
}
