package com.mazda.configuration.common.transferobject;

public class LocateDealerTO {

	private String state;
	private String city;
	private String zip;
	private String longitude;
	private String latitude;
	private String street;
	private String phone;
	private String name;
	private String location;
	private String dealerSearch;
	private String expServiceFlag;
	private String ServiceUrl;

	private int id;
	private double distance;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getDealerSearch() {
		return dealerSearch;
	}
	public void setDealerSearch(String dealerSearch) {
		this.dealerSearch = dealerSearch;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the expServiceFlag
	 */
	public String getExpServiceFlag() {
		return expServiceFlag;
	}
	/**
	 * @param expServiceFlag the expServiceFlag to set
	 */
	public void setExpServiceFlag(String expServiceFlag) {
		this.expServiceFlag = expServiceFlag;
	}
	public String getServiceUrl() {
		return ServiceUrl;
	}
	public void setServiceUrl(String serviceUrl) {
		ServiceUrl = serviceUrl;
	}
}
