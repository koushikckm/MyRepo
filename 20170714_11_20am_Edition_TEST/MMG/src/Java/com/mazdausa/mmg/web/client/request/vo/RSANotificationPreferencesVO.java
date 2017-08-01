package com.mazdausa.mmg.web.client.request.vo;

public class RSANotificationPreferencesVO {

	private String preferredMode;
	private String emailId;
	private String textMessageNumber;
	private String primaryPhoneNumber;
	
	public String getPreferredMode() {
		return preferredMode;
	}
	public void setPreferredMode(String preferredMode) {
		this.preferredMode = preferredMode;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getTextMessageNumber() {
		return textMessageNumber;
	}
	public void setTextMessageNumber(String textMessageNumber) {
		this.textMessageNumber = textMessageNumber;
	}
	public String getPrimaryPhoneNumber() {
		return primaryPhoneNumber;
	}
	public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
		this.primaryPhoneNumber = primaryPhoneNumber;
	}
	
}
