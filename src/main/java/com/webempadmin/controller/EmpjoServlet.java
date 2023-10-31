package com.webempadmin.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.webempadmin.model.WebempadminService;
import com.webempadmin.model.WebempadminVO;

@MultipartConfig(fileSizeThreshold = 0*1024*1024, maxFileSize = 1*1024*1024, maxRequestSize = 10*1024*1024)
public class EmpjoServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理*********************/
				String str = req.getParameter("empID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("empID","請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer empID = null;
				try {
					empID = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("empID","員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				WebempadminService empSvc = new WebempadminService();
				WebempadminVO empVO = empSvc.getOneEmp(empID);
				if (empVO == null) {
					errorMsgs.put("empID","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer empID = Integer.valueOf(req.getParameter("empID"));
				
				/***************************2.開始查詢資料***************************************/
				WebempadminService empSvc = new WebempadminService();
				WebempadminVO empVO = empSvc.getOneEmp(empID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				
				req.setAttribute("empVO", empVO); 
				String url = "/background/pages/adm_men_modify.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自adm_mem.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			WebempadminService empSvc = new WebempadminService();

Integer empID = Integer.valueOf(req.getParameter("empID").trim());

String empName = req.getParameter("empName").trim();
				String empNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_\s)]{2,10}$";
				if (empName == null || empName.length() == 0) {
					errorMsgs.put("empName","員工姓名: 請勿空白");
				} else if(!empName.matches(empNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("empName","員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
String empPassword = req.getParameter("empPassword").trim();
				if (empPassword == null || empPassword.trim().length() == 0) {
					errorMsgs.put("empPassword","密碼請勿空白");
				}
								
				java.sql.Date empArriveDate = empSvc.getOneEmp(empID).getEmpArriveDate();
				try {
					empArriveDate = java.sql.Date.valueOf(req.getParameter("empArriveDate").trim());
				} catch (IllegalArgumentException e) {
					empArriveDate=new java.sql.Date(System.currentTimeMillis());
				}

String empAdminAuthorization = req.getParameter("empAdminAuthorization").trim();
				if (empAdminAuthorization == null || empAdminAuthorization.trim().length() == 0) {
					errorMsgs.put("empAdminAuthorization","權限請勿空白");
				}
				
				// 照片
				byte[] empBlob = null;
InputStream in =req.getPart("empBlob").getInputStream();				
				if(in.available() == 0) { //取原本資料內的照片
					empSvc = new WebempadminService();
					empBlob = empSvc.getOneEmp(empID).getEmpBlob();
					
				} else {
//		            errorMsgs.put("empImage", "圖片格式不支援");
					empBlob = new byte[in.available()];
					in.read(empBlob);
					in.close();
		        }

				
				WebempadminVO empVO = new WebempadminVO();
				empVO.setEmpID(empID);
				empVO.setEmpName(empName);
				empVO.setEmpPassword(empPassword);
				empVO.setEmpArriveDate(empArriveDate);
				empVO.setEmpAdminAuthorization(empAdminAuthorization);
				empVO.setEmpBlob(empBlob);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/background/pages/adm_men_modify.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				empSvc = new WebempadminService();
				empVO = empSvc.updateEmp(empID, empName, empPassword,empArriveDate, empAdminAuthorization, empBlob );
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/emp/listOneEmp.jsp";
				String url = "/background/pages/adm_men.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

		
		
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求 
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
String empName = req.getParameter("empName");
				String empNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empName == null || empName.trim().length() == 0) {
					errorMsgs.put("empName","員工姓名: 請勿空白");
				} else if(!empName.trim().matches(empNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("empName","員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
String empPassword = req.getParameter("empPassword").trim();
				if (empPassword == null || empPassword.trim().length() == 0) {
					errorMsgs.put("empPassword","密碼請勿空白");
				}
				
				java.sql.Date empArriveDate = null;
			
				//日期轉換			
				String empArriveDateStr = req.getParameter("empArriveDate");
//				java.sql.Date empArriveDate = null;

				try {
				    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				    java.util.Date parsedDate = dateFormat.parse(empArriveDateStr);
				    empArriveDate = new java.sql.Date(parsedDate.getTime());
				} catch (ParseException e) {
				    errorMsgs.put("empArriveDate", "日期格式不正確");
				}
				
				// 權限角色					
String empAdminAuthorization = req.getParameter("empAdminAuthorization").trim();
				if (empAdminAuthorization == null || empAdminAuthorization.trim().length() == 0) {
					errorMsgs.put("empAdminAuthorization","權限請勿空白");
				}
			
				// 照片
				InputStream in =req.getPart("empBlob").getInputStream();
				byte[] empBlob = null;
				if(in.available() != 0) {
					empBlob = new byte[in.available()];
					in.read(empBlob);
					in.close();
				} else {
		            errorMsgs.put("empImage", "圖片格式不支援");
		        }


WebempadminVO empVO = new WebempadminVO();
				empVO.setEmpName(empName);
				empVO.setEmpPassword(empPassword);
				empVO.setEmpArriveDate(empArriveDate);
				empVO.setEmpAdminAuthorization(empAdminAuthorization);
				empVO.setEmpBlob(empBlob);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("empVO", empVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/background/pages/adm_men_add.jsp");
					
					failureView.forward(req, res);
					
					return;
				}
							
				/***************************2.開始新增資料***************************************/
				WebempadminService empSvc = new WebempadminService();
				empVO = empSvc.addEmp(empName, empPassword, empArriveDate, empAdminAuthorization, empBlob);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				String url = "/emp/listAllEmp.jsp";
				String url = "/background/pages/adm_men.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		//只要做停權動作
		if ("suspend".equals(action)) {  // 來自adm_mem.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer empID = Integer.valueOf(req.getParameter("empID"));
				
				/***************************2.開始停權，轉換狀態**************************************/
				WebempadminService empSvc = new WebempadminService();
				empSvc.suspendEmp(empID);
				
				/***************************3.停權完成,準備轉交(Send the Success view)***********/								
				String url = "/background/pages/adm_men.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}
		
		//取消
		if ("cancel".equals(action)) {
			String url = "/background/pages/adm_men.jsp"; // 指定的頁面路徑
            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, res);
		}
		
	}
	
	
	
	private String getSubmittedFileName(Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	

}
