package com.webempadmin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.grouporder.dao.GroupOrderDAOHibernateImpl_Tz;
import com.webempadmin.model.WebempadminDAO;

@MultipartConfig(fileSizeThreshold = 0*1024*1024, maxFileSize = 1*1024*1024, maxRequestSize = 10*1024*1024)
public class EmpNameServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Gson gson = new Gson();
		String outStr = "";
		res.setContentType("text/plain; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String empName = req.getParameter("empName");
		System.out.println("empName : " + empName);
		
		WebempadminDAO emp = new WebempadminDAO();
	    if (emp.findEmpID(empName) != 0) {
	        String repeatName = "true";
	        outStr = gson.toJson(repeatName);
	    }
	    PrintWriter out = res.getWriter();
	    out.println(outStr);
	    System.out.println("outStr : " + outStr);
	    out.close();

	}
}
