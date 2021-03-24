package com.bill.microservice.common.logging;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter
public class LoggingFilter extends OncePerRequestFilter{@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("LoggingFilter[doFilterInternal]");
		request = new ContentCachingRequestWrapper(request, 1024);
		response = new ContentCachingResponseWrapper(response);
		filterChain.doFilter(request, response);
	}
	
}
