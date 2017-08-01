package com.mazda.configuration.common.transferobject;

import java.io.Serializable;
import java.util.List;

public class ServiceHistoryTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int serviceDealerId;
	private int mileage;
	private int musaRefId;
	private String serviceDate;
	private String opCode;
	private String serviceType;
	private String amount;
	private String serviceLocation;
	private List<String> comments;
	private String comment;
	private String vin;
	private String historyBy;
	private String dealerAddress;
	private String dealerState;
	private String dealerStreet;
	private String dealerPhone;
	private String dealerName;
	private String dealerZip;
	private String dealerCity;
	
	public int getServiceDealerId() {
		return serviceDealerId;
	}
	public void setServiceDealerId(int serviceDealerId) {
		this.serviceDealerId = serviceDealerId;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public String getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}
	
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public int getMusaRefId() {
		return musaRefId;
	}
	public void setMusaRefId(int musaRefId) {
		this.musaRefId = musaRefId;
	}
	public String getOpCode() {
		return opCode;
	}
	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}
	
	public String getServiceLocation() {
		return serviceLocation;
	}
	public void setServiceLocation(String serviceLocation) {
		this.serviceLocation = serviceLocation;
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getHistoryBy() {
		return historyBy;
	}
	public void setHistoryBy(String historyBy) {
		this.historyBy = historyBy;
	}
	public String getDealerAddress() {
		return dealerAddress;
	}
	public void setDealerAddress(String dealerAddress) {
		this.dealerAddress = dealerAddress;
	}
	public String getDealerState() {
		return dealerState;
	}
	public void setDealerState(String dealerState) {
		this.dealerState = dealerState;
	}
	public String getDealerStreet() {
		return dealerStreet;
	}
	public void setDealerStreet(String dealerStreet) {
		this.dealerStreet = dealerStreet;
	}
	public String getDealerPhone() {
		return dealerPhone;
	}
	public void setDealerPhone(String dealerPhone) {
		this.dealerPhone = dealerPhone;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getDealerZip() {
		return dealerZip;
	}
	public void setDealerZip(String dealerZip) {
		this.dealerZip = dealerZip;
	}
	public String getDealerCity() {
		return dealerCity;
	}
	public void setDealerCity(String dealerCity) {
		this.dealerCity = dealerCity;
	}
	

}
