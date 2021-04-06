package com.bayee.political.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CrossDomainFilter implements Filter{
	
	private final int time = 20*24*60*60;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletResponse resp = (HttpServletResponse) response;
		
		resp.setHeader("Access-Control-Allow-Origin", "*");
		
		// 这个allow-headers要配为*，这样才能允许所有的请求头
		resp.setHeader("Access-Control-Allow-Headers", "*");
		
		resp.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
		
		 resp.setHeader("Access-Control-Max-Age", time+"");
		 
		 chain.doFilter(request, resp);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
