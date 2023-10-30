package filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UserLoginFilter implements Filter {
	
	public void init(FilterConfig config){
	}
	
	public void destroy(){
	}
    
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws ServletException, IOException{
	  HttpServletRequest req = (HttpServletRequest) request;
      HttpServletResponse res = (HttpServletResponse) response;
	  HttpSession session = req.getSession();
	  
	  Boolean needLogin = true;
	  if (req.getServletPath().startsWith("/GroupOrder.do")) { 
		  if (!req.getParameter("action").equals("join")) {
			  needLogin = false;
		  } else {
			  String location = req.getContextPath() + "/GroupOrder.do?action=getOne&groupOrderID=" + req.getParameter("groupOrderID");
			  session.setAttribute("location", location);
		  }
	  }
	  
	  if (needLogin && session.getAttribute("loginUserInfo") == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
			if (session.getAttribute("location") == null) {
//				session.setAttribute("location", req.getRequestURI()); //*工作1 : 同時記下目前位置 , 以便於登入成功後 , 能夠直接導至此網頁
				StringBuilder fullURL = new StringBuilder(req.getRequestURL());
				String queryString = req.getQueryString();
				if (queryString != null) {
				    fullURL.append('?').append(queryString);
				}
				session.setAttribute("location", fullURL.toString());
			}
		  	res.sendRedirect(req.getContextPath() + "/consumer/Login.jsp"); //*工作2 : 請該user去登入網頁 , 進行登入
			return;
	  }

	  chain.doFilter(req, res);
  }
}
