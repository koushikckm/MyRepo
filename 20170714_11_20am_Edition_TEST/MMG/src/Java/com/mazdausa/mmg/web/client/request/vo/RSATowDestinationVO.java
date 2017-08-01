package com.mazdausa.mmg.web.client.request.vo;

public class RSATowDestinationVO {

	private String destinationBusinessName;
	private String destinationType;
	private RSATowAddressVO address;
	private RSATowGeographicalCoordinates geographicalCoordinates;
	
	public String getDestinationBusinessName() {
		return destinationBusinessName;
	}
	public void setDestinationBusinessName(String destinationBusinessName) {
		this.destinationBusinessName = destinationBusinessName;
	}
	public String getDestinationType() {
		return destinationType;
	}
	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}
	public RSATowAddressVO getAddress() {
		return address;
	}
	public void setAddress(RSATowAddressVO address) {
		this.address = address;
	}
	public RSATowGeographicalCoordinates getGeographicalCoordinates() {
		return geographicalCoordinates;
	}
	public void setGeographicalCoordinates(
			RSATowGeographicalCoordinates geographicalCoordinates) {
		this.geographicalCoordinates = geographicalCoordinates;
	}
	
	
}
