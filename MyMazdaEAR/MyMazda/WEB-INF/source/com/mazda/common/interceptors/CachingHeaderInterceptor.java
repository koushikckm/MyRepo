package com.mazda.common.interceptors;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CachingHeaderInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		final ActionContext actionContext 	= invocation.getInvocationContext();
		HttpServletResponse response 		= (HttpServletResponse) actionContext
				.get(StrutsStatics.HTTP_RESPONSE);
		
		if (response != null) {
			response.setHeader("Cache-control", "no-cache, no-store");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");
		}
		
		/*//##("interceptor invoked .....");*/
		return invocation.invoke();
	}
}