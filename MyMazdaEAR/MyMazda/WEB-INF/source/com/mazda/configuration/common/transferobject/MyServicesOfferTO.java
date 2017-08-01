package com.mazda.configuration.common.transferobject;

import java.util.List;

public class MyServicesOfferTO {
	
	private String exception;
	private int id;
	private String title;
	private String vin;
	private String deal;
	private String disclaimer;
	private String service;
	private String email;
	private String description;
	private List<MyServicesOfferTO> servicesList;
	private List<MyServicesOfferTO> descriptionsList;
	
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	
	public String getDeal() {
		return deal;
	}
	public void setDeal(String deal) {
		this.deal = deal;
	}
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<MyServicesOfferTO> getServicesList() {
		return servicesList;
	}
	public void setServicesList(List<MyServicesOfferTO> servicesList) {
		this.servicesList = servicesList;
	}
	public List<MyServicesOfferTO> getDescriptionsList() {
		return descriptionsList;
	}
	public void setDescriptionsList(List<MyServicesOfferTO> descriptionsList) {
		this.descriptionsList = descriptionsList;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}