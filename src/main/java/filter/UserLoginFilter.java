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
	  if (session.getAttribute("loginUserInfo") == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
			session.setAttribute("location", req.getRequestURI()); //*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
			res.sendRedirect(req.getContextPath() + "/consumer/Login.jsp"); //*工作2 : 請該user去登入網頁(login.html) , 進行登入
			return;
		}
	  
	  chain.doFilter(req, res);
  }
}
