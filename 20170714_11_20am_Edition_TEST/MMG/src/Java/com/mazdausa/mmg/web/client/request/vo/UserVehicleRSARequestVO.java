package com.mazdausa.mmg.web.client.request.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserVehicleRSARequestVO {
	
	private RSARequesterVO requestor;
	private RSADisablementLocationVO disablementLocation;
	private RSANotificationPreferencesVO notificationPreferences;
	private RSATowDestinationVO towDestination;
	private RSAVehicleVO vehicle;
	private RSAServiceDetailsVO serviceDetails;
	
	public RSARequesterVO getRequestor() {
		return requestor;
	}
	public void setRequestor(RSARequesterVO requestor) {
		this.requestor = requestor;
	}
	public RSADisablementLocationVO getDisablementLocation() {
		return disablementLocation;
	}
	public void setDisablementLocation(RSADisablementLocationVO disablementLocation) {
		this.disablementLocation = disablementLocation;
	}
	public RSANotificationPreferencesVO getNotificationPreferences() {
		return notificationPreferences;
	}
	public void setNotificationPreferences(
			RSANotificationPreferencesVO notificationPreferences) {
		this.notificationPreferences = notificationPreferences;
	}
	public RSATowDestinationVO getTowDestination() {
		return towDestination;
	}
	public void setTowDestination(RSATowDestinationVO towDestination) {
		this.towDestination = towDestination;
	}
	public RSAVehicleVO getVehicle() {
		return vehicle;
	}
	public void setVehicle(RSAVehicleVO vehicle) {
		this.vehicle = vehicle;
	}
	public RSAServiceDetailsVO getServiceDetails() {
		return serviceDetails;
	}
	public void setServiceDetails(RSAServiceDetailsVO serviceDetails) {
		this.serviceDetails = serviceDetails;
	}
	
	
}
