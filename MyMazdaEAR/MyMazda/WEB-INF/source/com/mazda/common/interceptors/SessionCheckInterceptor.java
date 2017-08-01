package com.mazda.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * Session Check Interceptor to check session created in application
 * @author Pradeep.Sharma
 * @since 03-01-2013
 * @version 1.0
 */
public class SessionCheckInterceptor extends AbstractInterceptor {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	  public String intercept(ActionInvocation invocation) throws Exception {
		
		final ActionContext actionContext 	= invocation.getInvocationContext();
		HttpServletRequest request 			= (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);
		
		HttpSession 		session			= request.getSession(false);
		String actionResult 				= ""; 
//		Map<String, Object> sessionMap 		= actionContext.getSession();
		
		String pathInfo = request.getPathInfo();
		if(session != null && !session.isNew()) {
			actionResult = invocation.invoke();
		}else if(pathInfo.equalsIgnoreCase("/proxyLoginUser.action")){
			actionResult = invocation.invoke();
		}else if(pathInfo.equalsIgnoreCase("/resetpasswordlink.action")){
			actionResult = invocation.invoke();
		}else{
			return "sessionexpired";
		}
		
		return actionResult;
	}

}
