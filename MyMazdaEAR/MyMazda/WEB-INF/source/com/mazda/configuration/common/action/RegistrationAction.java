package com.mazda.configuration.common.action;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mazda.common.utility.PostJsonObject;
import com.mazda.common.utility.StringEncrypter;
import com.mazda.common.utility.Utility;
import com.mazda.common.utility.mail.GetJsonObject;
import com.mazda.configuration.common.transferobject.LocateDealerTO;
import com.mazda.configuration.common.transferobject.RegistrationTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 
 * @author mazda Tech Soft
 *
 */

public class RegistrationAction extends ActionSupport implements ModelDriven<RegistrationTO>, ServletResponseAware{
	static final Logger log = Logger.getLogger(RegistrationAction.class); 
	
	private static final long serialVersionUID = 1L;
	RegistrationTO registrationTO;
	final ActionContext actionContext 	= ActionContext.getContext();
	private HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST); 
	private HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
	List<RegistrationTO> loginVehicalList= new ArrayList<RegistrationTO>();
	List<RegistrationTO> loginUser=null;
	List<RegistrationTO> vehicalDetailsList=new ArrayList<RegistrationTO>();
	List<LocateDealerTO> dealerList = new ArrayList<LocateDealerTO>();
	List<LocateDealerTO> dealerListByName = new ArrayList<LocateDealerTO>();
	List<LocateDealerTO> dealerListByState = new ArrayList<LocateDealerTO>();
	List<LocateDealerTO> dealerListLocation = new ArrayList<LocateDealerTO>();
	List egiftTabList = new ArrayList();
	List egiftTabOnSelectVehicle = new ArrayList();
	List egiftTabOnReloadPage = new ArrayList();
	List egiftTabOnSelectMaintSch = new ArrayList();

	
	private HttpServletResponse servletResponse;
	
	public RegistrationTO getModel() {
		registrationTO = new RegistrationTO();
		return registrationTO;
	}

	public String execute() {
		return SUCCESS;
	}
	public String toRetrievePassword() 	{
		return SUCCESS;
	}
	
	public String myMazda() {
		try{
			@SuppressWarnings("unchecked")
			List<RegistrationTO> loginUser = (List<RegistrationTO>) request.getSession().getAttribute("loginUser");
			request.getSession().removeAttribute("addPreferedDealer");
			request.getSession().removeAttribute("registerPreferedDealer");
			request.getSession().removeAttribute("RegistrationTO");
			
			log.debug("Registration TO  removed from session");
			if(loginUser == null){
				    	
					return LOGIN;
				    }
			if(loginUser != null){
				
				RegistrationTO login = loginUser.get(0);
				RegistrationTO selectedVehical = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
				log.debug("Selected vehical from session is : "+ selectedVehical);
				int size = 0;
				//request.getSession().setAttribute("egiftTabOnSelectVeh", 0);
				String eGifturl = request.getSession().getAttribute("base_env_url")+"user/getUserEgifts/"+login.getCustomerId()+"/"+selectedVehical.getVin();
				String json = GetJsonObject.getWebServceObj(eGifturl,request);
				JSONObject jsonObjEgift = new JSONObject(json);
				 if(jsonObjEgift.has("status") && jsonObjEgift.getString("status").equalsIgnoreCase("Success")){
						if(jsonObjEgift.getString("count").equalsIgnoreCase("0")) {
							egiftTabOnReloadPage.add("0");
							size = egiftTabOnReloadPage.size();
							request.getSession().setAttribute("egiftTabOnRelPage", size);
						}
						else{
							request.getSession().setAttribute("egiftTabOnRelPage", 0);
						}
				  }
				  egiftTabOnReloadPage = null;
				  
				  keepUpdateOfSelectedVehical(selectedVehical.getVin());
			}
				
		}catch (Exception e) {
			log.error("MyMazda Exception :- "+ ExceptionUtils.getFullStackTrace(e));
		}
		log.debug("MyMazda action completed ****");
		return SUCCESS;
	}
	
	public String register(){
		try{	
			String url = request.getSession().getAttribute("base_env_url")+"vehicle/detail/"+registrationTO.getVin().trim();
			String jsonVehicle = GetJsonObject.getWebServceObj(url,request);
			JSONObject jsonResponse = new JSONObject(jsonVehicle);

			if(jsonResponse.has("status") && jsonResponse.getString("status").equalsIgnoreCase("success")){
				url = request.getSession().getAttribute("base_env_url")+"user/registration";
				JSONObject json = new JSONObject();
				StringEncrypter encrypto = new StringEncrypter();
				String encrptedPass = encrypto.encrypt(registrationTO.getPassword());
				JSONObject jsonData = new JSONObject();
				jsonData.put("userid", registrationTO.getEmail());
				jsonData.put("password", encrptedPass);
				jsonData.put("mobileno", registrationTO.getMobilePhone());
				json.put("data", jsonData.toString());
				
				//**"Registration inner data : "+jsonData);
				json.put("title", registrationTO.getTitle());
				json.put("fname", registrationTO.getFirstName());
				json.put("lname", registrationTO.getLastName());
				json.put("street", registrationTO.getStreet1address()+" "+registrationTO.getStreet2address());	//Park Avenue Stree
				json.put("city", registrationTO.getCity());		//Newyork
				json.put("state", registrationTO.getState());	//CA
				json.put("zip", registrationTO.getZip());	//95221
				json.put("vin", registrationTO.getVin());	//"1YVHZ8EH5D5M03240"
				json.put("modelyear", registrationTO.getMdlYear());
				/*json.put("vtitle", registrationTO.getVtitle());*/
				json.put("modelcode", registrationTO.getCarlineCode());
				json.put("mileage", registrationTO.getMileage());
				json.put("apnstoken", "simulator");

				//**"Registration json data : "+json);

				
				String postResponse = PostJsonObject.postJson(json, url,request);
				jsonResponse = new JSONObject(postResponse);
				if(jsonResponse.has("status") && jsonResponse.getString("status").equalsIgnoreCase("success")){
					if(jsonResponse.has("customerId")){
						String custormerId = jsonResponse.getString("customerId");
						url = request.getSession().getAttribute("base_env_url")+"vehicle/"+registrationTO.getVin().trim()+"/updateownership";
						JSONObject ownerUpdateObj = new JSONObject();
						ownerUpdateObj.put("newCustomerId", custormerId);
						ownerUpdateObj.put("disposalFlag", "1");
						ownerUpdateObj.put("disposalReason", "Transfer");
						Date date = new Date();
						String formatDate = new SimpleDateFormat("yyyy-MM-dd").format( date);
						ownerUpdateObj.put("resaleDate", formatDate);
						ownerUpdateObj.put("source", "MMG");
						ownerUpdateObj.put("customerId","" );
						postResponse = PostJsonObject.postJson(ownerUpdateObj, url,request);
						jsonResponse = new JSONObject(postResponse);
						if(jsonResponse.has("status") && jsonResponse.getString("status").equalsIgnoreCase("success")){
							url = request.getSession().getAttribute("base_env_url")+"mileage/vehicle/"+registrationTO.getVin();
							JSONObject mileageUpdate = new JSONObject();
							mileageUpdate.put("error", "");
							mileageUpdate.put("drivingConditions", registrationTO.getDrivingCondition());
							mileageUpdate.put("calcDate", "");
							mileageUpdate.put("mileage", registrationTO.getMileage());
							mileageUpdate.put("milesPerDay", registrationTO.getMilesPerDay());
							postResponse = PostJsonObject.postJson(mileageUpdate, url,request);
							jsonResponse = new JSONObject(postResponse);

							if(jsonResponse.has("status") && jsonResponse.getString("status").equalsIgnoreCase("success")){
								registrationTO.setCustomerId(custormerId);
								//updateCarlineDesc(registrationTO);
								if(registrationTO.getServiceDealerID() !=null){
								url = request.getSession().getAttribute("base_env_url")+"vehicle/"+registrationTO.getVin()+"/updateservicingdealer";
								JSONObject updateDealerObj = new JSONObject();
								updateDealerObj.put("customerId", custormerId);
								updateDealerObj.put("dealerCode", registrationTO.getServiceDealerID());
								updateDealerObj.put("source", "y");
								postResponse = PostJsonObject.postJson(updateDealerObj, url,request);
								jsonResponse = new JSONObject(postResponse);
								if(jsonResponse.getString("status").equalsIgnoreCase("success")){
									//jsonResponse.put("description", "User has been successfully registered with Mazda");
									this.getServletResponse().getWriter().print("success");
									return SUCCESS;
								}
							}
								else{
									//jsonResponse.put("description", "User has been successfully registered with Mazda");
									this.getServletResponse().getWriter().print("success");
									return SUCCESS;
								}
							}
						}else{
							//jsonResponse.put("description", "There is some problem in VIN registration, You might be registered with us. Please Try login with these credentials and email us, we will be rectifying the issue soon. Meanwhile you can another VIN if you have.");
							this.getServletResponse().getWriter().print("vin");
							return null;
						}
					}
				}else{
					if(jsonResponse.getString("description").trim().equalsIgnoreCase("email_exists:")){
						//jsonResponse.put("description", "Email already exist.");
						this.getServletResponse().getWriter().print("Email");
						return null;
					}else if(jsonResponse.getString("description").trim().contains("Please enter a valid combination of ZIP Code and state")){
						//jsonResponse.put("description", "Email already exist.");
						this.getServletResponse().getWriter().print("zip");
						return null;
					}else{
						/*jsonResponse.put("description", "Please enter a valid zip code.");
						 * */
						//jsonResponse.put("description", "There is a problem, while communicating with web services. Please try again.");
						this.getServletResponse().getWriter().print("serviceProblem");
						return null;
					}
				}
			}else{
				//jsonResponse.put("description", "Sorry unable to validate vin with our records.");
				this.getServletResponse().getWriter().print("vinvalidation");
				return null;
			}
			//this.getServletResponse().getWriter().print(jsonResponse.getString("description"));
			//addActionMessage(jsonResponse.getString("description"));
		}catch (Exception e) {
			log.error("Registration Exception :- "+ ExceptionUtils.getFullStackTrace(e));
		}
		return SUCCESS;
	}
	
	
	
	public String loginUser(){
		log.debug(">> Entering loginUser() with Username: "+ registrationTO.getEmail() + " Password: " + registrationTO.getPassword());
		try {
			
			if(registrationTO.getRemember().equalsIgnoreCase("true")){
				request.getSession().setAttribute("CheckboxRegistrationTO", registrationTO);
			}else{
				//Do Nothing;
				request.getSession().removeAttribute("CheckboxRegistrationTO");
			}
			
			
		String url = request.getSession().getAttribute("base_env_url")+"user/login";
		JSONObject outer = new JSONObject();
		JSONObject inner = new JSONObject();

		//To encryt password start
		StringEncrypter encrypto = new StringEncrypter();
		String encrptedPass = encrypto.encrypt(registrationTO.getPassword());
		//To encryt password end
		//**"Encrypted pwd in login : "+encrptedPass);

		inner.put("userid", registrationTO.getEmail());
		inner.put("password", encrptedPass);
		//**"Encrypted pwd in login inner : "+inner.toString());
		
		outer.put("data", inner.toString());
		outer.put("apnstoken", "simulator");
		//**"Encrypted pwd in login outer : "+outer.toString());

		String postResponse = PostJsonObject.postJson(outer, url,request);
		JSONObject jsonObj = new JSONObject(postResponse);
		//**"Login json obj > "+jsonObj);
		
		 //jsonObj = new JSONObject(postResponse);
		 if(jsonObj.has("status") && jsonObj.getString("authenticated").equalsIgnoreCase("yes")){
			  jsonObj = getLoginDetail(jsonObj);
			  log.debug(">> Login getLoginDetail returned json obj " + jsonObj);
			  if(jsonObj.getString("status").equalsIgnoreCase("success") && jsonObj.has("status")){
				  JSONObject jsonLogin = jsonObj.getJSONObject("userdetails");
				  String custId = jsonLogin.getString("customerId");
				  jsonObj = getVehicleDetail(custId);
				  log.debug(">> Login getVehicleDetail returned json obj " + jsonObj);
				  if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("success")){
					  List<RegistrationTO> vehicalsList = (List<RegistrationTO>) request.getSession().getAttribute("vehicalsList");
					  String data=registrationTO.getEmail()+','+registrationTO.getPassword();
					  HttpSession session = request.getSession();
					  session.setAttribute("data", data);
					  if(vehicalsList==null){
						  this.getServletResponse().getWriter().print("add");
							return null;
						}
					  
					  int size = 0;
					  request.getSession().setAttribute("egiftTabOnSelectVeh", 0);
					  //request.getSession().setAttribute("egiftTabOnRelPage", 0);
					  RegistrationTO selectedVehical = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
					  String eGiftUrl = request.getSession().getAttribute("base_env_url")+"user/getUserEgifts/"+custId+"/"+selectedVehical.getVin();
					  String json = GetJsonObject.getWebServceObj(eGiftUrl,request);
					  JSONObject jsonObjEgift = new JSONObject(json);
					  if(jsonObjEgift.has("status") && jsonObjEgift.getString("status").equalsIgnoreCase("Success")){
							if(jsonObjEgift.getString("count").equalsIgnoreCase("0")) {
								egiftTabList.add("0");
								size = egiftTabList.size();
								request.getSession().setAttribute("egiftTab", size);
							}
							else{
								request.getSession().setAttribute("egiftTab", 0);
							}
					  }
					  egiftTabList = null;
					  
					  return SUCCESS;
				  }
			  }
			  else{
					this.getServletResponse().getWriter().print("serviceProblem");
					return null;
					}
		}else{
			this.getServletResponse().getWriter().print("no");
			return null;
			}
		} catch (Exception e) {
			log.error("login User Exception :- " + ExceptionUtils.getFullStackTrace(e));
		}
		log.debug(">> Exiting loginUser() Success");
		return LOGIN;
	}
	
	
	public String proxyLoginUser() {

		try {
			registrationTO.setEmail(request.getParameter("username"));
			String url = request.getSession().getAttribute("base_env_url")
					+ "user/login";

			JSONObject outer = new JSONObject();
			JSONObject inner = new JSONObject();

			// To encryt password start
			StringEncrypter encrypto = new StringEncrypter();
			String encrptedPass = encrypto.encrypt(request
					.getParameter("password"));
			// To encryt password end
			// **"Encrypted pwd in login : "+encrptedPass);

			inner.put("userid", request.getParameter("username"));
			inner.put("password", encrptedPass);
			// **"Encrypted pwd in login inner : "+inner.toString());

			outer.put("data", inner.toString());
			outer.put("apnstoken", "simulator");
			// **"Encrypted pwd in login outer : "+outer.toString());

			String postResponse = PostJsonObject.postJson(outer, url, request);
			JSONObject jsonObj = new JSONObject(postResponse);
			// **"Login json obj > "+jsonObj);

			if (jsonObj.has("status")
					&& jsonObj.getString("authenticated").equalsIgnoreCase(
							"yes")) {
				jsonObj = getLoginDetail(jsonObj);
				if (jsonObj.getString("status").equalsIgnoreCase("success")
						&& jsonObj.has("status")) {
					JSONObject jsonLogin = jsonObj.getJSONObject("userdetails");
					String custId = jsonLogin.getString("customerId");
					jsonObj = getVehicleDetail(custId);
					if (jsonObj.has("status")
							&& jsonObj.getString("status").equalsIgnoreCase(
									"success")) {
						List<RegistrationTO> vehicalsList = (List<RegistrationTO>) request
								.getSession().getAttribute("vehicalsList");
						String data = request.getParameter("username") + ','
								+ request.getParameter("password");
						HttpSession session = request.getSession();
						session.setAttribute("data", data);
						if (vehicalsList == null) {
							this.getServletResponse().getWriter().print("add");
							return null;
						}

						int size = 0;
						request.getSession().setAttribute(
								"egiftTabOnSelectVeh", 0);
						// request.getSession().setAttribute("egiftTabOnRelPage",
						// 0);
						RegistrationTO selectedVehical = (RegistrationTO) request
								.getSession().getAttribute("selectedVehical");
						String eGiftUrl = request.getSession().getAttribute(
								"base_env_url")
								+ "user/getUserEgifts/"
								+ custId
								+ "/"
								+ selectedVehical.getVin();
						String json = GetJsonObject.getWebServceObj(eGiftUrl,
								request);
						JSONObject jsonObjEgift = new JSONObject(json);
						if (jsonObjEgift.has("status")
								&& jsonObjEgift.getString("status")
										.equalsIgnoreCase("Success")) {
							if (jsonObjEgift.getString("count")
									.equalsIgnoreCase("0")) {
								egiftTabList.add("0");
								size = egiftTabList.size();
								request.getSession().setAttribute("egiftTab",
										size);
							} else {
								request.getSession()
										.setAttribute("egiftTab", 0);
							}
						}
						egiftTabList = null;

						return SUCCESS;
					}
				} else {
					this.getServletResponse().getWriter()
							.print("serviceProblem");
					return null;
				}
			} else {
				this.getServletResponse().getWriter().print("Login Failed. Please check the credentials.");
				return null;
			}
		} catch (Exception e) {
			log.error("Proxylogin User Exception :- " + ExceptionUtils.getFullStackTrace(e));
		}
		return LOGIN;
	}
	
	
	
	public JSONObject getLoginDetail(JSONObject jsonObj){
		log.debug(">> Entering getLoginDetail()");
		try{
			JSONObject userDetail = new JSONObject();
			userDetail.put("vin", "");
			userDetail.put("customerid", jsonObj.getString("customerId"));
			userDetail.put("email", registrationTO.getEmail());
			userDetail.put("firstname", "?");
			userDetail.put("lastname", "?");
			userDetail.put("source", "?");
			userDetail.put("street1address", "?");
			userDetail.put("street2address", "?");
			userDetail.put("zip", "?");
			userDetail.put("city", "?");
			userDetail.put("state", "?");
			userDetail.put("mobilePhone", "?");
			String url = request.getSession().getAttribute("base_env_url")+"user/detail";
			String postResponse = PostJsonObject.postJson(userDetail, url,request);
			jsonObj = new JSONObject(postResponse);
			if(jsonObj.getString("status").equalsIgnoreCase("success") && jsonObj.has("status")){
			RegistrationTO loginUserDetail = new RegistrationTO();
			JSONObject loginUserDet = jsonObj.getJSONObject("userdetails");
			String customerId=loginUserDet.getString("customerId");
			loginUserDetail.setFirstName(loginUserDet.getString("firstName"));
			loginUserDetail.setLastName(loginUserDet.getString("lastName"));
			loginUserDetail.setState(loginUserDet.getString("state"));
			loginUserDetail.setCity(loginUserDet.getString("city"));
			loginUserDetail.setCustomerId(loginUserDet.getString("customerId"));
			loginUserDetail.setStreet1address(loginUserDet.getString("street1address"));
			loginUserDetail.setStreet2address(loginUserDet.getString("street2address"));
			loginUserDetail.setEmail(registrationTO.getEmail());
			loginUserDetail.setZip(loginUserDet.getString("zip"));
			loginUserDetail.setMobilePhone(loginUserDet.getString("mobilePhone"));
			request.getSession().setAttribute("loginDetail", loginUserDetail);
			}
			
			//Display Message On Login
			try {
				String message = Utility.getProperties("env.properties").getProperty("MESSAGE_ON_LOGIN");
				if(message != null && message.trim().length() > 0) {
					request.getSession().setAttribute("messageOnLogin", message);
				} else {
					request.getSession().setAttribute("messageOnLogin", null);
				}
			} catch (Exception e) {
				log.error("Exception in display Message On Login :- " + ExceptionUtils.getFullStackTrace(e));
			}
			log.debug(">> Exeting getLoginDetail()");
			return jsonObj;
			
		} catch (Exception e) {
			log.error("get Login Detail Exception :- " + ExceptionUtils.getFullStackTrace(e));
			return null;
		}
	}
	
	public JSONObject getVehicleDetail(String customerId){
		log.debug(">> Entering getVehicleDetail()");
		String url = request.getSession().getAttribute("base_env_url")+"user/"+customerId+"/vehicles";
		try{
			String json = GetJsonObject.getWebServceObjWithCustomHeaders(url, request);
			JSONObject jsonVehicleObj = new JSONObject(json);
			if(jsonVehicleObj.has("status") && jsonVehicleObj.getString("status").equalsIgnoreCase("Success")){
				JSONArray vehicalArray = new JSONArray();
				vehicalArray = jsonVehicleObj.getJSONArray("vehicles");
				log.debug(">> vehicalArray " + vehicalArray);
				log.debug(">> jsonVehicleObj " + jsonVehicleObj);
				for(int j=0; j<vehicalArray.length(); j++){
					RegistrationTO vehicalObj = new RegistrationTO();
					JSONObject jsonVehicalObj = vehicalArray.getJSONObject(j);
					vehicalObj.setServiceDealerID(jsonVehicalObj.getString("serviceDealerID"));
					vehicalObj.setMdlYear(jsonVehicalObj.getInt("mdlYear"));
					vehicalObj.setMdlCode(jsonVehicalObj.getString("mdlCode"));
					vehicalObj.setCarlineDesc(jsonVehicalObj.getString("carlineDesc"));
					vehicalObj.setVin(jsonVehicalObj.getString("vin"));
					vehicalObj.setTrim(jsonVehicalObj.getString("trim"));
					vehicalObj.setExtColorCode(jsonVehicalObj.getString("extColorCode"));
					vehicalObj.setExtColorDesc(jsonVehicalObj.getString("extColorDesc"));
					vehicalObj.setCarlineCode(jsonVehicalObj.getString("carlineCode"));
					vehicalObj.setIntColorDesc(jsonVehicalObj.getString("intColorDesc"));
					vehicalObj.setEngine(jsonVehicalObj.getJSONObject("engine").getString("engDesc"));
					vehicalObj.setTransmission(jsonVehicalObj.getJSONObject("transmission").getString("transdesc"));
					vehicalObj.setServiceDealerID(jsonVehicalObj.getString("serviceDealerID"));
					log.debug(">> vehicalObj before carline service call " + vehicalObj);
					
					//Get user updated vehicle names
					String vehicleName = fetchCarlineDesc(jsonVehicalObj.getString("vin"));
					if(vehicleName!=null){
						vehicalObj.setCarlineDesc(vehicleName.toUpperCase());
					}
					
					log.debug(">> vehicalObj after carline service call " + vehicalObj);
					
					url = request.getSession().getAttribute("base_env_url")+"mileage/vehicle/"+jsonVehicalObj.getString("vin");
				
					json = GetJsonObject.getWebServceObj(url,request);
					JSONObject jsonVehicleMileage = new JSONObject(json);
					if(jsonVehicleMileage.has("status") && jsonVehicleMileage.getString("status").equalsIgnoreCase("Success")){
						JSONObject mileageObj = jsonVehicleMileage.getJSONObject("mileagedetails");
						vehicalObj.setMileage(mileageObj.getInt("mileage"));
						vehicalObj.setMilesPerDay(mileageObj.getString("milesPerDay"));
						vehicalObj.setDrivingCondition(mileageObj.getString("drivingConditions").trim());
					}
					log.debug(">> vehicalObj after getting milage " + vehicalObj);
					/**This Code is for checking "0" doesnot exists in service dealer Id
					 * Implemented By Riaz 
					 * Date 31st Jan
					 */
					if(!jsonVehicalObj.getString("serviceDealerID").equals("0")){
						url = request.getSession().getAttribute("base_env_url")+"search/dealer/code/"+jsonVehicalObj.getString("serviceDealerID");
						json = GetJsonObject.getWebServceObj(url,request);
						JSONObject jsonPreferedDealer = new JSONObject(json);
						if(jsonPreferedDealer.has("status") && jsonPreferedDealer.getString("status").equalsIgnoreCase("Success")){
							JSONObject dealerObj = jsonPreferedDealer.getJSONObject("dealer");
							log.debug(">> Success dealer " + dealerObj);
							vehicalObj.setDealerAddress(dealerObj.getString("address"));
							vehicalObj.setDealerPhone(dealerObj.getString("phone"));
							vehicalObj.setDealerName(dealerObj.getString("name"));
							vehicalObj.setDealerZip(dealerObj.getString("zip"));
							vehicalObj.setDealerState(dealerObj.getString("state"));
							vehicalObj.setDealerCity(dealerObj.getString("city"));
							String dealerCode = dealerObj.getString("code").trim();
							log.debug(">> vehicalObj after getting dealerID " + vehicalObj);
							if(!dealerCode.equalsIgnoreCase("")){
								vehicalObj.setServiceDealerID(dealerObj.getString("code"));
								log.debug(">> Success dealer code " + dealerObj.getString("code"));
							}else{
								vehicalObj.setServiceDealerID(null);
							}
							if(dealerObj.has("expServiceFlag") && dealerObj.getString("expServiceFlag").equalsIgnoreCase("Y")){ // expServicelag typo replaced with expServiceFlag on 03/17/2017 by akudarav
								vehicalObj.setExpServiceFlag("Mazda Express Service");
					   	    }else{
					   	    	vehicalObj.setExpServiceFlag(null);
					   	    }
							
							String dlrName = dealerObj.getString("name");
							if(dlrName != null && dlrName.trim().length() > 0) {
								dlrName = dlrName.replaceAll("/"," ");
								dlrName = dlrName.replaceAll("#"," ");
							
								url = request.getSession().getAttribute("base_env_url")+"search/dealer/name/"+dlrName.trim();
								log.debug(">> Search dealer by name url: " + url);
								json = GetJsonObject.getWebServceObj(url,request);
								log.debug(">> Search dealer by name json response: " + json);
								JSONObject jsonObj = new JSONObject(json);
								
								if(jsonObj.getString("status").equalsIgnoreCase("Success") && jsonObj.getInt("searchcount")>0){
									JSONArray dealerArray = jsonObj.getJSONArray("dealers");
										if(jsonObj.getString("status").equalsIgnoreCase("Success")){
											for(int i =0; i<dealerArray.length();i++){
												JSONObject	jsonDealerObj = dealerArray.getJSONObject(i);
												if(jsonVehicalObj.getString("serviceDealerID").equalsIgnoreCase(jsonDealerObj.getString("id"))){
													if(jsonDealerObj.has("serviceUrl")){
														vehicalObj.setServiceUrl(jsonDealerObj.getString("serviceUrl"));
														String serviceurl=jsonDealerObj.getString("serviceUrl");
														 log.debug("serviceurl::"+serviceurl);
														  HttpSession session = request.getSession();
														  session.setAttribute("serviceurl", serviceurl);
													}else{
														vehicalObj.setServiceUrl(null);
													}
											}
										}
									}
								}
								log.debug(">> vehicalObj after getting dealerName " + vehicalObj);
							}
							
						}
					}
					loginVehicalList.add(vehicalObj);
				}
				log.debug(">> loginVehicalList # : "+loginVehicalList.size());
				if(loginVehicalList.size() > 0){
				request.getSession().setAttribute("vehicalsList", loginVehicalList);
				
				keepUpdateOfSelectedVehical(loginVehicalList.get(0).getVin());
				}
				
				
				RegistrationTO loginDetail = new RegistrationTO();
				loginDetail.setCustomerId(customerId);
				List<RegistrationTO> loginUser = new ArrayList<RegistrationTO>();
				loginUser.add(loginDetail);
				request.getSession().setAttribute("loginUser", loginUser);
			}
			log.debug(">> Exeting getVehicleDetail() with jsonVehicleObj " + jsonVehicleObj);
			return jsonVehicleObj;
		}catch (Exception e) {
			log.error("get Vehicle detail Exception :- " + ExceptionUtils.getFullStackTrace(e));
			return null;
		}
	}
	
	public String applyToUpdateSelectedVehical(){
				
		int size = 0;
		request.getSession().setAttribute("egiftTab", 0);
		request.getSession().setAttribute("egiftTabOnRelPage", 0);
		
		RegistrationTO loginUserDetail = new RegistrationTO();
		loginUserDetail =  (RegistrationTO) request.getSession().getAttribute("loginDetail");
		RegistrationTO selectedVehical = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
		  
		String eGiftUrl = request.getSession().getAttribute("base_env_url")+"user/getUserEgifts/"+loginUserDetail.getCustomerId()+"/"+registrationTO.getVin();
		String json = GetJsonObject.getWebServceObj(eGiftUrl,request);
		JSONObject jsonObjEgift = new JSONObject(json);
		if(jsonObjEgift.has("status") && jsonObjEgift.getString("status").equalsIgnoreCase("Success")){
			if(jsonObjEgift.getString("count").equalsIgnoreCase("0")) {
				egiftTabOnSelectVehicle.add("0");
				size = egiftTabOnSelectVehicle.size();
				request.getSession().setAttribute("egiftTabOnSelectVeh", size);
			}
			else{
				request.getSession().setAttribute("egiftTabOnSelectVeh", 0);
			}
		}
		egiftTabOnSelectVehicle = null;
		  
			
		keepUpdateOfSelectedVehical(registrationTO.getVin());
		request.getSession().removeAttribute("RegistrationTO");
		return SUCCESS;
	}
	
	public String updateSelectedVehicalPreferredDealer(){
		keepUpdateOfSelectedVehical(registrationTO.getVin());
		return SUCCESS;
	}
	
	public void keepUpdateOfSelectedVehical(String vin){
		try{
			List<RegistrationTO> loginVehicalList = (List<RegistrationTO>) request.getSession().getAttribute("vehicalsList"); 
			
			log.debug("List of vehicals avialable in session : " + loginVehicalList.size());
			String userUploadedImagePath = null;
			String stockImagePath = null;
			String windowStickerUrl = null;
			String userImageUrl = null;
			
			if(loginVehicalList !=null){
				RegistrationTO selectedVehical = null;
				for(int i=0; i<loginVehicalList.size(); i++){
					if(loginVehicalList.get(i).getVin().equalsIgnoreCase(vin)){
						selectedVehical = loginVehicalList.get(i);
						log.debug("Updating selected vehical values with : "+ selectedVehical);
						request.getSession().setAttribute("selectedVehical", selectedVehical);
						break;
					}
				}
				RegistrationTO loginUserDetail = new RegistrationTO();
				loginUserDetail =  (RegistrationTO) request.getSession().getAttribute("loginDetail");
			
				// Call user uploaded img ws
				userImageUrl = request.getSession().getAttribute("base_env_url")+ "user/userImageDetail/"+ loginUserDetail.getCustomerId()+ "/"+ selectedVehical.getVin();
				log.debug("userImageUrl: " + userImageUrl);
				// userImageUrl ="http://localhost:8082/MyMazdaGarage/client/user/userImageDetail/"+loginUserDetail.getCustomerId()+"/"+selectedVehical.getVin();
				String userImageJson = GetJsonObject.getWebServceObj(userImageUrl, request);
				JSONObject userImageResponse = new JSONObject(userImageJson);
				log.debug("userImageResponse: " + userImageResponse);
				if (userImageResponse.has("status") && userImageResponse.getString("status").equalsIgnoreCase("success")) {
					userUploadedImagePath = request.getSession().getAttribute(
							"base_env_url")
							+ "user/userImage/"
							+ loginUserDetail.getCustomerId()
							+ "/"
							+ selectedVehical.getVin();
				} else {
					// Call window sticker webservice
					String width = "0";
					String mdlCode = selectedVehical.getMdlCode().toLowerCase();
					mdlCode = mdlCode.replaceAll(" ", "+");
					
					windowStickerUrl = request.getSession().getAttribute("base_env_url")+"user/windowSticker/"+selectedVehical.getMdlYear()
							+"/"+selectedVehical.getCarlineCode().toLowerCase().trim()
							+"/"+selectedVehical.getExtColorCode().toLowerCase().trim()+"/"+width+"/"+mdlCode;
					log.debug("windowStickerUrl: " + windowStickerUrl);
					//windowStickerUrl = "http://localhost:8080/MyMazdaGarage/client/user/windowSticker/2016/m3h/42a/250/m3h++i+++a++";
					String windowStickerJson = GetJsonObject.getWebServceObj(
							windowStickerUrl, request);
					JSONObject windowStickerResponse = new JSONObject(
							windowStickerJson);
					log.debug("windowStickerResponse: " + windowStickerResponse);
					if (windowStickerResponse.has("status")
							&& windowStickerResponse.getString("status")
									.equalsIgnoreCase("success")) {
						if (windowStickerResponse.has("imageUrl")) {
							stockImagePath = windowStickerResponse
									.getString("imageUrl");
						}
					}
				}
				
				request.getSession().setAttribute("userUploadedImagePath", userUploadedImagePath);
				request.getSession().setAttribute("stockImagePath", stockImagePath);
			}
			
		} catch (Exception e) {
			System.err.println(e);
			log.error("keepUpdateOfSelectedVehical # Error Occured :- " + ExceptionUtils.getFullStackTrace(e));
			
		}
	}

	public String logoutUser(){
		try {
			HttpServletRequest request 	= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
			HttpSession httpSession 	= request.getSession(); 
			httpSession.removeAttribute("loginUser");
			httpSession.removeAttribute("selectedVehical");
			httpSession.removeAttribute("vehicalsList");
			httpSession.removeAttribute("globalTO");
			httpSession.removeAttribute("globalTO");
			httpSession.removeAttribute("loginDetail");
			httpSession.removeAttribute("egiftTab");
			httpSession.removeAttribute("egiftTabOnSelectVeh");
			httpSession.removeAttribute("egiftTabOnRelPage");
			return SUCCESS;
		}catch (Exception e) {
			log.error("LogOut User Exception :- " + ExceptionUtils.getFullStackTrace(e));
			return null;
		}
	}
	
	public String forgotPassword(){
		try {
			String url = request.getSession().getAttribute("base_env_url")+"user/forgotpassword";
			RegistrationTO loginDetail = (RegistrationTO) request.getSession().getAttribute("loginDetail");
			
			JSONObject forgotPassword = new JSONObject();
			forgotPassword.put("email", registrationTO.getEmail());
						
			String postResponse = PostJsonObject.postJson(forgotPassword, url,request);
			JSONObject jsonObj = new JSONObject(postResponse);
			
			this.getServletResponse().getWriter().print(jsonObj.getString("status"));
			return null;
		}catch (Exception e) {
			log.error("Exception occurred in forgot password :- " + ExceptionUtils.getFullStackTrace(e));
			return ERROR;
		}
		
	}
	
	public String resetPasswordLink(){

		try {
			if (null != request.getSession().getAttribute("loginUser")) {
				logoutUser();
			}
			
			String url = request.getSession().getAttribute("base_env_url")+"user/verifyresetpasswordlink/"+request.getQueryString();
					
			String getResponse = GetJsonObject.getWebServceObj(url,request);
			JSONObject jsonObj = new JSONObject(getResponse);
		
			if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("success")){
				log.debug(jsonObj.getString("email"));
				request.getSession().setAttribute("encryptedUser", jsonObj.getString("email"));
				return SUCCESS;
			}
			else if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("failure")){
				return NONE;
			}
	
		}catch (Exception e) {
			log.error("Exception occurred resetPasswordLink() :- " + ExceptionUtils.getFullStackTrace(e));
			return ERROR;
		}
		return null;	
	}

	public void passwordUpdate() throws IOException{

		try {
			String url = request.getSession().getAttribute("base_env_url")+"user/updatepassword";
						
			JSONObject resetPassword = new JSONObject();
			resetPassword.put("email", request.getParameter("userName"));
			resetPassword.put("newPassword", request.getParameter("newPassword"));
						
			String postResponse = PostJsonObject.postJson(resetPassword, url,request);
			JSONObject jsonObj = new JSONObject(postResponse);
			

			if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("success")){
				this.getServletResponse().getWriter().print("success");
				//return LOGIN;
			}
			else if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("failure")){
				this.getServletResponse().getWriter().print("error");
				//return NONE;
			}
			
		}catch (Exception e) {
			log.error("Exception occurred at submitPasswordChange() :- " + ExceptionUtils.getFullStackTrace(e));
			this.getServletResponse().getWriter().print("error");

			//return ERROR;
		}
		//return null;	
	}
	
	public String vehicalDetail()		{
		try{
			@SuppressWarnings("unchecked")
			List<RegistrationTO> loginUser = (List<RegistrationTO>) request.getSession().getAttribute("loginUser");
			if(loginUser.size() >0){
				for(int i=0; i<loginVehicalList.size(); i++){
					if(loginVehicalList.get(i).getId() == registrationTO.getId()){
						RegistrationTO vehicalDetails = loginVehicalList.get(i);
						vehicalDetailsList.add(vehicalDetails);
					}
				}
			}
		}catch (Exception e) {
			log.error("Vehicle Detail Exception :- " + ExceptionUtils.getFullStackTrace(e));
		}
		return SUCCESS;
	}
	public String addMoreVehical()		{
		try{
			RegistrationTO loginUser = (RegistrationTO) request.getSession().getAttribute("loginDetail");
			String url = request.getSession().getAttribute("base_env_url")+"vehicle/detail/"+registrationTO.getVin();
			String jsonVehicle = GetJsonObject.getWebServceObj(url,request);
			JSONObject jsonResponse = new JSONObject(jsonVehicle);
			if(jsonResponse.has("status") && jsonResponse.getString("status").equalsIgnoreCase("success")){
			 url = request.getSession().getAttribute("base_env_url")+"vehicle/"+registrationTO.getVin()+"/updateownership";
			JSONObject ownerUpdateObj = new JSONObject();
			ownerUpdateObj.put("newCustomerId", loginUser.getCustomerId());
			ownerUpdateObj.put("disposalFlag", "1");
			ownerUpdateObj.put("disposalReason", "Transfer");
			Date date = new Date();
			String formatDate = new SimpleDateFormat("yyyy-MM-dd").format( date);
			ownerUpdateObj.put("resaleDate", formatDate);
			ownerUpdateObj.put("source", "MMG");
			ownerUpdateObj.put("customerId","" );
			String postResponse = PostJsonObject.postJson(ownerUpdateObj, url,request);
			jsonResponse = new JSONObject(postResponse);
			if(jsonResponse.has("status") && jsonResponse.getString("status").equalsIgnoreCase("success")){
				url = request.getSession().getAttribute("base_env_url")+"mileage/vehicle/"+registrationTO.getVin();
				JSONObject updateMileage = new JSONObject();
				updateMileage.put("error", "value");
				updateMileage.put("drivingConditions",registrationTO.getDrivingCondition());
				updateMileage.put("calcDate", "value");
				updateMileage.put("mileage", registrationTO.getMileage());
				updateMileage.put("milesPerDay", registrationTO.getMilesPerDay());
				updateMileage.put("carlineDesc", registrationTO.getCarlineDesc());
				postResponse = PostJsonObject.postJson(updateMileage, url,request);
				jsonResponse = new JSONObject(postResponse);
				if(jsonResponse.has("status") && jsonResponse.getString("status").equalsIgnoreCase("success")){
					//updateCarlineDesc(registrationTO);
					url = request.getSession().getAttribute("base_env_url")+"vehicle/"+registrationTO.getVin()+"/updateservicingdealer";
					JSONObject preferedDealer = new JSONObject();
					preferedDealer.put("dealerCode", registrationTO.getServiceDealerID());
					preferedDealer.put("customerId", loginUser.getCustomerId());
					preferedDealer.put("source", "y");
					postResponse = PostJsonObject.postJson(preferedDealer, url,request);
					jsonResponse = new JSONObject(postResponse);
					if(jsonResponse.has("status") && jsonResponse.getString("status").equalsIgnoreCase("success")){
						jsonResponse.put("description", "Vehicle Updated Successfully");
						getVehicleDetail(loginUser.getCustomerId());
						return SUCCESS;
						
					}
				}
			}
			return null;
		 }else {
				this.getServletResponse().getWriter().print(jsonResponse.getString("status"));
				return null;
			}
		}catch (Exception e) {
			log.error("Add More Vehicle Exception :- " + ExceptionUtils.getFullStackTrace(e));
			return null;
		}
		
	}
	
	
	
	public String updateMileage(){
		RegistrationTO selectedVehicle = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
		RegistrationTO loginDetail = (RegistrationTO) request.getSession().getAttribute("loginDetail");
		JSONObject updateMileage = new JSONObject();
		updateMileage.put("error", "value");
		updateMileage.put("drivingConditions",registrationTO.getDrivingCondition());
		updateMileage.put("calcDate", "value");
		updateMileage.put("mileage", registrationTO.getMileage());
		updateMileage.put("milesPerDay", registrationTO.getMilesPerDay());
		updateMileage.put("carlineDesc", registrationTO.getCarlineDesc());
		log.debug(">> UpdateMilage Triggered "+ updateMileage);
		
		try{
			String url = request.getSession().getAttribute("base_env_url")+"mileage/vehicle/"+selectedVehicle.getVin();
			String postResponse = PostJsonObject.postJson(updateMileage, url,request);
			JSONObject jsonObj = new JSONObject(postResponse);
			if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("success")){
				jsonObj.put("description", "Vehicle Updated Successfully");
				
				updateCarlineDesc(registrationTO);
				log.debug(">> updateCarlineDesc Triggered");
				
				upDatePreferredDealer(registrationTO);
				log.debug(">> upDatePreferredDealer Triggered");
			}
			else if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("Error")){
				//jsonObj.put("description", "Internal Error");
				//upDatePreferredDealer(registrationTO);
				this.getServletResponse().getWriter().print("error");
				return null;
			}
		}catch (Exception e) {
			log.error("update Milage Exception :- " + ExceptionUtils.getFullStackTrace(e));			
		}
		return SUCCESS;
	}
	
	public void upDatePreferredDealer(RegistrationTO registrationTO ) {
		try{
		RegistrationTO loginDetail = (RegistrationTO) request.getSession().getAttribute("loginDetail");
		RegistrationTO selectedVehicle = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
		String url = request.getSession().getAttribute("base_env_url")+"vehicle/"+selectedVehicle.getVin()+"/updateservicingdealer";
		JSONObject preferedDealer = new JSONObject();
		preferedDealer.put("dealerCode", registrationTO.getServiceDealerID());
		preferedDealer.put("customerId", loginDetail.getCustomerId());
		preferedDealer.put("source", "y");
		String postResponse = PostJsonObject.postJson(preferedDealer, url,request);
		JSONObject jsonResponse = new JSONObject(postResponse);
		getVehicleDetail(loginDetail.getCustomerId());
		}catch (Exception e) {
			log.error("update prefered Dealer Exception :- " + ExceptionUtils.getFullStackTrace(e));	
		}
		
	}
	
	public void updateCarlineDesc(RegistrationTO registrationTO){
		try{
			RegistrationTO loginDetail = (RegistrationTO) request.getSession().getAttribute("loginDetail");
			//RegistrationTO selectedVehicle = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
			
			String url = request.getSession().getAttribute("base_env_url")+"user/carline";
			
			log.debug("url "+url);
			
			JSONObject carlineDescObj = new JSONObject();
			
			if(loginDetail == null){
				carlineDescObj.put("vin", registrationTO.getVin());
				carlineDescObj.put("custId", registrationTO.getCustomerId());
				carlineDescObj.put("vtitle", registrationTO.getCarlineDesc());
			}
			else{
				carlineDescObj.put("vin", registrationTO.getVin());
				carlineDescObj.put("custId", loginDetail.getCustomerId());
				carlineDescObj.put("vtitle", registrationTO.getCarlineDesc());
			}
			String response = PostJsonObject.postJson(carlineDescObj, url,request);
			JSONObject jsonResponse = new JSONObject(response);
		}
		catch(Exception e){
			log.error("updateCarlineDesc Exception :- " + ExceptionUtils.getFullStackTrace(e));	
		}
	}
	
	public String fetchCarlineDesc(String vin){
		log.debug(">> Entering fetchCarlineDesc()");
		try{
			// Call webservice to get vehicle name
			String vehicleName = null;
			String carlineDescUrl = request.getSession().getAttribute("base_env_url")+"user/carline/" + vin;
			log.debug("fetchCarlineDesc URL : " + carlineDescUrl);
			String carlineDescJson = GetJsonObject.getWebServceObj(carlineDescUrl, request);
			log.debug("fetchCarlineDesc carlineDescJson :- " + carlineDescJson);	
			JSONObject carlineDescResponse = new JSONObject(carlineDescJson);
			if (carlineDescResponse.has("status") && carlineDescResponse.getString("status").equalsIgnoreCase("success")) {
				if (carlineDescResponse.has("carlineDesc") && carlineDescResponse.getString("carlineDesc") != null) {
					vehicleName = carlineDescResponse.getString("carlineDesc");
				}
			}
			log.debug(">> Exeting fetchCarlineDesc()");
			return vehicleName;
		}
		catch(Exception e){
			log.error("fetchCarlineDesc Exception :- " + ExceptionUtils.getFullStackTrace(e));	
			return null;
		}
	}
	
	public void updateProfile(){
		try{
		RegistrationTO loginDetail =  (RegistrationTO) request.getSession().getAttribute("loginDetail");
		registrationTO.setEmail(loginDetail.getEmail());
		JSONObject updateProfile = new JSONObject();
		updateProfile.put("returnedEmail", "false");
		updateProfile.put("addressValidation", "true");
		updateProfile.put("addOnZip", "");
		updateProfile.put("source", "MMG");
		updateProfile.put("error", "");
		updateProfile.put("addressType", "");
		updateProfile.put("city", registrationTO.getCity());
		updateProfile.put("country", "");
		updateProfile.put("doNotCall", "");
		updateProfile.put("doNotEmail", "");
		updateProfile.put("email", loginDetail.getEmail());
		updateProfile.put("title", registrationTO.getTitle());
		updateProfile.put("firstName", registrationTO.getFirstName());
		updateProfile.put("homePhone", "");
		updateProfile.put("invalidAddressFlag", "");
		updateProfile.put("lastName", registrationTO.getLastName());
		updateProfile.put("mobilePhone", registrationTO.getMobilePhone());
		updateProfile.put("state", registrationTO.getState());
		updateProfile.put("street1Address", registrationTO.getStreet1address());
		updateProfile.put("street2Address", registrationTO.getStreet2address());
		updateProfile.put("workPhone", "");
		updateProfile.put("workPhoneExt", "");
		updateProfile.put("zip", registrationTO.getZip());
		updateProfile.put("doNotContact", "");
		String url = request.getSession().getAttribute("base_env_url")+"user/"+loginDetail.getCustomerId()+"/updatedetails";
		String postResponse = PostJsonObject.postJson(updateProfile, url,request);
		JSONObject jsonObj = new JSONObject(postResponse);
		if(jsonObj.getString("status").equalsIgnoreCase("success") && jsonObj.has("status")){
			JSONObject updateDetail = new JSONObject();
			updateDetail.put("customerId", loginDetail.getCustomerId());
			getLoginDetail(updateDetail);
			this.getServletResponse().getWriter().print("success");
		}
		else{
			this.getServletResponse().getWriter().print("error");
		}
		}catch (Exception e) {
			log.error("update Profile Exception :- " + ExceptionUtils.getFullStackTrace(e));	
		}
	}
	
	public String registerNewByPreferedDealer(){
		try {
			RegistrationTO registrationTO1 = new RegistrationTO();
			registrationTO1 = (RegistrationTO) request.getSession().getAttribute("RegistrationTO");
			registrationTO1.setServiceDealerID(registrationTO.getServiceDealerID());
			registrationTO1.setDealerName(registrationTO.getDealerName());
			registrationTO1.setDealerStreet(registrationTO.getDealerStreet());
			registrationTO1.setDealerPhone(registrationTO.getDealerPhone());
			registrationTO1.setDealerDistance(registrationTO.getDealerDistance());
			 request.getSession().setAttribute("RegistrationTO", registrationTO1);
			 request.getSession().setAttribute("registerPreferedDealer", null);
			
		} catch (Exception e) {
			log.error("registerNewByPreferedDealer Exception :- " + ExceptionUtils.getFullStackTrace(e));	
		}
		
		return SUCCESS;
	}
	
	public String addVehiclePreferedDealer(){
		try {
			RegistrationTO registrationTO1 = new RegistrationTO();
			registrationTO1 = (RegistrationTO) request.getSession().getAttribute("RegistrationTO");
			registrationTO1.setServiceDealerID(registrationTO.getServiceDealerID());
			
			String dlrName = registrationTO.getDealerName();
			if(dlrName != null && dlrName.indexOf("@@@") == 0) {
				dlrName = dlrName.replace("@@@", "#");
				registrationTO.setDealerName(dlrName);
			}
			registrationTO1.setDealerName(registrationTO.getDealerName());
			
			registrationTO1.setDealerStreet(registrationTO.getDealerStreet());
			registrationTO1.setDealerPhone(registrationTO.getDealerPhone());
			registrationTO1.setDealerCity(registrationTO.getDealerCity());
			registrationTO1.setDealerState(registrationTO.getDealerState());
			registrationTO1.setDealerZip(registrationTO.getDealerZip());
			registrationTO1.setDealerDistance(registrationTO.getDealerDistance());
			request.getSession().setAttribute("RegistrationTO", registrationTO1);
			request.getSession().setAttribute("addPreferedDealer", null);
			if(("Add").equalsIgnoreCase(registrationTO1.getEditUpdateFlag())){
				return SUCCESS;
			}else if(("Prefer").equalsIgnoreCase(registrationTO1.getEditUpdateFlag())){
				RegistrationTO selectedVehicle = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
				registrationTO1.setVin(selectedVehicle.getVin());
				upDatePreferredDealer(registrationTO1);
				return INPUT;
			}else{
				return LOGIN;
			}
			
		} catch (Exception e) {
			log.error("add Vehicle Prefered Dealer Exception :- " + ExceptionUtils.getFullStackTrace(e));
			return null;
		}
		
	}
	public String addPreferedDealer(){
		try {
			 if(registrationTO.getFlag() != null){
				 request.getSession().setAttribute("registerPreferedDealer", registrationTO.getFlag());
				 request.getSession().setAttribute("RegistrationTO", registrationTO);
			 }
			 else{
				 request.getSession().setAttribute("addPreferedDealer", registrationTO.getAddVehicleFlag());
				 request.getSession().setAttribute("RegistrationTO", registrationTO);
			 }
		} catch (Exception e) {
			log.error("add Prefered Dealer Exception :- " + ExceptionUtils.getFullStackTrace(e));
		}
		
		return SUCCESS;
	}
	
	
	public String editPreferDealer(){
		try {
			List<RegistrationTO> vehicalsList = (List<RegistrationTO>) request.getSession().getAttribute("vehicalsList");
			if(vehicalsList==null){
				return NONE;
			}
		} catch (Exception e) {
			log.error("edit Prefered Dealer Exception :-" + ExceptionUtils.getFullStackTrace(e));
		}
		
		return SUCCESS;
	}
	public String addVehicle(){
		try {
			
		} catch (Exception e) {
		}
		
		return SUCCESS;
	}
	public String uploadImage(){
		try {
			RegistrationTO selectedVehicle = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
			RegistrationTO loginDetail = (RegistrationTO) request.getSession().getAttribute("loginDetail");
			/*String url = request.getSession().getAttribute("base_env_url")+"user/"+loginDetail.getCustomerId()+"/updatedetails";*/
			log.debug("uploadImage() :- " + registrationTO.getUploadedFile());
			if(registrationTO.getUploadedFile() != null){
				String url = request.getSession().getAttribute("base_env_url")+"vehicle/saveVehicleImageDetails/vin/"+selectedVehicle.getVin()+"/customerId/"+loginDetail.getCustomerId();
				log.debug("MMG Get Image Name Service :- "+url);
				String postResponse = GetJsonObject.getWebServceObj(url,request);
				JSONObject jsonResponse = new JSONObject(postResponse);
				log.debug("Image Name  :- "+postResponse);
				String fileName  = jsonResponse.getString("imgPath");
				//String fileName  = registrationTO.getImgName();
				/**
				 * 
				 * Image Upload Path
				 */
				String filePath = Utility.getProperties("env.properties").getProperty("imageUploadPath");
				log.debug("filePath :- "+filePath);
				 File f = new File(filePath);
				if (f.exists()){
					log.debug("Upload Folder already Exists >>>>>>>>>>>>>>>>>");
				}else{
					f.mkdirs();
					log.debug("Upload Folder not Exists >>>>>>>>>>>>>>>>> created");
				}
				log.debug("Server File path :- " + filePath);
		        File fileToCreate = new File(filePath, fileName);
		        log.debug("Server file Name :- " + fileName);
				FileUtils.copyFile(registrationTO.getUploadedFile(), fileToCreate);
				
				String fileURL = "http://test.mymazda.com/MyMazdaWeb/images/user_vehicle_images/"+fileName;
				
				request.getSession().setAttribute("imagePath", fileURL);
				log.debug("Uploaded file URL:>>>>>>>>>>>>>>>>>>>>>>>>" + fileURL);
			}
		} catch (Exception e) {
			System.err.println(e);
			log.error("uploadImage # Error occured :- " + ExceptionUtils.getFullStackTrace(e));
		}
	    return "success";  
	}
	
	public String login() {
		log.debug("*********************");
		return LOGIN;
	}
	
	public String editProfile()		{
		return SUCCESS;
	}
	
	public List<RegistrationTO> getLoginVehicalList() {
		return loginVehicalList;
	}

	public void setLoginVehicalList(List<RegistrationTO> loginVehicalList) {
		this.loginVehicalList = loginVehicalList;
	}

	public List<RegistrationTO> getVehicalDetailsList() {
		return vehicalDetailsList;
	}

	public void setVehicalDetailsList(List<RegistrationTO> vehicalDetailsList) {
		this.vehicalDetailsList = vehicalDetailsList;
	}

	public HttpServletResponse getServletResponse() {
		return servletResponse;
	}

	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	public List<LocateDealerTO> getDealerList() {
		return dealerList;
	}

	public void setDealerList(List<LocateDealerTO> dealerList) {
		this.dealerList = dealerList;
	}

	/**
	 * @return the dealerListByName
	 */
	public List<LocateDealerTO> getDealerListByName() {
		return dealerListByName;
	}

	/**
	 * @param dealerListByName the dealerListByName to set
	 */
	public void setDealerListByName(List<LocateDealerTO> dealerListByName) {
		this.dealerListByName = dealerListByName;
	}

	/**
	 * @return the dealerListByState
	 */
	public List<LocateDealerTO> getDealerListByState() {
		return dealerListByState;
	}

	/**
	 * @param dealerListByState the dealerListByState to set
	 */
	public void setDealerListByState(List<LocateDealerTO> dealerListByState) {
		this.dealerListByState = dealerListByState;
	}

	/**
	 * @return the dealerListLocation
	 */
	public List<LocateDealerTO> getDealerListLocation() {
		return dealerListLocation;
	}

	/**
	 * @param dealerListLocation the dealerListLocation to set
	 */
	public void setDealerListLocation(List<LocateDealerTO> dealerListLocation) {
		this.dealerListLocation = dealerListLocation;
	}
	
	public String  registerpage(){
		
		return SUCCESS;
	}
	public String editVehicle() {
		
			List<RegistrationTO> loginUser = (List<RegistrationTO>) request.getSession().getAttribute("loginUser");
			if(loginUser != null){
				List<RegistrationTO> vehicalsList = (List<RegistrationTO>) request.getSession().getAttribute("vehicalsList");
				
				if(vehicalsList==null){
					return NONE;
				}
			
				RegistrationTO login = loginUser.get(0);
				RegistrationTO selectedVehical = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
				log.debug("***** Selected Vehical from session **** " + selectedVehical);
				RegistrationTO loginUserDetail = new RegistrationTO();
				loginUserDetail =  (RegistrationTO) request.getSession().getAttribute("loginDetail");
				log.debug("***** login user detais from session **** " + loginUserDetail);
			}
		
		return SUCCESS;
	}
	public String maintainSchedule(){
		
		//For enabling/disabling eGift tab
		RegistrationTO loginDetail = (RegistrationTO) request.getSession().getAttribute("loginDetail");
		 RegistrationTO selectedVehical = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
		 
		int size = 0;
		request.getSession().setAttribute("egiftTab", 0);
		request.getSession().setAttribute("egiftTabOnRelPage", 0);
		request.getSession().setAttribute("egiftTabOnSelectVeh", 0);
		
		String eGiftUrl = request.getSession().getAttribute("base_env_url")+"user/getUserEgifts/"+loginDetail.getCustomerId()+"/"+selectedVehical.getVin();
		String jsonEgift = GetJsonObject.getWebServceObj(eGiftUrl,request);
		JSONObject jsonObjEgift = new JSONObject(jsonEgift);
		if(jsonObjEgift.has("status") && jsonObjEgift.getString("status").equalsIgnoreCase("Success")){
			if(jsonObjEgift.getString("count").equalsIgnoreCase("0")) {
				egiftTabOnSelectMaintSch.add("0");
				size = egiftTabOnSelectMaintSch.size();
				request.getSession().setAttribute("egiftTabOnMaintSchChange", size);
			}
			else{
				request.getSession().setAttribute("egiftTabOnMaintSchChange", 0);
			}
		}
		egiftTabOnSelectMaintSch = null;
		
		return SUCCESS;
	}
	public String addVehical(){
		return SUCCESS;
	}
	
	
	//Change Password Functionality	
	public String changePassword() {
		return SUCCESS;
	}	
	public void submitPasswordChange() throws IOException {
		try{
			RegistrationTO loginDetail = (RegistrationTO) request.getSession().getAttribute("loginDetail");
			JSONObject changePassword = new JSONObject();
			changePassword.put("currentPassword", request.getParameter("currentPassword"));
			changePassword.put("newPassword", request.getParameter("newPassword"));
			changePassword.put("confirmPassword", request.getParameter("confirmPassword"));
			
			log.debug("changePassword json: " + changePassword);
			
			String url = request.getSession().getAttribute("base_env_url")+"user/"+loginDetail.getEmail()+"/changePassword";
			String postResponse = PostJsonObject.postJson(changePassword, url,request);
			JSONObject jsonObj = new JSONObject(postResponse);
			
			if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("success")){
				this.getServletResponse().getWriter().print("success");
			} else{
				this.getServletResponse().getWriter().print("error");
			}
			
		} catch (Exception e) {
			log.error("Exception at submitPasswordChange() :- " + ExceptionUtils.getFullStackTrace(e));
			this.getServletResponse().getWriter().print("error");
		}
	}
	
	
	//Delete Vehicle Functionality	
	public void deleteVehicle() throws IOException {
		try{
			RegistrationTO loginDetail = (RegistrationTO) request.getSession().getAttribute("loginDetail");
			Date date = new Date();
			String formatDate = new SimpleDateFormat("yyyy-MM-dd").format( date);
			
			JSONObject deleteVehicle = new JSONObject();
			
			deleteVehicle.put("newCustomerId", "");
			deleteVehicle.put("disposalFlag", "0");
			deleteVehicle.put("disposalReason", "Transfer");
			deleteVehicle.put("resaleDate", formatDate);
			deleteVehicle.put("source", "MMG");
			deleteVehicle.put("customerId", loginDetail.getCustomerId());
			//deleteVehicle.put("custid", loginDetail.getCustomerId());
			//deleteVehicle.put("vin", request.getParameter("vin"));
			
			log.debug("deleteVehicle json: " + deleteVehicle);
						
			String url = request.getSession().getAttribute("base_env_url")+"vehicle/"+request.getParameter("vin")+"/updateownership";
			//String url = request.getSession().getAttribute("base_env_url")+"user/"+loginDetail.getCustomerId()+"/deleteVehicle";
			String postResponse = PostJsonObject.postJsonWithCustomHeaders(deleteVehicle, url, request);
			JSONObject jsonObj = new JSONObject(postResponse);
			
			if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("success")){
				getVehicleDetail(loginDetail.getCustomerId());
				this.getServletResponse().getWriter().print("success");
			} else{
				this.getServletResponse().getWriter().print("error");
			}
			
		} catch (Exception e) {
			log.error("Exception at eleteVehicle() :- " + ExceptionUtils.getFullStackTrace(e));
			this.getServletResponse().getWriter().print("error");
		}
		//return SUCCESS;
	}
	
}





