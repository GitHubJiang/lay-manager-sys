package com.lay.shop.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SpringWebUtil {

	/**
	 * request的获取.
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = null;
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if(requestAttributes != null) {
			request = ((ServletRequestAttributes)requestAttributes).getRequest();
		}
		
		return request;
	}
}