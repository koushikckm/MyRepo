package com.mazdausa.mmg.service.rest.response.vo;

import javax.xml.bind.annotation.XmlAttribute;

public class DeviceTokenVinVO {

	//@XmlAttribute(required = true, name = "deviceToken")
	private String deviceToken;
	//@XmlAttribute(required = true, name = "vin")
	private String vin;
	//@XmlAttribute(required = true, name = "custId")
	private String custId;
	private String crlnName;
	private String deviceType;

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCrlnName() {
		return crlnName;
	}

	public void setCrlnName(String crlnName) {
		this.crlnName = crlnName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	
}
