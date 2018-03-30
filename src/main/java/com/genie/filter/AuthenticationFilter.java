package com.genie.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*author:Genie
 *date:2017年5月29日
 *配置spring security之后不用
**/
public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		System.out.println("in filter");
		if(session == null){
			System.out.println("session is null");
		}
		if(session != null && session.getAttribute("username") == null){
			System.out.println("当前session：" + session.getId());
			((HttpServletResponse) response).sendRedirect("/GenieProject/html/login.html");
			System.out.println("redirect!");
		}else{
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
