package com.mazda.common.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONObject;



public class PostJsonObject {
	static final Logger log = Logger.getLogger(PostJsonObject.class); 
	
	public static String postJson(JSONObject object, String url, HttpServletRequest request) {
		String resp = "";

		try{
			HttpClient client = new DefaultHttpClient();
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
			//Setting proxy start
			log.debug("postJson > Proxy enabled?"+isEnable+" URL - "+url);
   	        
			HttpPost post = new HttpPost(url);
			post.addHeader("User-Agent", "android");

			StringEntity input = new StringEntity(object.toString());
			input.setContentType("application/json");

			post.setEntity(input);
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				resp = resp + line;
			}

		} catch (Exception e) {
			log.debug("Exception occured while posting request data - " + e);
			e.printStackTrace();
			JSONObject exceptionMess = new JSONObject();
			exceptionMess.put("status", "failure");
			exceptionMess.put("description", "Exception");
			resp = exceptionMess.toString();
		}
		log.debug("Request completed >>>" + resp);
		return resp;
	}

	public static String postJsonWithCustomHeaders(JSONObject object, String url, HttpServletRequest request) {
		String resp = "";

		try{
			HttpClient client = new DefaultHttpClient();
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
			//Setting proxy start
			log.debug("postJson > Proxy enabled?"+isEnable+" URL - "+url);
   	        
			HttpPost post = new HttpPost(url);
			post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3)");
			post.addHeader("requestor", "web");
			post.addHeader("requestid", "1");
			post.addHeader("version", request.getHeader("User-Agent"));
			
			StringEntity input = new StringEntity(object.toString());
			input.setContentType("application/json");

			post.setEntity(input);
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				resp = resp + line;
			}

		} catch (Exception e) {
			log.debug("Exception occured while posting request data - " + e);
			e.printStackTrace();
			JSONObject exceptionMess = new JSONObject();
			exceptionMess.put("status", "failure");
			exceptionMess.put("description", "Exception");
			resp = exceptionMess.toString();
		}
		log.debug("Request completed >>>" + resp);
		return resp;
	}
	
	
	public static String postJson(JSONObject object, String url) {
		String resp = "";

		try {
			HttpClient client = new DefaultHttpClient();
			
			//Setting proxy start
			String isEnable = Utility.getProperties("env.properties").getProperty("enableProxy");
			if(isEnable != null && isEnable.equalsIgnoreCase("yes"))
			{
				log.debug("Proxy enabled..."+url);
				HttpHost proxy = new HttpHost("proxyarray.mazdausa.com",80);
	   	        client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);    
			}			
			//Setting proxy start
   	        
			HttpPost post = new HttpPost(url);
			post.addHeader("User-Agent", "android");

			StringEntity input = new StringEntity(object.toString());
			input.setContentType("application/json");

			post.setEntity(input);
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				resp = resp + line;
			}

		} catch (Exception e) {
			log.debug("Exception occured while posting request data - " + e);
			e.printStackTrace();
			JSONObject exceptionMess = new JSONObject();
			exceptionMess.put("status", "failure");
			exceptionMess.put("description", "Exception");
			resp = exceptionMess.toString();
		}
		log.debug("Request completed >>>" + resp);
		return resp;
	}


	public static void main_x(String[] arg) {
		String url = "http://mobilegarage.mazdausa.com/MyMazdaGarage/client/user/login";

		JSONObject outer = new JSONObject();
		JSONObject inner = new JSONObject();

		StringEncrypter encrypto = new StringEncrypter();
		String encrptedPass = encrypto.encrypt("mazdausa");

		inner.put("userid", "carenrpt@hotmail.com");
		inner.put("password", encrptedPass/* "TzgaimRmE2EdGPBvkE5+rw==" */);

		outer.put("data", inner.toString());
		outer.put("apnstoken", "simulator");
		log.debug("json = " + outer.toString());

		String postResponse = PostJsonObject.postJson(outer, url);

		log.debug("Response = " + postResponse);

	}
}
