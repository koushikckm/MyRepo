package com.mazda.common.interceptors;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.mazda.common.utility.Utility;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Common Interceptor to control common filter applied on application
 * 
 * @author Pradeep.Sharma
 * @since 03-01-2013
 * @version 1.0
 */
public class CommonInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation invocation) throws Exception {
		
		final ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		String result 	= "";
		
		/* pre-processing */
		try {
			//Configuring server base url start
			String requestURL = request.getRequestURL().toString().toLowerCase();
			String isProxyApply="yes";
			String serverBaseUrl = null;
			
			if(requestURL.contains("qa.mymazda.com") || requestURL.contains("stg.mymazda.com")){
				serverBaseUrl=Utility.getProperties("env.properties").getProperty("baseUrlQa");
			}else if(requestURL.contains("test.mymazda.com") || requestURL.contains("approval.mymazda.com")){
				serverBaseUrl=Utility.getProperties("env.properties").getProperty("baseUrlTest");
			}else{
				serverBaseUrl=Utility.getProperties("env.properties").getProperty("baseUrlDefault");
				isProxyApply="no";
			}
			
			String envParam=request.getParameter("env")!=null ? request.getParameter("env"):"";
			String proxySetting=request.getParameter("prx")!=null ? request.getParameter("prx"):"";
			

			//Environment setting start
			if(!envParam.equals("")) {	
				if(envParam.equalsIgnoreCase("test"))
					serverBaseUrl=Utility.getProperties("env.properties").getProperty("baseUrlTest");
				else if(envParam.equalsIgnoreCase("qa"))
					serverBaseUrl=Utility.getProperties("env.properties").getProperty("baseUrlQa");
				else
					serverBaseUrl=Utility.getProperties("env.properties").getProperty("baseUrlProd");
			}
			request.getSession().setAttribute("base_env_url", serverBaseUrl);
			//Environment setting end			
			
			//Proxy setting start
			if(!proxySetting.equals("")) {
				if(proxySetting.equalsIgnoreCase("yes"))
					isProxyApply="yes";
				else if(proxySetting.equalsIgnoreCase("no"))
					isProxyApply="no";
			}else{
				if(request.getSession().getAttribute("prox_set") == null) {
					String isEnable = Utility.getProperties("env.properties").getProperty("enableProxy");
					if(isEnable != null && isEnable.equalsIgnoreCase("yes")) {
						isProxyApply="yes";
					}else{
						isProxyApply="no";
					}
				}else {
					isProxyApply=(String) request.getSession().getAttribute("prox_set");
				}
			}
			request.getSession().setAttribute("prox_set", isProxyApply);
			//Proxy setting end			
			//Configuring server base url end			
			//String serverName = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			String contextPath = "mazda";
			request.getSession().setAttribute("contextPath", contextPath);

			/* call action or next interceptor */
			result = invocation.invoke();

			/* post-processing */
			
			//Set State Codes
			String stateCodes = Utility.getProperties("env.properties").getProperty("STATE_CODES");
			if(stateCodes != null)
				request.getSession().setAttribute("STATE_CODES", stateCodes);
			
		} catch (Exception exception) { }
		
		return result;
	}

}
