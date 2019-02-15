package com.jdp.security.config;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CustomFilter{}
/*
public class CustomFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("CustomFilter::init.");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("CustomFilter::doFilter.");
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		Principal principal = request.getUserPrincipal();
		System.out.println("CustomFilter::doFilter. principal: " + principal);
	}

	@Override
	public void destroy() {
		System.out.println("CustomFilter::destroy.");
	}
}
*/
