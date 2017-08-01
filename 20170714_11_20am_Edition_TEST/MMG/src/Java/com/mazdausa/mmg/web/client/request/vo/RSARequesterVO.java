package com.mazdausa.mmg.web.client.request.vo;

public class RSARequesterVO {

	private RSANameVO name;
	private String callbackNumber;
	private RSAContactInfoVO contactInfo;
	
	public RSANameVO getName() {
		return name;
	}
	public void setName(RSANameVO name) {
		this.name = name;
	}
	public String getCallbackNumber() {
		return callbackNumber;
	}
	public void setCallbackNumber(String callbackNumber) {
		this.callbackNumber = callbackNumber;
	}
	public RSAContactInfoVO getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(RSAContactInfoVO contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	
}
