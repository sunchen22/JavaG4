package com.dinerinfo.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;


public class DinerInfoServletD extends HttpServlet  {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		=========================== 註冊 =============================
		
	       if ("insert".equals(action)) { // 來自register-form.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String dinerName = req.getParameter("dinerName");
					String dinerNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,15}$";
					if (dinerName == null || dinerName.trim().length() == 0) {
						errorMsgs.add("商家名稱 : 請勿空白");
					} else if(!dinerName.trim().matches(dinerNameReg)) {
						errorMsgs.add("商家名稱 : 只能是中、英文字母、數字, 且長度必需在1到15之間");
		            }
					
//					密碼預設註冊時為EMAIL通知 , 這裡就不寫
//					String dinerPassword = req.getParameter("dinerPassword");
//					String dinerPasswordReg = "^[(a-zA-Z0-9_)]{6,10}$";
//					if (dinerPassword == null || dinerPassword.trim().length() == 0) {
//						errorMsgs.add("密碼 : 請勿空白");
//					} else if(!dinerPassword.trim().matches(dinerPassword)) { 
//						errorMsgs.add("密碼 : 只能是英文字母、數字和_ , 且長度必需在6到10之間");
//		            }
					
					String dinerTaxID = req.getParameter("dinerTaxID");
					String dinerTaxIDReg = "^[(0-9)]{8}$";
					if (dinerTaxID == null || dinerTaxID.trim().length() == 0) {
						errorMsgs.add("商家統編 : 請勿空白");
					} else if(!dinerTaxID.trim().matches(dinerTaxIDReg)) { 
						errorMsgs.add("商家統編 : 只能是8個數字");
		            }
					
					
					String dinerContact = req.getParameter("dinerContact");
					String chineseNameReg = "^[\u4e00-\u9fa5]+$";  // 只能是中文，不含空格
					String englishNameReg = "^[a-zA-Z]+(\\s[a-zA-Z]+)?$";  // 只能是英文，允許一個空格

					if (dinerContact == null || dinerContact.trim().length() == 0) {
					    errorMsgs.add("聯絡人姓名 : 請勿空白");
					} else {
					    if (dinerContact.matches(chineseNameReg)) {
					        // 中文名稱，不含空格
					    } else if (dinerContact.matches(englishNameReg)) {
					        // 英文名稱，允許一個空格
					    } else {
					        errorMsgs.add("聯絡人姓名 : 中文名稱不可有空格，英文名稱可有一個空格分隔姓與名");
					    }
					}

					String dinerPhone = req.getParameter("dinerPhone");
					String dinerPhoneReg = "^[(0-9)]{2,10}$";
					if (dinerPhone == null || dinerPhone.trim().length() == 0) {
						errorMsgs.add("商家手機號碼 : 請勿空白");
					} else if(!dinerPhone.trim().matches(dinerPhoneReg)) { 
						errorMsgs.add("商家手機號碼 : 只能是10個數字");
		            }
					
					
					String job = req.getParameter("job").trim();
					if (job == null || job.trim().length() == 0) {
						errorMsgs.add("職位請勿空白");
					}
					
					java.sql.Date hiredate = null;
					try {
						hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
					} catch (IllegalArgumentException e) {
						hiredate=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					
					Double sal = null;
					try {
						sal = Double.valueOf(req.getParameter("sal").trim());
					} catch (NumberFormatException e) {
						sal = 0.0;
						errorMsgs.add("薪水請填數字.");
					}
					
					Double comm = null;
					try {
						comm = Double.valueOf(req.getParameter("comm").trim());
					} catch (NumberFormatException e) {
						comm = 0.0;
						errorMsgs.add("獎金請填數字.");
					}
					
					Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());

					EmpVO empVO = new EmpVO();
					empVO.setEname(ename);
					empVO.setJob(job);
					empVO.setHiredate(hiredate);
					empVO.setSal(sal);
					empVO.setComm(comm);
					empVO.setDeptno(deptno);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
	req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/emp/addEmp.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					EmpService empSvc = new EmpService();
					empVO = empSvc.addEmp(ename, job, hiredate, sal, comm, deptno);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/emp/listAllEmp.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
			}
	}

}
