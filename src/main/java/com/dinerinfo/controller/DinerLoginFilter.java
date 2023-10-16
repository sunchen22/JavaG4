//package com.dinerinfo.controller;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
////確保商家使用者在訪問需要身份驗證的網頁時已經登入
//@WebFilter(filterName = "dinerLoginFilter", 
//		   urlPatterns = {"/dinerbackground/pages/Team/dashboard/*",
//				   		  "/dinerbackground/pages/Team/help/*",
//				   		  "/dinerbackground/pages/Team/ord_query/*",
//				   		  "/dinerbackground/pages/Team/p_list/*",
//				   		  "/dinerbackground/pages/Team/shelve/*"})
//public class DinerLoginFilter implements Filter {
//   
//	//	config 用來儲存此過濾器的設定資訊
//	private FilterConfig config;
//
//	@Override
//	public void init(FilterConfig config) throws ServletException {
//		this.config = config ;
//	}
//
//
//	@Override
//	public void destroy() {
//		// 清理 config 裡面留存的資訊
//		config = null;
//	}
//	
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  
//			throws IOException, ServletException {
//		// 為了保持 Servlet 過濾器的通用性，所以這裡用了父類別 ServletRequest、ServletResponse
//		// 這樣，同一個過濾器可以被用於不同的 Servlet 類型，不僅僅是基於 HTTP 的 Servlets
//		
//		// 強制轉型
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse res = (HttpServletResponse) response;
//		
//		// 取得 session
//		HttpSession session = req.getSession();
//		// 從 session 判斷此 diner 是否登入過
//		Object account = session.getAttribute("account");
//		// account 是在成功登入後，servlet 有將資料庫既有dinerInfo物件標註進去 session 的屬性
//		if(account == null) {
//			// 代表完全沒登錄過
//			session.setAttribute("location" , req.getRequestURI());
//			// 將當前的URI（也就是使用者原本試圖訪問的頁面）保存到session的"location"屬性中
//			// 以便在成功登入後返回此頁面
//			
//			res.sendRedirect(req.getContextPath() + "/dinerbackground/pages/Team/dashboard/login-form.jsp");
//			// 將使用者重定向到登入頁面
//			return;
//			// 提早結束doFilter方法的執行，不繼續處理這次的請求
//		} else {
//			chain.doFilter(request, response);
//			// 如果使用者已登入（account不為null），則繼續處理請求
//			// 將請求和回應傳遞給下一個過濾器或目標資源
//		}
//		
//		
//		
//	}
//	
//
//}
