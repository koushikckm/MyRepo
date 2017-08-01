package com.mazda.configuration.common.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.StrutsStatics;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mazda.common.utility.mail.GetJsonObject;
import com.mazda.configuration.common.transferobject.RegistrationTO;
import com.mazda.configuration.common.transferobject.ServiceReminderTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ServiceRemindersAction extends ActionSupport implements ModelDriven<ServiceReminderTO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ServiceReminderTO	serviceReminderTO;
	final ActionContext actionContext 	= ActionContext.getContext();
	private HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST); 
	private HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
	List<ServiceReminderTO> serviceReminderList = new ArrayList<ServiceReminderTO>();
	List egiftTabOnServRem = new ArrayList();
	
	public ServiceReminderTO getModel() {
		serviceReminderTO = new ServiceReminderTO();
		return serviceReminderTO;
	}
	public String execute() {
		try{
			
			@SuppressWarnings("unchecked")
			List<RegistrationTO> loginUser = (List<RegistrationTO>) request.getSession().getAttribute("loginUser");
			if(loginUser != null){
				List<RegistrationTO> vehicalsList = (List<RegistrationTO>) request.getSession().getAttribute("vehicalsList");
				if(vehicalsList==null){
					return NONE;
				}
				RegistrationTO loginUserDetail =loginUser.get(0)!=null?loginUser.get(0):null;
				RegistrationTO selectedVehical = (RegistrationTO)request.getSession().getAttribute("selectedVehical");
				// Properties properties 	= Utility.getProperties("env.properties");
				
				int size = 0;
				request.getSession().setAttribute("egiftTab", 0);
				request.getSession().setAttribute("egiftTabOnRelPage", 0);
				request.getSession().setAttribute("egiftTabOnSelectVeh", 0);
				
				String eGiftUrl = request.getSession().getAttribute("base_env_url")+"user/getUserEgifts/"+loginUserDetail.getCustomerId()+"/"+selectedVehical.getVin();
				String jsonEgift = GetJsonObject.getWebServceObj(eGiftUrl,request);
				JSONObject jsonObjEgift = new JSONObject(jsonEgift);
				if(jsonObjEgift.has("status") && jsonObjEgift.getString("status").equalsIgnoreCase("Success")){
					if(jsonObjEgift.getString("count").equalsIgnoreCase("0")) {
						egiftTabOnServRem.add("0");
						size = egiftTabOnServRem.size();
						request.getSession().setAttribute("egiftTabOnServRemChange", size);
					}
					else{
						request.getSession().setAttribute("egiftTabOnServRemChange", 0);
					}
				}
				egiftTabOnServRem = null;
				
				 String url 			= request.getSession().getAttribute("base_env_url")+"user/"+loginUserDetail.getCustomerId()+"/vehicle/"+selectedVehical.getVin()+"/servicereminders/v1";
				// String url 			= "http://mobilegarageqa.mazdausa.com/MyMazdaGarage/client/user/0000704405/vehicle/JM3ER2AM1B0361031/servicereminders/v1";
			    String json = GetJsonObject.getWebServceObj(url,request);
			    JSONObject jsonObj = new JSONObject(json);
			    /**
			     * Convert into json to arraylist
			     *//*if(jsonObj.getString("status").equalsIgnoreCase("Error") && jsonObj.has("status") ){
			    	 addActionError(jsonObj.getString("description"));
			    	 
			     }*/
			    	if(jsonObj.getString("status").equalsIgnoreCase("success") && jsonObj.has("status") ){
			    		ServiceReminderTO serviceReminders= new ServiceReminderTO();
			    		JSONArray jsonReminderArr = jsonObj.getJSONArray("serviceReminders");
			    		for(int i=0;i<jsonReminderArr.length();i++){
			    			JSONObject jsonReminderObj = jsonReminderArr.getJSONObject(i);
			    			JSONArray jsonCouponArr = jsonReminderObj.getJSONArray("coupons");
			    			for(int j=0;j<jsonCouponArr.length();j++){
			    				ArrayList<String> lineTextList = new ArrayList<String>();
			    				JSONObject jsonCouponObj = jsonCouponArr.getJSONObject(j);
			    				ServiceReminderTO serviceReminderCoupon = new ServiceReminderTO();
			    				serviceReminderCoupon.setVin(jsonCouponObj.getString("vin"));
			    				serviceReminderCoupon.setCouponNumber(jsonCouponObj.getString("couponNumber"));
			    				serviceReminderCoupon.setSpecificationPrice(jsonCouponObj.getString("specificationPrice"));
			    				serviceReminderCoupon.setDealerName(jsonCouponObj.getString("dealerName"));
			    				serviceReminderCoupon.setDealerZip(jsonCouponObj.getString("dealerZip"));
			    				serviceReminderCoupon.setDealerState(jsonCouponObj.getString("dealerState"));
			    				serviceReminderCoupon.setDealerCity(jsonCouponObj.getString("dealerCity"));
			    				serviceReminderCoupon.setDealerAddress(jsonCouponObj.getString("dealerAddr1"));
			    				serviceReminderCoupon.setCouponHeader(jsonCouponObj.getString("couponHeader1"));
			    				serviceReminderCoupon.setDisclaimer(jsonCouponObj.getString("disclaimerId"));
			    				String[] off = (jsonCouponObj.getString("specificationPrice").split("Off"));
			    				if(off.length>1){
			    					serviceReminderCoupon.setDollarOff(off[0]+" Off");
			    				}else {
			    					serviceReminderCoupon.setDollarOff(off[0]);
								}
			    				int textLine = 1; 
			    				String couponText="";
			    				for(int k = 0; k < 25; k++){
			    					String	TextVal = (jsonCouponObj.getString("lineText"+textLine)).trim();
			    					if(!TextVal.equalsIgnoreCase("")){
			    						/*char firstChar = TextVal.charAt(0);
			    						int asciiValue = (int)firstChar;
			    						boolean upperCase = Character.isUpperCase(TextVal.charAt(0));
			    						if((upperCase || asciiValue==45 || asciiValue==42)){
			    							if(!couponText.equalsIgnoreCase("")){
			    								lineTextList.add(couponText);
				    							couponText="";
			    							}else{
			    							couponText=TextVal;
			    							}
			    						}
			    						else if(!upperCase){
			    							couponText=couponText+" "+TextVal;
			    						}*/
			    						lineTextList.add(TextVal);
			    					}
			    					
			    					textLine++;
			    				}
			    				
			    				serviceReminderCoupon.setTextLine(lineTextList);
			    				serviceReminders = serviceReminderCoupon;
			    			
			    			
			    			JSONObject fullCircleObj = jsonReminderObj.getJSONObject("fullCircleData");
			    			String brake = fullCircleObj.getString("brake");
			    			serviceReminders.setBrakeFlag(brake.substring(0,1));
			    			
			    			String tire = fullCircleObj.getString("tire");
			    			serviceReminders.setTireFlag(tire.substring(0,1));
			    			if(fullCircleObj.has("serviceDate") && !fullCircleObj.getString("serviceDate").equalsIgnoreCase("")){
				    			String[] serviceDateArr = fullCircleObj.getString("serviceDate").split("-");
				    			serviceReminders.setServiceDate(serviceDateArr[1]+"/"+serviceDateArr[2]+"/"+serviceDateArr[0]);
			    			}
			    			
			    			if(serviceReminders.getBrakeFlag().equalsIgnoreCase("W")){
			    				serviceReminders.setBrtext("Not Inspected");
			    				serviceReminders.setBrImg("Brakes_notinspected.png");
			    			}else if(serviceReminders.getBrakeFlag().equalsIgnoreCase("G")){
			    				serviceReminders.setBrtext("50% or more remaining");
			    				serviceReminders.setBrImg("Brakes_good.png");
			    			}else if(serviceReminders.getBrakeFlag().equalsIgnoreCase("Y")){
			    				serviceReminders.setBrtext("20% - 50% remaining");
			    				serviceReminders.setBrImg("Brakes_normal.png");
			    			}else{
			    				serviceReminders.setBrtext("10% - 20% remaining");
			    				serviceReminders.setBrImg("Brakes_replace.png");
			    			}
			    			
			    			
			    			if(serviceReminders.getTireFlag().equalsIgnoreCase("W")){
			    				serviceReminders.setTitext("Not Inspected");
			    				serviceReminders.setTiImg("TC_notinspected.png");
			    			}else if(serviceReminders.getTireFlag().equalsIgnoreCase("G")){
			    				serviceReminders.setTitext("50% or more remaining");
			    				serviceReminders.setTiImg("TC_good.png");
			    			}else if(serviceReminders.getTireFlag().equalsIgnoreCase("Y")){
			    				serviceReminders.setTitext("20% - 50% remaining");
			    				serviceReminders.setTiImg("TC_normal.png");
			    			}else{
			    				serviceReminders.setTitext("10% - 20% remaining");
			    				serviceReminders.setTiImg("TC_replace.png");
			    			}
			    			
			    		
			    			serviceReminderList.add(serviceReminders);
			    		}
			    	}
		    				request.getSession().setAttribute("noOfReminder", serviceReminderList.size());
			   }
			    	if(serviceReminderList.size() ==0 && serviceReminderList.size()==0){
                        addActionMessage("Sorry, No Service reminder for the selected vehicle. Please check again later.");
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
	
	
	public String serviceReminderOfVehical(){
		RegistrationAction registrationAction = new RegistrationAction();
		registrationAction.keepUpdateOfSelectedVehical(serviceReminderTO.getVin());
		execute();
		return SUCCESS;
	}
	public List<ServiceReminderTO> getServiceReminderList() {
		return serviceReminderList;
	}
	public void setServiceReminderList(List<ServiceReminderTO> serviceReminderList) {
		this.serviceReminderList = serviceReminderList;
	}
	
	
}
