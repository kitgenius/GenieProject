package com.genie.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * author:Genie
 * date:2017年12月16日
 * 本类用于用户访问url时对用户进行鉴权。
 * 其中会调用FilterInvocationSecurityMetadataSource的getAttributes方法来获取被拦截url所需的全部权限；
 * 再调用授权管理器AccessDecisionManager，这个授权管理器会通过spring的全局缓存SecurityContextHolder获取用户的权限信息，用户登陆时权限信息保存在全局缓存
 * 还会获取被拦截的url和被拦截url所需的全部权限，然后根据所配的策略（有：一票决定，一票否定，少数服从多数等），如果权限足够，则返回，权限不够则报错并调用权限不足页面。 
**/
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

	//配置文件注入
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	
	@Override
	public Class<?> getSecureObjectClass() {
		// TODO Auto-generated method stub
		return FilterInvocation.class;	
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		// TODO Auto-generated method stub
		return this.securityMetadataSource;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);		
	}
	
	public void invoke(FilterInvocation fi) throws IOException,ServletException {
		InterceptorStatusToken token = super.beforeInvocation(fi);//beforeInvocation方法会调用securityMetadataSource的getAttributes方法获取被拦截url所需的权限
		try{
			//继续向下执行下一个拦截器
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		}finally{
			super.afterInvocation(token, null);
		}
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

}
