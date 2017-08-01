package com.mazdausa.mmg.web.client.request.vo;

public class RSAContactInfoVO {

	private RSAContactAddressVO address;
	private String addressType;
	private String phoneNumber;
	private String phoneType;
	
	public RSAContactAddressVO getAddress() {
		return address;
	}
	public void setAddress(RSAContactAddressVO address) {
		this.address = address;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
}
