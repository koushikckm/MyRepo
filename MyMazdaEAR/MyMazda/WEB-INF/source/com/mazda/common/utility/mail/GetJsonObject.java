package com.mazda.common.utility.mail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.mazda.common.utility.Utility;

public class GetJsonObject {
	
	static final Logger log = Logger.getLogger(GetJsonObject.class); 
	
	public static String getWebServceObj(String url , HttpServletRequest request) {
		log.debug(">> Entering getWebserviceObj");
		try{
		HttpURLConnection connection=null;
		
		//Proxy config start
		String isEnable = (String)request.getSession().getAttribute("prox_set");
		isEnable = "no";
		if(isEnable != null && isEnable.equalsIgnoreCase("yes"))
		{
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxyarray.mazdausa.com",80));			
		connection = (HttpURLConnection) new URL(url).openConnection(proxy);
		}
		else{
			connection = (HttpURLConnection) new URL(url).openConnection();	
			//**"Proxy disabled..."+url);
		}
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3)");

		//Proxy config end
		//**"getWebServceObj > Proxy enabled?"+isEnable+" URL - "+url);

		BufferedReader	rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null){
			System.out.println("Line "+ line);
			sb.append(line + '\n');
		}
		String response = sb.toString();
		//**"Resp ="+response);
		log.debug(">> Exeting getWebserviceObj with response");
		return response;
	}catch (Exception e) {
		//**"Error in get "+e.getMessage());
		log.error("Exception in getWebServceObj : "+ ExceptionUtils.getFullStackTrace(e));
		return "{\"error\" : \"Requset failed\"}";
	}
		
	}
	
	public static String getWebServceObjWithCustomHeaders(String url , HttpServletRequest request) {
		log.debug(">> Entering getWebServceObjWithCustomHeaders");
		try{
		HttpURLConnection connection=null;
		
		//Proxy config start
		String isEnable = (String)request.getSession().getAttribute("prox_set");
		isEnable = "no";
		if(isEnable != null && isEnable.equalsIgnoreCase("yes"))
		{
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxyarray.mazdausa.com",80));			
		connection = (HttpURLConnection) new URL(url).openConnection(proxy);
		}
		else{
			connection = (HttpURLConnection) new URL(url).openConnection();	
			//**"Proxy disabled..."+url);
		}
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3)");
		connection.setRequestProperty("requestor", "web");
		connection.setRequestProperty("requestid", "1");
		connection.setRequestProperty("version", request.getHeader("User-Agent"));
		
		//Proxy config end
		//**"getWebServceObj > Proxy enabled?"+isEnable+" URL - "+url);

		
		BufferedReader	rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null){
			sb.append(line + '\n');
		}
		String response = sb.toString();
		//**"Resp ="+response);
		log.debug(">> Exeting getWebServceObjWithCustomHeaders with response");
		return response;
	}catch (Exception e) {
		//**"Error in get "+e.getMessage());
		e.printStackTrace();
		log.error("Exception in getWebServceObj : "+ ExceptionUtils.getFullStackTrace(e));
		return "{\"error\" : \"Requset failed\"}";
	}
		
	}
	
	public static StringBuffer getWebServceObjSb(String url , HttpServletRequest request) {
		log.debug(">> Entering getWebserviceObjSb");
		try{
		HttpURLConnection connection=null;
		Long proxyTime = new Date().getTime();
		//#("Time Captured On proxy Setting ======"+ proxyTime); 
		//Proxy config start
		String isEnable = (String)request.getSession().getAttribute("prox_set");
		if(isEnable != null && isEnable.equalsIgnoreCase("yes"))
		{
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxyarray.mazdausa.com",80));			
		connection = (HttpURLConnection) new URL(url).openConnection(proxy);
		}
		else{
			connection = (HttpURLConnection) new URL(url).openConnection();	
			//**"Proxy disabled..."+url);
		}
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3)");
		//Proxy config end
		//**"getWebServceObj > Proxy enabled?"+isEnable+" URL - "+url);

		
		BufferedReader	rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		StringBuffer sbb = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null){
			sbb.append(line + '\n');
		}
	//	StringBuffer response = sb.toString()
		//**"Resp ="+response);
	//	return response;
		log.debug(">> Exeting getWebserviceObjSb with response");
		return sbb;
	}catch (Exception e) {
		//**"Error in get "+e.getMessage());
		log.error("Exception in getWebServceObjSb : "+ ExceptionUtils.getFullStackTrace(e));
	//	return "Requset failed";
		return null;
	}
		
		
	}

	public static String getWebServceObj(String url) {
		log.debug(">> Entering getWebserviceObj");
		try{
		HttpURLConnection connection=null;
		
		//Proxy config start
		String isEnable = Utility.getProperties("env.properties").getProperty("enableProxy");
		if(isEnable != null && isEnable.equalsIgnoreCase("yes"))
		{
		//**"Proxy enabled..."+url);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxyarray.mazdausa.com",80));			
		connection = (HttpURLConnection) new URL(url).openConnection(proxy);
		}
		else{
			connection = (HttpURLConnection) new URL(url).openConnection();	
		}
		//Proxy config end
		
		BufferedReader	rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null){
			sb.append(line + '\n');
		}
		String response = sb.toString();
		//**"Resp ="+response);
		log.debug(">> Exeting getWebserviceObj with response");
		return response;
	}catch (Exception e) {
		log.error("Exception in getWebServceObj : "+ ExceptionUtils.getFullStackTrace(e));
		return "{\"error\" : \"Requset failed\"}";
	}
		
	}
	
	
	public static void main(String[] arg)
	{
		
		//#("test ......... "+"\u2605".toUpperCase());
	}
	
}
