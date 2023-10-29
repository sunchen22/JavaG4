package com.background;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dinerinfo.dao.DinerInfoDAOImpl_Tz;
import com.dinerinfo.service.DinerInfoServiceImpl;
import com.google.gson.Gson;
import com.grouporder.dao.GroupOrderDAOHibernateImpl_Tz;

public class BgsalesServlet extends HttpServlet {
	private DinerInfoServiceImpl dinerInfoServiceImpl;
	
	@Override
	public void init() throws ServletException {
		dinerInfoServiceImpl = new DinerInfoServiceImpl(); // 實做一個方法物件
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Gson gson = new Gson();
		String outStr = "";
		res.setContentType("text/plain; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		DinerInfoDAOImpl_Tz dinerinfo = new DinerInfoDAOImpl_Tz();
		int dinercount = dinerinfo.dinerCount();

		GroupOrderDAOHibernateImpl_Tz grouporderdao = new GroupOrderDAOHibernateImpl_Tz();
		int groupordertotalprice = grouporderdao.groupOrderPriceCount();

		HashMap<String, Object> responseData = new HashMap<>();
		responseData.put("dinercount", dinercount);
		responseData.put("groupordertotalprice", groupordertotalprice);

		outStr = gson.toJson(responseData);
		PrintWriter out = res.getWriter();
		out.println(outStr);
		System.out.println("dinercount : " + dinercount);
		System.out.println("groupordertotalprice : " + groupordertotalprice);
		System.out.println("outStr : " + outStr);
		out.close();
		
	}

}