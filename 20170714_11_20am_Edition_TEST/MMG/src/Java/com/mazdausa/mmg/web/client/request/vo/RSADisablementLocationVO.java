package com.mazdausa.mmg.web.client.request.vo;

public class RSADisablementLocationVO {

	private RSADisablementAddressVO address;
	private RSADisablementGeographicalCoordinatesVO geographicalCoordinates;
	private String locationType;
	private String customerAtLocation;
	
	public RSADisablementAddressVO getAddress() {
		return address;
	}
	public void setAddress(RSADisablementAddressVO address) {
		this.address = address;
	}
	public RSADisablementGeographicalCoordinatesVO getGeographicalCoordinates() {
		return geographicalCoordinates;
	}
	public void setGeographicalCoordinates(
			RSADisablementGeographicalCoordinatesVO geographicalCoordinates) {
		this.geographicalCoordinates = geographicalCoordinates;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getCustomerAtLocation() {
		return customerAtLocation;
	}
	public void setCustomerAtLocation(String customerAtLocation) {
		this.customerAtLocation = customerAtLocation;
	}
	
}
