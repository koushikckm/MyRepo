package com.mazda.configuration.common.transferobject;

import java.io.Serializable;

public class UploadImageTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String vin;
	private String userId;
	private String custId;
	
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	
}
