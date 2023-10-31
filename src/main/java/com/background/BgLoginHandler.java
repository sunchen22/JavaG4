package com.background;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webempadmin.model.WebempadminService;
import com.webempadmin.model.WebempadminVO;

//@WebServlet("/bglogin") 
public class BgLoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 【改成與資料庫搜尋比對】
	protected boolean allowUser(String account, String password) {
		WebempadminService empSvc = new WebempadminService();
		Map<String, String> map = empSvc.getOnePassword(account);
		Map<String, Integer> mapstatus = empSvc.getOneStatus(account);

		if (map.containsKey(account) && mapstatus.get(account)==0) {
			System.out.println(mapstatus.get(account));
			String bgpassword = (String) map.get(account);
			if (bgpassword.equals(password)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("Big5");
		res.setContentType("text/html; charset=Big5");

		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String action = req.getParameter("action");
		System.out.println(account);
		System.out.println(password);

// 【檢查該帳號 , 密碼是否有效】
		if (action.equals("login")) {
			if (!allowUser(account, password)) { // 【帳號 , 密碼無效時】// 重新登入頁面
				res.sendRedirect(req.getContextPath() + "/background/login_failed.jsp");
				return;
			} else { // 【帳號 , 密碼有效時, 才做以下工作】

				
				HttpSession session = req.getSession();
				session.setAttribute("account", account); // *工作1: 才在session內做已經登入過的標識
				
				try {
					String location = (String) session.getAttribute("location");
					
					if (location != null) {
						session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						System.out.println(location);
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {
				}
				res.sendRedirect(req.getContextPath() + "/background/login_succss.jsp");
			}
		}
//			======== 登出功能 ========
		if ("signout".equals(action)) {
			System.out.println("我有再signout這");
			HttpSession session = req.getSession();
			session.removeAttribute(account);
			req.getSession().invalidate();
			res.sendRedirect(req.getContextPath() + "/background/signout.jsp");
			
		}

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}