package com.bill.microservice.common.logging;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.base.BaseWebGatewayRes;
import com.bill.microservice.base.LoggingDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {
	
	private static final int DEFAULT_MAX_PAYLOAD_LENGTH = 1024 * 512;
	
	@Autowired
	ObjectMapper mapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis());
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long)request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - startTime;
		
		String req = getRequestBody(request);
		String res = getResponseBody(response);
		
		BaseWebGatewayReq<Object> baseWebReq = mapper.readValue(req, BaseWebGatewayReq.class);
		BaseWebGatewayRes<Object> baseWebRes = mapper.readValue(res, BaseWebGatewayRes.class);
		
		LoggingDto loggingDto = LoggingDto.builder()
				.txn_seq(baseWebReq.getMwheader().getTxnseq())
//				.return_code(baseWebRes.getMwheader().getReturncode())
//				.return_desc(baseWebRes.getMwheader().getReturndesc())
				.server_host(gerServerHost())
				.remote_host(getRemoteHost(request))
				.elapse(executeTime)
				.build();
		log.info("==== start ====");
		log.info("interceptor logging [txn_seq : {}, server_host : {}, remote_host : {}, req : {}, res : {}, elapse : {} ]", 
				loggingDto.getTxn_seq(),
//				loggingDto.getReturn_code(),
//				loggingDto.getReturn_desc(),
				loggingDto.getServer_host(),
				loggingDto.getRemote_host(),
				req,
				res,
				loggingDto.getElapse()
		);
		log.info("==== end ====");
		
		super.afterCompletion(request, response, handler, ex);
	}
	
	private String getRequestBody(HttpServletRequest request) {
		ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
		if(wrapper == null) return "";
		return generatePayload(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
	}
	
	private String getResponseBody(HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
		if(wrapper == null) return "";
		
		byte[] buffer = wrapper.getContentAsByteArray();
		try {
			wrapper.copyBodyToResponse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return generatePayload(buffer, wrapper.getCharacterEncoding());
	}
	
	private String generatePayload(byte[] buffer, String characterEncoding) {
		String result = "";
		if(buffer.length > 0 && buffer.length < DEFAULT_MAX_PAYLOAD_LENGTH) {
			try {
				result = new String(buffer, 0, buffer.length, characterEncoding);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				result = "[unknow]";
			}
		}
		return result;
	}
	
	private String gerServerHost() throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		return address.getHostAddress();
	}
	
	private String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWORD-FOR");
		if(ip == null || ip.length() == 0 || StringUtils.equalsIgnoreCase("unknown", ip))
			ip = request.getHeader("Proxy-Client-IP");
		if(ip == null || ip.length() == 0 || StringUtils.equalsIgnoreCase("unknown", ip))
			ip = request.getHeader("WL-Proxy-Client-IP");
		if(ip == null || ip.length() == 0 || StringUtils.equalsIgnoreCase("unknown", ip))
			ip = request.getRemoteAddr();
		return ip;
	}
	
	private Map<String, String> convertString2Map(String mapString) {
		mapString = mapString.replace("{","").replace("}","").replace("=,", "='',");
		Map<String, String> map = Arrays.stream(mapString.split(","))
				.map(entry -> StringUtils.trim(entry).split("="))
				.collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
		return map;
	}
	
}
