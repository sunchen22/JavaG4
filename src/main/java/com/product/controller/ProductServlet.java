package com.product.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.entity.Product;
import com.product.service.ProductService;

public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("productID");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("productID", "請輸入編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer productID = null;
			try {
				productID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("productID", "編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ProductService productSvc = new ProductService();
			Product product = productSvc.getOneProduct(productID);
			if (product == null) {
				errorMsgs.put("productID", "查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("product", product); // 資料庫取出的empVO物件,存入req
			String url = "/emp/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer productID = Integer.valueOf(req.getParameter("productID"));

			/*************************** 2.開始查詢資料 ****************************************/
			ProductService empSvc = new ProductService();
			Product empVO = empSvc.getOneProduct(productID);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?empno=" + empVO.getEmpno() + "&ename=" + empVO.getEname() + "&job=" + empVO.getJob()
					+ "&hiredate=" + empVO.getHiredate() + "&sal=" + empVO.getSal() + "&comm=" + empVO.getComm()
					+ "&deptno=" + empVO.getDeptno();
			String url = "/emp/update_emp_input.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer empno = Integer.valueOf(req.getParameter("empno").trim());

			String ename = req.getParameter("ename");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (ename == null || ename.trim().length() == 0) {
				errorMsgs.put("ename", "員工姓名: 請勿空白");
			} else if (!ename.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("ename", "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String job = req.getParameter("job").trim();
			if (job == null || job.trim().length() == 0) {
				errorMsgs.put("job", "職位請勿空白");
			}

			java.sql.Date hiredate = null;
			try {
				hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("hiredate", "請輸入日期");
			}

			Double sal = null;
			try {
				sal = Double.valueOf(req.getParameter("sal").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("sal", "薪水請填數字");
			}

			Double comm = null;
			try {
				comm = Double.valueOf(req.getParameter("comm").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("comm", "獎金請填數字");
			}

			Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/update_emp_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ProductService productSvc = new ProductService();
			Product product = productSvc.updateProduct(empno, ename, job, hiredate, sal, comm, deptno);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("product", product); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/emp/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Double dinerID = req.getParameter("dinerID");

			String productName = req.getParameter("productName").trim();
			if (productName == null || productName.trim().length() == 0) {
				errorMsgs.put("productName", "職位請勿空白");
			}

			Integer productPrice = null;
			try {
				productPrice = java.sql.Date.valueOf(req.getParameter("productPrice").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("productPrice", "請輸入日期");
			}

			Integer productTypeID = null;
			try {
				productTypeID = Double.valueOf(req.getParameter("productTypeID").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("productTypeID", "薪水請填數字");
			}

			Double productDailyStock = null;
			try {
				productDailyStock = Double.valueOf(req.getParameter("productDailyStock").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("productDailyStock", "獎金請填數字");
			}

			String productRemark = null;
			if (productRemark == null || productRemark.trim().length() == 0) {
				errorMsgs.put("productRemark", "職位請勿空白");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ProductService productSvc = new ProductService();
			productSvc.addProduct(dinerID, productName, productPrice, productTypeID, productDailyStock, productRemark);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/emp/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer productID = Integer.valueOf(req.getParameter("productID"));

			/*************************** 2.開始刪除資料 ***************************************/
			ProductService empSvc = new ProductService();
			empSvc.deleteProduct(productID);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/emp/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
