package com.entor.filter;

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

@WebFilter("/*")
public class SessionFilter implements Filter {

    public SessionFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp =(HttpServletResponse)response;
		/*String contextPath =  req.getContextPath();
		String uri = req.getRequestURI();
		String url = req.getRequestURL().toString();
		String ser =  req.getServletPath();*/
		String queryString =  req.getQueryString();
		if(req.getSession().getAttribute("s")==null) {
			//登录页面允许通过
			if(queryString!=null&&(queryString.contains("login"))||(queryString.contains("add"))) {
				//请求地址通过
				chain.doFilter(req, resp);
			}else {
				//未登录，无权访问(重定向 登录)
				resp.sendRedirect("StudentServlet?opType=loginForm");
			}
		}else {
			//请求地址通过
			chain.doFilter(req, resp);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
