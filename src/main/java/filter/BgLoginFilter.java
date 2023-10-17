package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter("/*")
public class BgLoginFilter implements Filter {

//	private FilterConfig config;

	public void init(FilterConfig config) {
//		this.config = config;
	}

	public void destroy() {
//		config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		
//		後臺登入
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		Object accountbg = session.getAttribute("account"); //判斷session中的user
		
		if (accountbg == null) {
			session.setAttribute("location", req.getRequestURI());
			System.out.println(req.getContextPath()); // /HibernateEx-Web
			res.sendRedirect(req.getContextPath() + "/background/login.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}
}


