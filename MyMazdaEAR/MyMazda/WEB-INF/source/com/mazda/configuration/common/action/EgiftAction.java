package com.mazda.configuration.common.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mazda.common.utility.mail.GetJsonObject;
import com.mazda.configuration.common.transferobject.EgiftTO;
import com.mazda.configuration.common.transferobject.RegistrationTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class EgiftAction extends ActionSupport implements ModelDriven<EgiftTO>, ServletResponseAware{

	private static final long serialVersionUID = 1L;
	EgiftTO eGiftTO;
	List<EgiftTO> egiftList = new ArrayList<EgiftTO>();
	List<RegistrationTO> newVehicalsList = new ArrayList<RegistrationTO>();
	final ActionContext actionContext 	= ActionContext.getContext();
	private HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST); 
	private HttpServletResponse servletResponse;
	 
	public EgiftTO getModel() {
		eGiftTO = new EgiftTO();
		return eGiftTO;
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
				
				for(int i=0; i<vehicalsList.size();i++){
					RegistrationTO login = loginUser.get(0);
					String url = request.getSession().getAttribute("base_env_url")+"user/getUserEgifts/"+login.getCustomerId()+"/"+vehicalsList.get(i).getVin();
					String json = GetJsonObject.getWebServceObj(url,request);
					JSONObject jsonObj = new JSONObject(json);
					if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("Success")){
						if(!(jsonObj.getString("count").equalsIgnoreCase("0"))) {
							newVehicalsList.add(vehicalsList.get(i));
						}
					}
				}
				
				RegistrationTO login = loginUser.get(0);
				RegistrationTO selectedVehical = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
				String url = request.getSession().getAttribute("base_env_url")+"user/getUserEgifts/"+login.getCustomerId()+"/"+selectedVehical.getVin();
				String json = GetJsonObject.getWebServceObj(url,request);
				JSONObject jsonObj = new JSONObject(json);
				if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("Success")){
					if(jsonObj.has("egifts")) {
						JSONArray eGiftRecordArr = jsonObj.getJSONArray("egifts");
						for(int i=0; i<eGiftRecordArr.length(); i++){
							JSONObject eGiftDetail = eGiftRecordArr.getJSONObject(i);
							EgiftTO eGifts = new EgiftTO();
							eGifts.setCertificateNo(eGiftDetail.getString("certificateNo"));
							if(eGiftDetail.getString("egiftStatus").equalsIgnoreCase("Redeemed") || eGiftDetail.getString("egiftStatus").equalsIgnoreCase("Paid")
								|| eGiftDetail.getString("egiftStatus").equalsIgnoreCase("Request Reversal") || eGiftDetail.getString("egiftStatus").equalsIgnoreCase("Reversed")){
								eGifts.setEgiftStatus("REDEEMED");
							}
							else if(eGiftDetail.getString("egiftStatus").equalsIgnoreCase("Verified")){
								eGifts.setEgiftStatus("SUBMITTED");
							}
							else{
								eGifts.setEgiftStatus(eGiftDetail.getString("egiftStatus").toUpperCase());
							}
							if(eGiftDetail.getString("amount").trim().equals("")){
								eGifts.setAmount(eGiftDetail.getString("amount"));
							}
							else{
								if(eGiftDetail.getString("amount").contains(".")){
									eGifts.setAmount("$"+eGiftDetail.getString("amount"));
								}
								else{
									eGifts.setAmount("$"+eGiftDetail.getString("amount")+".00");
								}
							}
							eGifts.setIssueDate(eGiftDetail.getString("issueDate"));
							if(eGiftDetail.getString("redemptionAmount").trim().equals("")){
								eGifts.setRedemptionAmount(eGiftDetail.getString("redemptionAmount"));
							}
							else{
								if(eGiftDetail.getString("redemptionAmount").contains(".")){
									eGifts.setRedemptionAmount("$"+eGiftDetail.getString("redemptionAmount"));
								}
								else{
									eGifts.setRedemptionAmount("$"+eGiftDetail.getString("redemptionAmount")+".00");
								}
							}
							eGifts.setRedemptionDate(eGiftDetail.getString("redemptionDate"));
							eGifts.setExpirationDate(eGiftDetail.getString("expirationDate"));
							eGifts.setVin(eGiftDetail.getString("vinNo"));
							eGifts.setDealerName(eGiftDetail.getString("dealerName"));
							egiftList.add(eGifts);
						}
					}
				}
				
				int size = egiftList.size();
				request.setAttribute("noOfPages", size);
 
				if(egiftList.size() == 0){
					 addActionMessage("We are not able to show e-Gift details matching your profile. Please call 1-800-222-5500 if you have any question.");
				}
			}
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
}
	
	public String showUpdateSelectedVehicleEgift() {
		try {
			RegistrationAction registrationAction = new RegistrationAction();
			registrationAction.keepUpdateOfSelectedVehical(eGiftTO.getVin());
			execute();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
		
	}
	
	public EgiftTO geteGiftTO() {
		return eGiftTO;
	}

	public void seteGiftTO(EgiftTO eGiftTO) {
		this.eGiftTO = eGiftTO;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getServletResponse() {
		return servletResponse;
	}
	
	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	public List<EgiftTO> getEgiftList() {
		return egiftList;
	}

	public void setEgiftList(List<EgiftTO> egiftList) {
		this.egiftList = egiftList;
	}
	
	public List<RegistrationTO> getNewVehicalsList() {
		return newVehicalsList;
	}


	public void setNewVehicalsList(List<RegistrationTO> newVehicalsList) {
		this.newVehicalsList = newVehicalsList;
	}
	
}
