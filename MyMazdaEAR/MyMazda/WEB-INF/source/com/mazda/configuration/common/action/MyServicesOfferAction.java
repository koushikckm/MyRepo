package com.mazda.configuration.common.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.xwork.WordUtils;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mazda.common.utility.PostJsonObject;
import com.mazda.common.utility.mail.GetJsonObject;
import com.mazda.configuration.common.transferobject.MyServicesOfferTO;
import com.mazda.configuration.common.transferobject.RegistrationTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MyServicesOfferAction extends ActionSupport implements ModelDriven<MyServicesOfferTO>, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MyServicesOfferTO	myServicesOfferTO;
	List<MyServicesOfferTO> coupnsList = new ArrayList<MyServicesOfferTO>();
	List<MyServicesOfferTO> coupnsList1 = new ArrayList<MyServicesOfferTO>();
	final ActionContext actionContext 	= ActionContext.getContext();
	private HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST); 
	private HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
	List<RegistrationTO> loginVehicalList=null;
	private HttpServletResponse servletResponse;
	List egiftTabOnServCoupon = new ArrayList();
	
	public MyServicesOfferTO getModel() {
		myServicesOfferTO = new MyServicesOfferTO();
		return myServicesOfferTO;
	}
	
	public String servicesOffer(){
		
		try{
		@SuppressWarnings("unchecked")
		List<RegistrationTO> loginUser = (List<RegistrationTO>) request.getSession().getAttribute("loginUser");
		if(loginUser != null){
			List<RegistrationTO> vehicalsList = (List<RegistrationTO>) request.getSession().getAttribute("vehicalsList");
			if(vehicalsList==null){
				return NONE;
			}
			String customerId = loginUser.get(0).getCustomerId();
			RegistrationTO selectedVehical = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
			//Properties properties 	= Utility.getProperties("env.properties");
			String url 		= request.getSession().getAttribute("base_env_url")+"user/"+customerId+"/vehicle/"+selectedVehical.getVin()+"/coupons";
			String json = GetJsonObject.getWebServceObj(url,request);
			JSONObject jsonObj = new JSONObject(json);
			if(jsonObj.getString("status").equalsIgnoreCase("success") && jsonObj.getInt("count")==0){
				if(selectedVehical.getServiceDealerID()==null){
					addActionMessage("Please select your preferred dealer in 'Edit Vehicle' to service offers.");
					return SUCCESS;
				}

				//properties 	= Utility.getProperties("env.properties");
			    url 		= request.getSession().getAttribute("base_env_url")+"dealercoupons/dealer/"+selectedVehical.getServiceDealerID();
				//url 		= request.getSession().getAttribute("base_env_url")+"dealercoupons/dealer/23454";
				json = GetJsonObject.getWebServceObj(url,request);
				
			}
			if(json != "Requset failed"){
				jsonObj = new JSONObject(json);
			}
				
			if(jsonObj.getString("status").equalsIgnoreCase("Success") && jsonObj.getInt("count")>0){
				JSONArray jsonCoupnArrayObj = jsonObj.getJSONArray("coupons");
				List<MyServicesOfferTO> listToSetBeanList = new ArrayList<MyServicesOfferTO>();
				for(int x=0; x<jsonCoupnArrayObj.length(); x++){
					MyServicesOfferTO coupns = new MyServicesOfferTO();
					JSONObject jsonCoupnObj = jsonCoupnArrayObj.getJSONObject(x);
					if(jsonCoupnObj.has("exception")){
						coupns.setException(jsonCoupnObj.getString("exception"));
					}
		    		if(jsonCoupnObj.has("id")){
		    			coupns.setId(Integer.parseInt(jsonCoupnObj.getString("id")));
		    		}
		    		
		    		if(jsonCoupnObj.has("title")){
		    			coupns.setTitle(WordUtils.capitalizeFully(jsonCoupnObj.getString("title")));
		    		}
		    		if(jsonCoupnObj.has("services")){
		    			JSONArray jsonServicesArrayObj = jsonCoupnObj.getJSONArray("services");
		    			for(int i =0; i<jsonServicesArrayObj.length();i++){
		    				MyServicesOfferTO service = new MyServicesOfferTO();
		    				service.setService(jsonServicesArrayObj.getString(i));
		    				listToSetBeanList.add(service);
		    			}
		    		}
		    		coupns.setServicesList(listToSetBeanList);
		    		listToSetBeanList = new ArrayList<MyServicesOfferTO>();
		    		if(jsonCoupnObj.has("deal")){
		    			coupns.setDeal(jsonCoupnObj.getString("deal"));
		    		}
		    		if(jsonCoupnObj.has("descriptions")){
		    			JSONArray jsonDescriptionArrObj = jsonCoupnObj.getJSONArray("descriptions");
		    			for(int i =0; i<jsonDescriptionArrObj.length();i++){
		    				MyServicesOfferTO description = new MyServicesOfferTO();
		    				description.setDescription(jsonDescriptionArrObj.getString(i));
		    				listToSetBeanList.add(description);
		    			}
		    			coupns.setDescriptionsList(listToSetBeanList);
			    		listToSetBeanList = new ArrayList<MyServicesOfferTO>();
		    		}
		    		if(jsonCoupnObj.has("disclaimer")){
		    			coupns.setDisclaimer(jsonCoupnObj.getString("disclaimer"));
		    		}
		    		coupnsList.add(coupns);
		    		coupnsList1.add(coupns);
		    		
		    	}
		    }
		    int size = coupnsList.size();
    		request.setAttribute("noOfPages", size);
    		if(coupnsList.size() == 0){
    			addActionMessage("Sorry, there are no service coupons for the selected vehicle. Please check again later.");
    		}
			return SUCCESS;
		} else{
			return LOGIN;
		}
		}catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String myServiceOfferVehicleUpdate() {
		RegistrationAction selectedVehicleUpdate = new RegistrationAction();
		selectedVehicleUpdate.keepUpdateOfSelectedVehical(myServicesOfferTO.getVin());
		servicesOffer();
		
		int size = 0;
		request.getSession().setAttribute("egiftTab", 0);
		request.getSession().setAttribute("egiftTabOnRelPage", 0);
		request.getSession().setAttribute("egiftTabOnSelectVeh", 0);
		
		
		RegistrationTO loginUserDetail = new RegistrationTO();
		loginUserDetail =  (RegistrationTO) request.getSession().getAttribute("loginDetail");
		  
		String eGiftUrl = request.getSession().getAttribute("base_env_url")+"user/getUserEgifts/"+loginUserDetail.getCustomerId()+"/"+myServicesOfferTO.getVin();
		String json = GetJsonObject.getWebServceObj(eGiftUrl,request);
		JSONObject jsonObjEgift = new JSONObject(json);
		if(jsonObjEgift.has("status") && jsonObjEgift.getString("status").equalsIgnoreCase("Success")){
			if(jsonObjEgift.getString("count").equalsIgnoreCase("0")) {
				egiftTabOnServCoupon.add("0");
				size = egiftTabOnServCoupon.size();
				request.getSession().setAttribute("egiftTabOnServCoup", size);
			}
			else{
				request.getSession().setAttribute("egiftTabOnServCoup", 0);
			}
		}
		egiftTabOnServCoupon = null;
		
		return SUCCESS;
	}

	public String myServiceOffer(){
		RegistrationAction selectedVehicleUpdate = new RegistrationAction();
		selectedVehicleUpdate.keepUpdateOfSelectedVehical(myServicesOfferTO.getVin());
		servicesOffer();
		
		return SUCCESS;
	}
	public String sendCouponByEamil(){
		try{
		 RegistrationTO selectedVehical = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
		 RegistrationTO loginDetail = (RegistrationTO) request.getSession().getAttribute("loginDetail");
		 String address = selectedVehical.getDealerName()+"<br/>"+selectedVehical.getDealerAddress()+"<br/>"+selectedVehical.getDealerCity()+", "+selectedVehical.getDealerState();
		 address = address+" "+selectedVehical.getDealerZip()+"<br/> "+selectedVehical.getDealerPhone();
		 JSONObject jsonEmailObj = new JSONObject();
		 jsonEmailObj.put("useremail", loginDetail.getEmail());
		 jsonEmailObj.put("emailto", myServicesOfferTO.getEmail());
		 jsonEmailObj.put("vin", selectedVehical.getVin());
		 jsonEmailObj.put("emailsubject", myServicesOfferTO.getTitle());
		 jsonEmailObj.put("emailmessage", myServicesOfferTO.getDescription().replaceAll("\t", "").replaceAll("\n", " "));
		 jsonEmailObj.put("disclaimer", myServicesOfferTO.getDisclaimer());
		 jsonEmailObj.put("address", address);
		 jsonEmailObj.put("couponvalue", myServicesOfferTO.getDeal());
		 jsonEmailObj.put("userphone", "");
		 String url = request.getSession().getAttribute("base_env_url")+"customercare/emailcoupons";
		 String postResponse = PostJsonObject.postJson(jsonEmailObj, url, request);
		 this.getServletResponse().getWriter().print("");
		 JSONObject jsonResponse = new JSONObject(postResponse);
		 if(jsonResponse.has("status") && jsonResponse.getString("status").equalsIgnoreCase("Success")){
			 this.getServletResponse().getWriter().print("Email sent successfully.");
		 }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<RegistrationTO> getLoginVehicalList() {
		return loginVehicalList;
	}

	public void setLoginVehicalList(List<RegistrationTO> loginVehicalList) {
		this.loginVehicalList = loginVehicalList;
	}

	public List<MyServicesOfferTO> getCoupnsList() {
		return coupnsList;
	}

	public void setCoupnsList(List<MyServicesOfferTO> coupnsList) {
		this.coupnsList = coupnsList;
	}

	public HttpServletResponse getServletResponse() {
		return servletResponse;
	}

	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	/**
	 * @return the coupnsList1
	 */
	public List<MyServicesOfferTO> getCoupnsList1() {
		return coupnsList1;
	}

	/**
	 * @param coupnsList1 the coupnsList1 to set
	 */
	public void setCoupnsList1(List<MyServicesOfferTO> coupnsList1) {
		this.coupnsList1 = coupnsList1;
	}
		
}
