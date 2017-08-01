package com.mazda.configuration.common.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.StrutsStatics;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mazda.common.utility.Utility;
import com.mazda.common.utility.mail.GetJsonObject;
import com.mazda.configuration.common.transferobject.RecallDetailsTO;
import com.mazda.configuration.common.transferobject.RecallTO;
import com.mazda.configuration.common.transferobject.RegistrationTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RecallAction extends ActionSupport implements ModelDriven<RecallTO>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final ActionContext actionContext 	= ActionContext.getContext();
	private HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST); 
	//private List<RecallTO> alertList = new ArrayList<RecallTO>();
	private List<RecallDetailsTO> alertList = new ArrayList<RecallDetailsTO>();

	RecallTO recallTO;
	
	
	public RecallTO getModel() {
		recallTO = new RecallTO();
		return recallTO;
	}
	
		public String recall() {
		try {
		List<RegistrationTO> loginUser = (List<RegistrationTO>) request.getSession().getAttribute("loginUser");
		if(loginUser != null){
			 List<RegistrationTO> vehicalsList = (List<RegistrationTO>) request.getSession().getAttribute("vehicalsList");
				if(vehicalsList==null){
					return NONE;
				}
			RegistrationTO selectedVehical = (RegistrationTO)request.getSession().getAttribute("selectedVehical");
			 //Properties properties 	= Utility.getProperties("env.properties");
			 String url = request.getSession().getAttribute("base_env_url")+"vehicle/recallalert/"+selectedVehical.getVin();
			
			//String url 			= properties.getProperty("baseUrl")+"vehicle/recallalert/1YVHP80A795M43097";
		    String json = GetJsonObject.getWebServceObj(url,request);
		    JSONObject jsonObj = new JSONObject(json);
		    if(jsonObj.getString("status").equalsIgnoreCase("error")){
		    	addActionMessage("No recall notifications were found for this vehicle.");
		    }
		    if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("success")){
		    	JSONArray jsonAlertArr = jsonObj.getJSONArray("alerts");
		    	for(int i=0; i<jsonAlertArr.length();i++){
		    		JSONObject jsonRecallObj = jsonAlertArr.getJSONObject(i);
		    		RecallDetailsTO recallAlert = new RecallDetailsTO();
		    		recallAlert.setStartDate(jsonRecallObj.getString("startDate"));
		    		recallAlert.setRecallSSPNo(jsonRecallObj.getString("recallSSPNo"));
		    		recallAlert.setRecallSSPDesc(jsonRecallObj.getString("recallSSPDesc"));
		    		alertList.add(recallAlert);
		    	}
		    }
	      }
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	public String recall1() {
		try {
		List<RegistrationTO> loginUser = (List<RegistrationTO>) request.getSession().getAttribute("loginUser");
		if(loginUser != null){
			List<RegistrationTO> vehicalsList = (List<RegistrationTO>) request.getSession().getAttribute("vehicalsList");
				if(vehicalsList==null){
					return NONE;
				}
			RegistrationTO selectedVehical = (RegistrationTO)request.getSession().getAttribute("selectedVehical");
			 //Properties properties 	= Utility.getProperties("env.properties");
			String url =  "";
			
			String requestURL = request.getRequestURL().toString().toLowerCase();				
				
			if(requestURL.contains("qa.mymazda.com") || requestURL.contains("stg.mymazda.com")){
				url = "http://portaltest.mazdausa.com/m276/RecallService/api/vin/"+selectedVehical.getVin()+"?api_key=bSk1pD_p9WEP_TxEc3hvA_QZY-0Xojgi_qlxHG1IyleXMmg";
			}else if(requestURL.contains("test.mymazda.com") || requestURL.contains("approval.mymazda.com")){
				url = "http://portaltest.mazdausa.com/m371/RecallService/api/vin/"+selectedVehical.getVin()+"?api_key=bSk1pD_p9WEP_TxEc3hvA_QZY-0Xojgi_qlxHG1IyleXMmg";
			}else{
				url = "http://portal.mazdausa.com/m176/RecallService/api/vin/"+selectedVehical.getVin()+"?api_key=bSk1pD_p9WEP_TxEc3hvA_QZY-0Xojgi_qlxHG1IyleXMmg";
			}
				
		    String json = GetJsonObject.getWebServceObj(url,request);
		    JSONObject jsonObj = new JSONObject(json);
		    if(jsonObj.has("status") && jsonObj.getBoolean("status") == true && jsonObj.has("recalls_available") && jsonObj.getBoolean("recalls_available") == false){
		    	addActionMessage("No recall notifications were found for this vehicle.");
		    }
		    if(jsonObj.has("status") && jsonObj.getBoolean("status") == true && jsonObj.has("recalls_available") && jsonObj.getBoolean("recalls_available") == true){
	    		RecallTO recallAlert = new RecallTO();
		    	JSONArray jsonAlertArr = jsonObj.getJSONArray("recalls");
		    	for(int i=0; i<jsonAlertArr.length();i++){
		    		JSONObject jsonRecallObj = jsonAlertArr.getJSONObject(i);
		    		RecallDetailsTO recallDetails = new RecallDetailsTO();
		    		recallDetails.setNhtsa_recall_number(jsonRecallObj.getString("nhtsa_recall_number"));
		    		recallDetails.setMfr_recall_number(jsonRecallObj.getString("mfr_recall_number"));
		    		recallDetails.setRecall_date(jsonRecallObj.getString("recall_date"));
		    		recallDetails.setRecall_description(jsonRecallObj.getString("recall_description"));
		    		recallDetails.setSafety_risk_description(jsonRecallObj.getString("safety_risk_description"));
		    		recallDetails.setRemedy_description(jsonRecallObj.getString("remedy_description"));
		    		recallDetails.setMfr_recall_status(jsonRecallObj.getInt("mfr_recall_status"));
		    		recallDetails.setMfr_notes(jsonRecallObj.getString("mfr_notes"));
		    		recallDetails.setRefresh_date(jsonRecallObj.getString("refresh_date"));
		    		
		    		alertList.add(recallDetails);
		    		recallAlert.setRecalls(recallDetails);
		    	}
		    	recallAlert.setVin(jsonObj.getString("vin"));
		    	recallAlert.setMake(jsonObj.getString("make"));
		    	recallAlert.setYear(jsonObj.getInt("year"));
		    }
	      }
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	public String showUpdateSelectedVehicle() {
		try {
			RegistrationAction registrationAction = new RegistrationAction();
			registrationAction.keepUpdateOfSelectedVehical(recallTO.getVin());
			recall1();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
		


	public List<RecallDetailsTO> getAlertList() {
		return alertList;
	}

	public void setAlertList(List<RecallDetailsTO> alertList) {
		this.alertList = alertList;
	}

	public RecallTO getRecallTO() {
		return recallTO;
	}

	public void setRecallTO(RecallTO recallTO) {
		this.recallTO = recallTO;
	}

	
}
