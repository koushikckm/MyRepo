package com.mazda.configuration.common.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.json.JSONObject;

import com.mazda.common.utility.PostJsonObject;
import com.mazda.common.utility.mail.GetJsonObject;
import com.mazda.configuration.common.transferobject.ContactTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ContactAction extends ActionSupport implements ModelDriven<ContactTO>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final ActionContext actionContext 	= ActionContext.getContext();
	private HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST); 
	
	
	ContactTO contactTo;
	
	public ContactTO getModel() {
		contactTo = new ContactTO();
		return contactTo;
	}
	
	public String contactMazda() { 
		return SUCCESS;
	}
	
	public String contactUS(){
		try {
			String url = request.getSession().getAttribute("base_env_url")+"customercare/info";
			String postResponse = GetJsonObject.getWebServceObj(url,request);
	        JSONObject jsonResponse = new JSONObject(postResponse);
	        if(jsonResponse.has("allowemail") && jsonResponse.getString("allowemail").equalsIgnoreCase("no")) {
	        	request.getSession().setAttribute("allowemail", "no");
	        } else {
	        	request.getSession().setAttribute("allowemail", "yes");
	        }
	        
		} catch (Exception e) { }
		return SUCCESS;
	}
	
	public String sendMail() { 
		JSONObject contactData = new JSONObject();
		try {
			contactData.put("emailmessage", contactTo.getMessage());
			contactData.put("vin", contactTo.getVin());
			contactData.put("emailsubject", "Website - "+contactTo.getSubject());
			contactData.put("emailto", contactTo.getTo());
			contactData.put("useremail", contactTo.getEmail());
			contactData.put("userphone", contactTo.getMobilePhone());
			contactData.put("username", contactTo.getFrom());
			
			String url = request.getSession().getAttribute("base_env_url")+"customercare/sendemail";
			String postResponse = PostJsonObject.postJson(contactData, url,request);
	        JSONObject jsonResponse = new JSONObject(postResponse);
	        if(jsonResponse.toString().contains("success")) {
	        	return null;
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "error";
	}

	public ContactTO getContactTo() {
		return contactTo;
	}

	public void setContactTo(ContactTO contactTo) {
		this.contactTo = contactTo;
	}
	

}
