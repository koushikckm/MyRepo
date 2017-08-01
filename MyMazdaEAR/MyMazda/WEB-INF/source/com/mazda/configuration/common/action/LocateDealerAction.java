package com.mazda.configuration.common.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mazda.common.utility.mail.GetJsonObject;
import com.mazda.configuration.common.transferobject.LocateDealerTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LocateDealerAction extends ActionSupport implements ModelDriven<LocateDealerTO>, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LocateDealerTO locateDealerTO;
	List<LocateDealerTO> dealerList = new ArrayList<LocateDealerTO>();
	List<LocateDealerTO> dealerListByName = new ArrayList<LocateDealerTO>();
	List<LocateDealerTO> dealerListByState = new ArrayList<LocateDealerTO>();
	List<LocateDealerTO> dealerListLocation = new ArrayList<LocateDealerTO>();
	final ActionContext actionContext 	= ActionContext.getContext();
	private HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
	private HttpServletResponse servletResponse;
	public LocateDealerTO getModel() {
		locateDealerTO = new LocateDealerTO();
		return locateDealerTO;
	}
	
	public String execute() {
		try{ 
			/*String url = "http://mobilegarage.mazdausa.com/MyMazdaGarage/client/search/dealer/zip/92692";*/
			/* String url = "http://mobilegarage.mazdausa.com/MyMazdaGarage/client/search/dealer/name/mazda";*/


			// http://mobilegarage.mazdausa.com/MyMazdaGarage/client/search/dealer/longlat/{longitude}/{latiude}/{distance}/{mazdaspeedonly}/{count}/{startFrom}/		 
			/**This code is done for time of registration of prefered dealer
			 * which shows in LocateDealer.jsp.
			 * 
			 */
			request.getSession().setAttribute("addPreferedDealer", null);
			request.getSession().setAttribute("registerPreferedDealer", null);
			/**
			 * Search Dealer By zip
			 */
			//String url = request.getSession().getAttribute("base_env_url")+"search/dealer/"+"zip/"+locateDealerTO.getDealerSearch();
			String url = "http://mobilegarage.mazdausa.com/MyMazdaGarage/client/"+"search/dealer/"+"zip/"+locateDealerTO.getZip();
			String json = GetJsonObject.getWebServceObj(url,request);
			JSONObject jsonObj = new JSONObject(json);
			/**
			 * Convert  json into arraylist
			 */
			JSONArray dealerArray = new JSONArray();
			if(jsonObj.getString("status").equalsIgnoreCase("Success")){
				System.out.println("*****IFF******"+dealerArray.length());
				dealerArray = jsonObj.getJSONArray("dealers");
				for(int i =0; i<dealerArray.length();i++){
					System.out.println("*****IFF******"+dealerArray.length());
					JSONObject	jsonDealerObj = dealerArray.getJSONObject(i);
					LocateDealerTO locateDealer = new LocateDealerTO();
					locateDealer.setName(jsonDealerObj.getString("name"));
					locateDealer.setLocation(jsonDealerObj.getString("location"));
					locateDealer.setId(jsonDealerObj.getInt("id"));
					if(jsonDealerObj.getString("expServiceFlag").equalsIgnoreCase("Y")){
						locateDealer.setExpServiceFlag("Mazda Express Service");
					}else{
						locateDealer.setExpServiceFlag(null);
					}
					locateDealer.setDistance(jsonDealerObj.getDouble("distance"));
					JSONObject jsonAddressObj = jsonDealerObj.getJSONObject("address");
					locateDealer.setState(jsonAddressObj.getString("state"));
					locateDealer.setCity(jsonAddressObj.getString("city"));
					locateDealer.setStreet(jsonAddressObj.getString("street"));
					locateDealer.setZip(jsonAddressObj.getString("zip"));
					String phone = jsonAddressObj.getString("phone").trim();
					if(phone !=null && !phone.equalsIgnoreCase("")){
						char[] phoneArray = phone.toCharArray();
						String phoneNumber = "("+phoneArray[0]+phoneArray[1]+phoneArray[2]+") "+phoneArray[3]+phoneArray[4]+phoneArray[5]+"-"+phoneArray[6]+phoneArray[7]+phoneArray[8]+phoneArray[9];
						locateDealer.setPhone(phoneNumber);
					}
					if(jsonDealerObj.has("serviceUrl")){
						locateDealer.setServiceUrl(jsonDealerObj.getString("serviceUrl"));
					}else{
						locateDealer.setServiceUrl(null);
					}
					dealerList.add(locateDealer);
				}
			}
			/*
			 * search Dealer By Name
			 * */
			
			else{
				//url = request.getSession().getAttribute("base_env_url")+"search/dealer/"+"name/"+locateDealerTO.getDealerSearch();
				System.out.println("********"+locateDealerTO.getDealerSearch());
				 url = "http://mobilegarage.mazdausa.com/MyMazdaGarage/client/"+"search/dealer/"+"name/"+locateDealerTO.getDealerSearch();
				json = GetJsonObject.getWebServceObj(url,request);
				jsonObj = new JSONObject(json);
				/**
				 * Convert  json into arraylist
				 */ 
				dealerArray = new JSONArray();
				if(jsonObj.getString("status").equalsIgnoreCase("Success") && jsonObj.getInt("searchcount")>0){
					dealerArray = jsonObj.getJSONArray("dealers");
					System.out.println("***********"+dealerArray.length());
					if(jsonObj.getString("status").equalsIgnoreCase("Success")){
						for(int i =0; i<dealerArray.length();i++){
							System.out.println("***********"+dealerArray.length());
							JSONObject	jsonDealerObj = dealerArray.getJSONObject(i);
							LocateDealerTO locateDealer = new LocateDealerTO();
							locateDealer.setName(jsonDealerObj.getString("name"));
							locateDealer.setLocation(jsonDealerObj.getString("location"));
							locateDealer.setId(jsonDealerObj.getInt("id"));
							locateDealer.setDistance(jsonDealerObj.getDouble("timeDiff"));
							JSONObject jsonAddressObj = jsonDealerObj.getJSONObject("address");
							if(jsonDealerObj.getString("expServiceFlag").equalsIgnoreCase("Y")){
								locateDealer.setExpServiceFlag("Mazda Express Service");
							}else{
								locateDealer.setExpServiceFlag(null);
							}
							locateDealer.setState(jsonAddressObj.getString("state"));
							locateDealer.setCity(jsonAddressObj.getString("city"));
							locateDealer.setStreet(jsonAddressObj.getString("street"));
							locateDealer.setZip(jsonAddressObj.getString("zip"));
							String phone = jsonAddressObj.getString("phone").trim();
							if(phone !=null && !phone.equalsIgnoreCase("")){
								char[] phoneArray = phone.toCharArray();
								String phoneNumber = "("+phoneArray[0]+phoneArray[1]+phoneArray[2]+") "+phoneArray[3]+phoneArray[4]+phoneArray[5]+"-"+phoneArray[6]+phoneArray[7]+phoneArray[8]+phoneArray[9];
								locateDealer.setPhone(phoneNumber);
							}
							if(jsonDealerObj.has("serviceUrl")){
								System.out.println("IF");
								locateDealer.setServiceUrl(jsonDealerObj.getString("serviceUrl"));
							}else{
								System.out.println("Else");
								locateDealer.setServiceUrl(null);
							}
							dealerListByName.add(locateDealer);
							dealerList.add(locateDealer);
						}
						
					}
				}
			}   
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}
/*	************************************/
	
	 	// http://mobilegarage.mazdausa.com/MyMazdaGarage/client/search/dealer/
	 
		// http://mobilegarage.mazdausa.com/MyMazdaGarage/client/search/dealer/longlat/77.3689727/28.5550469/50/false
	
	public String searchByLocation() {
		try {
			String url = request.getSession().getAttribute("base_env_url")+"search/dealer/"+"longlat/"+locateDealerTO.getLongitude()+"/"+locateDealerTO.getLatitude();
		    String json = GetJsonObject.getWebServceObj(url,request);
		    JSONObject jsonObj = new JSONObject(json);
		    /**
		     * Convert  json into arraylist
		     */
		    JSONArray dealerArray = new JSONArray();
		    if(jsonObj.getString("status").equalsIgnoreCase("Success") && jsonObj.getInt("searchcount")>0){
		    dealerArray = jsonObj.getJSONArray("dealers");
		    for(int i =0; i<dealerArray.length();i++){
		    	JSONObject	jsonDealerObj = dealerArray.getJSONObject(i);
		    	LocateDealerTO locateDealer = new LocateDealerTO();
		    	locateDealer.setName(jsonDealerObj.getString("name"));
		    	locateDealer.setLocation(jsonDealerObj.getString("location"));
		    	locateDealer.setId(jsonDealerObj.getInt("id"));
		    	locateDealer.setDistance(jsonDealerObj.getDouble("distance"));
		    	if(jsonDealerObj.getString("expServiceFlag").equalsIgnoreCase("Y")){
		   	    	locateDealer.setExpServiceFlag("Mazda Express Service");
		   	    	}else{
		   	    		locateDealer.setExpServiceFlag(null);
		   	    	}
		    	JSONObject jsonAddressObj = jsonDealerObj.getJSONObject("address");
		    	locateDealer.setState(jsonAddressObj.getString("state"));
		    	locateDealer.setCity(jsonAddressObj.getString("city"));
		    	locateDealer.setStreet(jsonAddressObj.getString("street"));
		    	locateDealer.setZip(jsonAddressObj.getString("zip"));
		    	String phone = jsonAddressObj.getString("phone").trim();
	    		if(phone !=null && !phone.equalsIgnoreCase("")){
	    		char[] phoneArray = phone.toCharArray();
	    		String phoneNumber = "("+phoneArray[0]+phoneArray[1]+phoneArray[2]+") "+phoneArray[3]+phoneArray[4]+phoneArray[5]+"-"+phoneArray[6]+phoneArray[7]+phoneArray[8]+phoneArray[9];
	    		locateDealer.setPhone(phoneNumber);
	    		}
	    		if(jsonDealerObj.has("serviceUrl")){
	    			locateDealer.setServiceUrl(jsonDealerObj.getString("serviceUrl"));
	    		}else{
	   	    		locateDealer.setServiceUrl(null);
	   	    	}
	    		dealerListLocation.add(locateDealer);
		    }
		   }
	    if(dealerListLocation.size()==0){
			this.getServletResponse().getWriter().print("no");
			return null;
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	
/************/
	public String searchByName() {
		try {
			String url = request.getSession().getAttribute("base_env_url")+"search/dealer/"+"name/"+locateDealerTO.getName();
			String json = GetJsonObject.getWebServceObj(url,request);
			JSONObject jsonObj = new JSONObject(json);
			/**
			 * Convert  json into arraylist
			 */
			JSONArray dealerArray = new JSONArray();
			if(jsonObj.getString("status").equalsIgnoreCase("Success") && jsonObj.getInt("searchcount")>0){
				dealerArray = jsonObj.getJSONArray("dealers");
				for(int i =0; i<dealerArray.length();i++){
					JSONObject	jsonDealerObj = dealerArray.getJSONObject(i);
					LocateDealerTO locateDealer = new LocateDealerTO();
					locateDealer.setName(jsonDealerObj.getString("name"));
					locateDealer.setLocation(jsonDealerObj.getString("location"));
					locateDealer.setId(jsonDealerObj.getInt("id"));
					locateDealer.setDistance(jsonDealerObj.getDouble("timeDiff"));
					JSONObject jsonAddressObj = jsonDealerObj.getJSONObject("address");
					if(jsonDealerObj.getString("expServiceFlag").equalsIgnoreCase("Y")){
						locateDealer.setExpServiceFlag("Mazda Express Service");
					}else{
						locateDealer.setExpServiceFlag(null);
					}
					locateDealer.setState(jsonAddressObj.getString("state"));
					locateDealer.setCity(jsonAddressObj.getString("city"));
					locateDealer.setStreet(jsonAddressObj.getString("street"));
					locateDealer.setZip(jsonAddressObj.getString("zip"));
					String phone = jsonAddressObj.getString("phone").trim();
					if(phone !=null && !phone.equalsIgnoreCase("")){
						char[] phoneArray = phone.toCharArray();
						String phoneNumber = "("+phoneArray[0]+phoneArray[1]+phoneArray[2]+") "+phoneArray[3]+phoneArray[4]+phoneArray[5]+"-"+phoneArray[6]+phoneArray[7]+phoneArray[8]+phoneArray[9];
						locateDealer.setPhone(phoneNumber);
					}
					if(jsonDealerObj.has("serviceUrl")){
						locateDealer.setServiceUrl(jsonDealerObj.getString("serviceUrl"));
					}else{
						locateDealer.setServiceUrl(null);
					}
					dealerListByName.add(locateDealer);
				}
			}
			if(dealerListByName.size()==0){
				this.getServletResponse().getWriter().print("no");
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}


		return SUCCESS;
	}
	
	
	public String searchByStateCity() {
		try {
			String url = request.getSession().getAttribute("base_env_url")+"search/dealer/"+"statecity/"+locateDealerTO.getState()+"/"+locateDealerTO.getCity();
			String json = GetJsonObject.getWebServceObj(url,request);
			JSONObject jsonObj = new JSONObject(json);
			/**
			 * Convert  json into arraylist
			 */
			JSONArray dealerArray = new JSONArray();
			if(jsonObj.getString("status").equalsIgnoreCase("Success") && jsonObj.getInt("searchcount")>0){
				dealerArray = jsonObj.getJSONArray("dealers");
				for(int i =0; i<dealerArray.length();i++){
					JSONObject	jsonDealerObj = dealerArray.getJSONObject(i);
					LocateDealerTO locateDealer = new LocateDealerTO();
					locateDealer.setName(jsonDealerObj.getString("name"));
					locateDealer.setLocation(jsonDealerObj.getString("location"));
					locateDealer.setId(jsonDealerObj.getInt("id"));
					locateDealer.setDistance(jsonDealerObj.getDouble("distance"));
					JSONObject jsonAddressObj = jsonDealerObj.getJSONObject("address");
					if(jsonDealerObj.getString("expServiceFlag").equalsIgnoreCase("Y")){
			   	    	locateDealer.setExpServiceFlag("Mazda Express Service");
			   	    	}else{
			   	    		locateDealer.setExpServiceFlag(null);
			   	    	}
					locateDealer.setState(jsonAddressObj.getString("state"));
					locateDealer.setCity(jsonAddressObj.getString("city"));
					locateDealer.setStreet(jsonAddressObj.getString("street"));
					locateDealer.setZip(jsonAddressObj.getString("zip"));
					String phone = jsonAddressObj.getString("phone").trim();
		    		if(phone !=null && !phone.equalsIgnoreCase("")){
		    		char[] phoneArray = phone.toCharArray();
		    		String phoneNumber = "("+phoneArray[0]+phoneArray[1]+phoneArray[2]+") "+phoneArray[3]+phoneArray[4]+phoneArray[5]+"-"+phoneArray[6]+phoneArray[7]+phoneArray[8]+phoneArray[9];
		    		locateDealer.setPhone(phoneNumber);
		    		}
		    		if(jsonDealerObj.has("serviceUrl")){
		    			locateDealer.setServiceUrl(jsonDealerObj.getString("serviceUrl"));
		    		}else{
		   	    		locateDealer.setServiceUrl(null);
		   	    	}
		    		dealerListByState.add(locateDealer);
				}
			}
		 if(dealerListByState.size()==0){
				this.getServletResponse().getWriter().print("no");
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String searchByZip() {
		try {
			System.out.println("*******searchByZip");
			System.out.println("*******locateDealerTO.getZip()"+locateDealerTO.getZip());
			String zip=locateDealerTO.getZip();
			HttpSession session = request.getSession();
			session.setAttribute("zip", zip);
			//String url = request.getSession().getAttribute("base_env_url")+"search/dealer/"+"zip/"+locateDealerTO.getZip();
			String url = "http://mobilegarage.mazdausa.com/MyMazdaGarage/client/"+"search/dealer/"+"zip/"+locateDealerTO.getZip();
			String json = GetJsonObject.getWebServceObj(url,request);
			JSONObject jsonObj = new JSONObject(json);
			/**
			 * Convert  json into arraylist
			 */
			JSONArray dealerArray = new JSONArray();
			if(jsonObj.getString("status").equalsIgnoreCase("Success") && jsonObj.getInt("searchcount")>0){
				dealerArray = jsonObj.getJSONArray("dealers");
				for(int i =0; i<dealerArray.length();i++){
					JSONObject	jsonDealerObj = dealerArray.getJSONObject(i);
					LocateDealerTO locateDealer = new LocateDealerTO();
					locateDealer.setName(jsonDealerObj.getString("name"));
					locateDealer.setLocation(jsonDealerObj.getString("location"));
					locateDealer.setId(jsonDealerObj.getInt("id"));
					locateDealer.setDistance(jsonDealerObj.getDouble("distance"));
					JSONObject jsonAddressObj = jsonDealerObj.getJSONObject("address");
					if(jsonDealerObj.getString("expServiceFlag").equalsIgnoreCase("Y")){
			   	    	locateDealer.setExpServiceFlag("Mazda Express Service");
			   	    	}else{
			   	    		locateDealer.setExpServiceFlag(null);
			   	    	}
					locateDealer.setState(jsonAddressObj.getString("state"));
					locateDealer.setCity(jsonAddressObj.getString("city"));
					locateDealer.setStreet(jsonAddressObj.getString("street"));
					locateDealer.setZip(Integer.toString(jsonAddressObj.getInt("zip")));
					String phone = jsonAddressObj.getString("phone").trim();
		    		if(phone !=null && !phone.equalsIgnoreCase("")){
		    		char[] phoneArray = phone.toCharArray();
		    		String phoneNumber = "("+phoneArray[0]+phoneArray[1]+phoneArray[2]+") "+phoneArray[3]+phoneArray[4]+phoneArray[5]+"-"+phoneArray[6]+phoneArray[7]+phoneArray[8]+phoneArray[9];
		    		locateDealer.setPhone(phoneNumber);
		    		}
		    		if(jsonDealerObj.has("serviceUrl")){
		    			locateDealer.setServiceUrl(jsonDealerObj.getString("serviceUrl"));
		    		}else{
		   	    		locateDealer.setServiceUrl(null);
		   	    	}
					dealerList.add(locateDealer);
				}
			}
			System.out.println("dealerList::"+dealerList.size());
		if(dealerList.size()==0){
			this.getServletResponse().getWriter().print("no");
			return SUCCESS;
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public List<LocateDealerTO> getDealerList() {
		return dealerList;
	}

	public void setDealerList(List<LocateDealerTO> dealerList) {
		this.dealerList = dealerList;
	}

	public List<LocateDealerTO> getDealerListByName() {
		return dealerListByName;
	}

	public void setDealerListByName(List<LocateDealerTO> dealerListByName) {
		this.dealerListByName = dealerListByName;
	}

	public List<LocateDealerTO> getDealerListByState() {
		return dealerListByState;
	}

	public void setDealerListByState(List<LocateDealerTO> dealerListByState) {
		this.dealerListByState = dealerListByState;
	}

	public List<LocateDealerTO> getDealerListLocation() {
		return dealerListLocation;
	}

	public void setDealerListLocation(List<LocateDealerTO> dealerListLocation) {
		this.dealerListLocation = dealerListLocation;
	}

	public HttpServletResponse getServletResponse() {
		return servletResponse;
	}

	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}
	public String locateDealer(){
		try {
			
		} catch (Exception e) {
		}
		
		return SUCCESS;
	}
}
