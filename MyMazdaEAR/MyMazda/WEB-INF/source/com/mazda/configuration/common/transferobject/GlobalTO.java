package com.mazda.configuration.common.transferobject;

public class GlobalTO {
	
	private String loginFName;
	private String carlineDesc;
	private String vin;
	private int mileage;
	private int loginId;
	
	
	public String getLoginFName() {
		return loginFName;
	}
	public void setLoginFName(String loginFName) {
		this.loginFName = loginFName;
	}
	public String getCarlineDesc() {
		return carlineDesc;
	}
	public void setCarlineDesc(String carlineDesc) {
		this.carlineDesc = carlineDesc;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	
}