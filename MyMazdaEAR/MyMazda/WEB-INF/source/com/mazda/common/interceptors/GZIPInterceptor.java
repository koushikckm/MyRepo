package com.mazda.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class GZIPInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;


	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		final ActionContext actionContext = invocation.getInvocationContext();
		HttpServletResponse response 	= (HttpServletResponse) actionContext
				.get(StrutsStatics.HTTP_RESPONSE);
		HttpServletRequest request 		= (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);
		String acceptEncoding			= request.getHeader("accept-encoding");
		String result 					= "";
		if (acceptEncoding != null && acceptEncoding.indexOf("gzip") != -1) {
	        GZIPResponseWrapper wrappedResponse =  new GZIPResponseWrapper(response);
		      result = invocation.invoke();
		      wrappedResponse.finishResponse();
		}
	      return result;
	}
}