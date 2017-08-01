package com.mazda.common.action;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mazda.common.utility.mail.GetJsonObject;
import com.mazda.configuration.common.transferobject.ModelTO;
import com.mazda.configuration.common.transferobject.RegistrationTO;
import com.mazda.configuration.common.transferobject.WelcomeTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class WelcomeAction extends ActionSupport implements ModelDriven<RegistrationTO>{
	static final Logger log = Logger.getLogger(WelcomeAction.class); 
	
	private static final long serialVersionUID = 1L;

	RegistrationTO registrationTO;
	final ActionContext actionContext 	= ActionContext.getContext();
	private HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
	List<WelcomeTO> yearModelList = new ArrayList<WelcomeTO>();


	public RegistrationTO getModel() {
		registrationTO = new RegistrationTO();
		return registrationTO;
	}

	public String execute() throws Exception {
		
		log.debug("Inside WelcomeAction > execute>>>>>");
		
		
		
	try{
		 while(yearModelList.size()==0){
			 String url = request.getSession().getAttribute("base_env_url")+"vehicle/codeswithtrims";
			 String json = GetJsonObject.getWebServceObj(url,request);
			 JSONObject jsonObj = new JSONObject(json);
			 if(jsonObj.has("vehicles")){
				 JSONArray yearVehicalArray = jsonObj.getJSONArray("vehicles");
				 for(int i=0; i<yearVehicalArray.length(); i++){
					 JSONObject yearModelObj = yearVehicalArray.getJSONObject(i);
					 WelcomeTO yearModel = new WelcomeTO();
					 yearModel.setYear(yearModelObj.getInt("year"));
					 //yearList.add(yearModelObj.getInt("year"));
					 JSONArray modelsArr = yearModelObj.getJSONArray("models");
					 List<ModelTO> modelList = new ArrayList<ModelTO>();
					 for(int j=0; j<modelsArr.length(); j++){
						 JSONObject modelObj = modelsArr.getJSONObject(j);
						 ModelTO modelTO = new ModelTO();
						 modelTO.setModelCode(modelObj.getString("modelCode"));
						 modelTO.setModelName(modelObj.getString("modelName"));
						 JSONArray trimsArr = modelObj.getJSONArray("trims");
						 List<String> trim = new ArrayList<String>();
						 if(trimsArr.length()>0){
							 trim.add("ALL");
						 }
						 for(int k=0; k<trimsArr.length(); k++){
							 JSONObject trimObj = trimsArr.getJSONObject(k);
							 trim.add(trimObj.getString("trimName"));
						 }
						 modelTO.setTrims(trim);
						 modelList.add(modelTO);
					 }
					 yearModel.setModels(modelList);
					 yearModelList.add(yearModel);
				 }
				 request.getSession().setAttribute("yearModelTrim", yearModelList);
			 }
		 }
	  } catch (Exception  e) {
		e.printStackTrace();
	  } 
	@SuppressWarnings("unchecked")
	List<RegistrationTO> loginUser = (List<RegistrationTO>) request.getSession().getAttribute("loginUser");
	if(loginUser != null){
		return LOGIN;
	}else {
		return SUCCESS;
	}
	
	}


	public RegistrationTO getRegistrationTO() {
		return registrationTO;
	}

	public void setRegistrationTO(RegistrationTO registrationTO) {
		this.registrationTO = registrationTO;
	}

}
