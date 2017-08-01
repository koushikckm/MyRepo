package com.mazda.configuration.common.action;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mazda.common.utility.PostJsonObject;
import com.mazda.common.utility.mail.GetJsonObject;
import com.mazda.configuration.common.transferobject.RegistrationTO;
import com.mazda.configuration.common.transferobject.ServiceHistoryTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ServiceHistoryAction extends ActionSupport implements ModelDriven<ServiceHistoryTO>, ServletResponseAware{
	
	static final Logger log = Logger.getLogger(ServiceHistoryAction.class); 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ServiceHistoryTO serviceHistoryTO;
	 List<ServiceHistoryTO> serviceHistoryDetailList = new ArrayList<ServiceHistoryTO>();
	 List<ServiceHistoryTO> mazdaServiceHistoryDetailList = new ArrayList<ServiceHistoryTO>();
	 List<ServiceHistoryTO> serviceTypeList = new ArrayList<ServiceHistoryTO>();
	 final ActionContext actionContext 	= ActionContext.getContext();
	 private HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST); 
	 private HttpServletResponse servletResponse;
	 List egiftTabOnSelectServHistVehicle = new ArrayList();
	 
	public ServiceHistoryTO getModel() {
		serviceHistoryTO = new ServiceHistoryTO();
		return serviceHistoryTO;
	}
	
	public String execute() {
		log.debug(">> Entering execute()");
		try{
		
		@SuppressWarnings("unchecked")
		List<RegistrationTO> loginUser = (List<RegistrationTO>) request.getSession().getAttribute("loginUser");
		if(loginUser != null){
			List<RegistrationTO> vehicalsList = (List<RegistrationTO>) request.getSession().getAttribute("vehicalsList");
			if(vehicalsList==null){
				return NONE;
			}
			
		RegistrationTO login = loginUser.get(0);
		 RegistrationTO selectedVehical = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
		 
		 //checkIfEligibleForEgift();
		 /**
		  * Checking if eligible for egift 
		  */
		 
		 	int size = 0;
			request.getSession().setAttribute("egiftTab", 0);
			request.getSession().setAttribute("egiftTabOnRelPage", 0);
			request.getSession().setAttribute("egiftTabOnSelectVeh", 0);
			
			String eGiftUrl = request.getSession().getAttribute("base_env_url")+"user/getUserEgifts/"+login.getCustomerId()+"/"+selectedVehical.getVin();
			String jsonEgift = GetJsonObject.getWebServceObj(eGiftUrl,request);
			JSONObject jsonObjEgift = new JSONObject(jsonEgift);
			if(jsonObjEgift.has("status") && jsonObjEgift.getString("status").equalsIgnoreCase("Success")){
				log.debug(">> Entered egifts");
				if(jsonObjEgift.getString("count").equalsIgnoreCase("0")) {
					egiftTabOnSelectServHistVehicle.add("0");
					size = egiftTabOnSelectServHistVehicle.size();
					request.getSession().setAttribute("egiftTabOnServHistChange", size);
				}
				else{
					request.getSession().setAttribute("egiftTabOnServHistChange", 0);
				}
			}
			egiftTabOnSelectServHistVehicle = null;
			log.debug(">> Exeting egifts");
		 
		 
		 /**
		     * History Added  By user
		     */
		 String url = request.getSession().getAttribute("base_env_url")+"servicehistory/getServiceRecord/"+login.getCustomerId()+"/"+selectedVehical.getVin();
		 String json = GetJsonObject.getWebServceObj(url,request);
	    JSONObject jsonObj = new JSONObject(json);
	    /**
	     * Convert  json into arraylist
	     */
	    if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("Success")){
	    	log.debug(">> Entered User entered service history");
	    	if(jsonObj.has("serviceRecords")) {
	    	JSONArray serviceRecordArr = jsonObj.getJSONArray("serviceRecords");
	    	for(int i=0; i<serviceRecordArr.length(); i++){
	    		JSONObject serviceDetail = serviceRecordArr.getJSONObject(i);
	    		ServiceHistoryTO vehicleService = new ServiceHistoryTO();
	    		vehicleService.setMileage(serviceDetail.getInt("mileage"));
	    		vehicleService.setAmount(serviceDetail.getString("amount"));
	    		vehicleService.setOpCode(serviceDetail.getString("opCode"));
	    		vehicleService.setServiceType(serviceDetail.getString("serviceType"));
	    		vehicleService.setMusaRefId(serviceDetail.getInt("musaRefId"));
	    		vehicleService.setServiceLocation(serviceDetail.getString("serviceFacility"));
	    		JSONObject date = serviceDetail.getJSONObject("serviceDate");
	    		vehicleService.setServiceDate(date.getInt("month")+"/"+date.getInt("day")+"/"+date.getInt("year"));
	    		JSONArray comments = serviceDetail.getJSONArray("comments");
	    		List<String> comments1 = new ArrayList<String>();
	    		String comm ="";
	    		for(int j=0; j<comments.length(); j++){
	    			comm = comm +comments.getString(j);
	    		}
				log.debug(">> Comment String: "+ comm);
	    		String serviceComment = StringUtils.EMPTY;
	    		try{
	    			URLDecoder.decode(comm,"UTF-8");
	    		}catch(Exception e){
	    			comm =  handleException(comm);
	    		} 
				serviceComment = URLDecoder.decode(comm,"UTF-8").replaceAll("%0A", "\n");
	    		vehicleService.setComment(serviceComment);
	    		
	    		vehicleService.setComments(comments1);
	    		vehicleService.setVin(jsonObj.getString("vin"));
	    		vehicleService.setHistoryBy("user");
	    		serviceHistoryDetailList.add(vehicleService);
	    	}
	      }
	   }
	   log.debug(">> Exeting User entered service history");
	    
	    /**
	     * History Added By Mazda
	     */
	    
	    url = request.getSession().getAttribute("base_env_url")+"servicehistory/user/"+login.getCustomerId()+"/vehicle/"+selectedVehical.getVin();
		json = GetJsonObject.getWebServceObj(url,request);
	    jsonObj = new JSONObject(json);
	    if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("Success")){
	    	log.debug(">> Entered dealer service history");
	    	if(jsonObj.has("servicedetails")) { 
		    JSONObject jsonServiceDetailObj = jsonObj.getJSONObject("servicedetails");
		    JSONArray jsonVehicalServiceList = jsonServiceDetailObj.getJSONArray("vehicleServiceList");
		    for(int i =0; i<jsonVehicalServiceList.length(); i++){
		    	ServiceHistoryTO vehicalService = new ServiceHistoryTO();
		    	JSONObject vehicalServiceObj = (JSONObject) jsonVehicalServiceList.get(i);
		    	vehicalService.setServiceDealerId((vehicalServiceObj.getInt("serviceDealerId")));
		    	vehicalService.setMileage( vehicalServiceObj.getInt("mileage"));
		    	String  date = vehicalServiceObj.getString("serviceDate");
		    	char[] d =date.toCharArray();
		    	String da = ""+d[0]+d[1]+"/"+d[2]+d[3]+"/"+d[4]+d[5]+d[6]+d[7];
		    	vehicalService.setServiceDate(da);
		    	ServiceHistoryTO dealserDetail = (ServiceHistoryTO) request.getSession().getAttribute("dealerDetail");
		    	int dealerID =(dealserDetail !=null ? dealserDetail.getServiceDealerId():0);
		    	if(dealerID != vehicalService.getServiceDealerId()){
		    	url = request.getSession().getAttribute("base_env_url")+"search/dealer/code/"+vehicalService.getServiceDealerId();
		    	json = GetJsonObject.getWebServceObj(url,request);
		    	JSONObject jsonPreferedDealer = new JSONObject(json);
				if(jsonPreferedDealer.has("status") && jsonPreferedDealer.getString("status").equalsIgnoreCase("Success")){
					JSONObject dealerObj = jsonPreferedDealer.getJSONObject("dealer");
					vehicalService.setDealerAddress(dealerObj.getString("address"));
					vehicalService.setDealerPhone(dealerObj.getString("phone"));
					vehicalService.setDealerName(dealerObj.getString("name"));
					vehicalService.setDealerZip(dealerObj.getString("zip"));
					vehicalService.setDealerState(dealerObj.getString("state"));
					vehicalService.setDealerCity(dealerObj.getString("city"));
					request.getSession().setAttribute("dealerDetail", vehicalService);
				}
				}else{
					dealserDetail = (ServiceHistoryTO) request.getSession().getAttribute("dealerDetail");
					vehicalService.setDealerAddress(dealserDetail.getDealerAddress());
					vehicalService.setDealerPhone(dealserDetail.getDealerPhone());
					vehicalService.setDealerName(dealserDetail.getDealerName());
					vehicalService.setDealerZip(dealserDetail.getDealerZip());
					vehicalService.setDealerState(dealserDetail.getDealerState());
					vehicalService.setDealerCity(dealserDetail.getDealerCity());
				}
		    	vehicalService.setServiceType( vehicalServiceObj.getString("serviceTypeCode"));
		    	vehicalService.setServiceType( vehicalServiceObj.getString("serviceTypeDesc"));
		    	vehicalService.setHistoryBy(null);
		    	mazdaServiceHistoryDetailList.add(vehicalService);
		    }
		    request.getSession().removeAttribute("dealerDetail");
	    	}
	    }
	    log.debug(">> Exeting dealer service history");    
	    
	    
	    
	    url = request.getSession().getAttribute("base_env_url")+"customercare/servicetypes";
	    json = GetJsonObject.getWebServceObj(url,request);
	    jsonObj = new JSONObject(json);
	    if(jsonObj.has("serviceTypes")){
	    	log.debug(">> Entered servicetype");
	    	JSONArray serviceTypeArr = jsonObj.getJSONArray("serviceTypes");
		    for(int j=0;j<serviceTypeArr.length(); j++){
		    	ServiceHistoryTO serviceType = new ServiceHistoryTO();
		    	String[] serviceTypeDet = serviceTypeArr.getString(j).split(",");
		    	serviceType.setOpCode(serviceTypeDet[0]);
		    	serviceType.setServiceType(serviceTypeDet[1]);
		    	serviceTypeList.add(serviceType);
		    }
	    }
	    log.debug(">> Exeting servicetype");
	}
	    int size = serviceHistoryDetailList.size();
		request.setAttribute("noOfPages", size);
		 if(serviceHistoryDetailList.size() ==0 && mazdaServiceHistoryDetailList.size()==0){
			 addActionMessage("We are not able to show service history matching your profile. Please call 1-800-222-5500 if you have any question or add a new service record by tapping the icon at the top right corner.");
		    }
		 
		log.debug(">> Exeting execute()");
		return SUCCESS;
		} catch(Exception e){
			log.error("execute() Exception :- " + ExceptionUtils.getFullStackTrace(e));
		}
		return null;
	}
	
	private String handleException(String comm) {
		LOG.debug("Exception while decoding comment So , Encoding again");
		try {
			return URLEncoder.encode(comm, "UTF-8");
		} catch (Exception e) {
			log.error("Exception while encoding decoded string : "+ ExceptionUtils.getFullStackTrace(e));
		}
		return comm;
	}
	
	public String showUpdateSelectedVehicle() {
		log.debug(">> Entering showUpdateSelectedVehicle()");
		try {
			RegistrationAction registrationAction = new RegistrationAction();
			registrationAction.keepUpdateOfSelectedVehical(serviceHistoryTO.getVin());
			execute();
		} catch (Exception e) {
			log.error("showUpdateSelectedVehicle() Exception :- " + ExceptionUtils.getFullStackTrace(e));
			return ERROR;
		}
		log.debug(">> Exeting showUpdateSelectedVehicle()");
		return SUCCESS;
		
	}

	public String saveServiceHistory() {
		log.debug(">> Entering saveServiceHistory()");
		try {
			String url = request.getSession().getAttribute("base_env_url")+"servicehistory/uploadServiceRecord";
			RegistrationTO loginDetail = (RegistrationTO) request.getSession().getAttribute("loginDetail");
			RegistrationTO selectedVehical = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
			String date = serviceHistoryTO.getServiceDate();
			String[] monDayYea = date.split("/");
			String amount = serviceHistoryTO.getAmount();
			amount = amount.replaceAll("\\$", "").trim();
			JSONObject serviceHistory = new JSONObject();
			serviceHistory.put("mileage", serviceHistoryTO.getMileage());
			serviceHistory.put("vin", selectedVehical.getVin());
			serviceHistory.put("custId", loginDetail.getCustomerId());
			serviceHistory.put("month", monDayYea[0]);
			serviceHistory.put("year", monDayYea[2] );
			serviceHistory.put("day", monDayYea[1]);
			serviceHistory.put("amount", amount);
			serviceHistory.put("serviceLocation", serviceHistoryTO.getServiceLocation());
			serviceHistory.put("serviceType", serviceHistoryTO.getServiceType());
			serviceHistory.put("opCode", serviceHistoryTO.getOpCode());
			serviceHistory.put("comments", serviceHistoryTO.getComment());
			String postResponse = PostJsonObject.postJson(serviceHistory, url,request);
			JSONObject	jsonResponse = new JSONObject(postResponse);
			if(jsonResponse.getString("status").equalsIgnoreCase("failure")) {
				this.getServletResponse().getWriter().print("error");
				return null;
			}else
			showUpdateSelectedVehicle();
		} catch (Exception e) {
			log.error("saveServiceHistory() Exception :- " + ExceptionUtils.getFullStackTrace(e));
		}
		log.debug(">> Exeting saveServiceHistory()");
		return SUCCESS;
	}
	
	public String updateRecord() {
		log.debug(">> Entering updateRecord()");
		try {
			String url = request.getSession().getAttribute("base_env_url")+"servicehistory/updateServiceRecord";
			RegistrationTO loginDetail = (RegistrationTO) request.getSession().getAttribute("loginDetail");
			RegistrationTO selectedVehical = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
			String date = serviceHistoryTO.getServiceDate();
			String[] monDayYea = date.split("/");
			String amount = serviceHistoryTO.getAmount();
			amount = amount.replaceAll("\\$", "").trim();
			JSONObject serviceHistory = new JSONObject();
			serviceHistory.put("vin", selectedVehical.getVin());
			serviceHistory.put("custId", loginDetail.getCustomerId());
			serviceHistory.put("month", monDayYea[0]);
			serviceHistory.put("day", monDayYea[1]);
			serviceHistory.put("year", monDayYea[2]);
			serviceHistory.put("mileage", serviceHistoryTO.getMileage());
			serviceHistory.put("amount", amount);
			serviceHistory.put("serviceLocation", serviceHistoryTO.getServiceLocation());
			serviceHistory.put("serviceType", serviceHistoryTO.getServiceType());
			serviceHistory.put("opCode", serviceHistoryTO.getOpCode());
			serviceHistory.put("comments", serviceHistoryTO.getComment().replaceAll("\r\n", "\n"));
			serviceHistory.put("musaRefId", serviceHistoryTO.getServiceDealerId());
			String postResponse = PostJsonObject.postJson(serviceHistory, url,request);
			JSONObject	jsonResponse = new JSONObject(postResponse);
			showUpdateSelectedVehicle();
			
		} catch (Exception e) {
			log.error("updateRecord() Exception :- " + ExceptionUtils.getFullStackTrace(e));
		}
		log.debug(">> Exeting updateRecord()");
		return SUCCESS;
	}
	public String deleteRecord() {
		log.debug(">> Entering deleteRecord()");
		try {
			//http://mobilegaragetest.mazdausa.com/MyMazdaGarage/client/servicehistory/delServiceRecord/refId/{refId}/vin/{vin}
			String url = request.getSession().getAttribute("base_env_url")+"servicehistory/delServiceRecord/refId/"+serviceHistoryTO.getMusaRefId()+"/vin/"+serviceHistoryTO.getVin();
			String json = GetJsonObject.getWebServceObj(url,request);
		    JSONObject jsonObj = new JSONObject(json);
		    showUpdateSelectedVehicle();
		} catch (Exception e) {
			log.error("deleteRecord() Exception :- " + ExceptionUtils.getFullStackTrace(e));
		}
		log.debug(">> Exeting deleteRecord()");
		return SUCCESS;
	}
	
	public ServiceHistoryTO getServiceHistoryTO() {
		return serviceHistoryTO;
	}

	public void setServiceHistoryTO(ServiceHistoryTO serviceHistoryTO) {
		this.serviceHistoryTO = serviceHistoryTO;
	}

	public List<ServiceHistoryTO> getServiceHistoryDetailList() {
		return serviceHistoryDetailList;
	}

	public void setServiceHistoryDetailList(
			List<ServiceHistoryTO> serviceHistoryDetailList) {
		this.serviceHistoryDetailList = serviceHistoryDetailList;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<ServiceHistoryTO> getServiceTypeList() {
		return serviceTypeList;
	}

	public void setServiceTypeList(List<ServiceHistoryTO> serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}

	/**
	 * @return the servletResponse
	 */
	public HttpServletResponse getServletResponse() {
		return servletResponse;
	}

	/**
	 * @param servletResponse the servletResponse to set
	 */
	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	public List<ServiceHistoryTO> getMazdaServiceHistoryDetailList() {
		return mazdaServiceHistoryDetailList;
	}

	public void setMazdaServiceHistoryDetailList(
			List<ServiceHistoryTO> mazdaServiceHistoryDetailList) {
		this.mazdaServiceHistoryDetailList = mazdaServiceHistoryDetailList;
	}


}
