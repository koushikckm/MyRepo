package com.mazdausa.mmg.service.rest.response.vo;

public class PushNotificationListVO {

	private String vin;
	private String crlnName;
	private String deviceToken;
	private String deviceType;
	
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getCrlnName() {
		return crlnName;
	}
	public void setCrlnName(String crlnName) {
		this.crlnName = crlnName;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	
}
