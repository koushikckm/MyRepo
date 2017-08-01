package com.mazda.common.interceptors;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class EncodingInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	private String encoding = "utf-8";
	@Override
	public void destroy() {
	}

	@Override
	public void init() {
		/*String encodingParam = this.getInitParameter("encoding");
		if (encodingParam != null) {
			encoding = encodingParam;
		}*/
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		final ActionContext actionContext 	= invocation.getInvocationContext();
		HttpServletRequest request 			= (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);
		request.setCharacterEncoding(encoding);
		return invocation.invoke();
	}
}