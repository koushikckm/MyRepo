package com.mazda.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PublicInterceptor  extends AbstractInterceptor {
		
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		final ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);
		HttpSession session = request.getSession();
		if(session != null){
			String headerTab = request.getParameter("headerTab");
			if(headerTab == null) {
				headerTab = (String) session.getAttribute("headerTab");
			}
			if(headerTab == null){
				headerTab = "1";
			}
			session.setAttribute("headerTab", headerTab);
			
			if(request.getParameter("createNewTab") != null)
				session.setAttribute("createNewTab", request.getParameter("createNewTab"));
		}
		return invocation.invoke();
	}
}
