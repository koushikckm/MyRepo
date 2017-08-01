package com.mazdausa.mmg.web.client.request.vo;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * This Value Object, holds the details of the service upload request sent by the client.
 * @author anilk
 *
 */

@XmlRootElement
public class ServiceUploadRequestVO {
	private String vin,custId;
	private String ownerEntered,mileage,amount,serviceFacility,serviceType,opCode,musaRefId;
	private String year,month,day;
	private String comments,serviceLocation;
	
	
	
	public ServiceUploadRequestVO() {
		super();
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
	public String getOwnerEntered() {
		return ownerEntered;
	}
	public void setOwnerEntered(String ownerEntered) {
		this.ownerEntered = ownerEntered;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getServiceFacility() {
		return serviceFacility;
	}
	public void setServiceFacility(String serviceFacility) {
		this.serviceFacility = serviceFacility;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getOpCode() {
		return opCode;
	}
	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}
	public String getMusaRefId() {
		return musaRefId;
	}
	public void setMusaRefId(String musaRefId) {
		this.musaRefId = musaRefId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getServiceLocation() {
		return serviceLocation;
	}
	public void setServiceLocation(String serviceLocation) {
		this.serviceLocation = serviceLocation;
	}
	@Override
	public String toString() {
		return "ServiceUploadRequestVO [vin=" + vin + ", custId=" + custId
				+ ", ownerEntered=" + ownerEntered + ", mileage=" + mileage
				+ ", amount=" + amount + ", serviceFacility=" + serviceFacility
				+ ", serviceType=" + serviceType + ", opCode=" + opCode
				+ ", musaRefId=" + musaRefId + ", year=" + year + ", month="
				+ month + ", day=" + day + ", comments=" + comments
				+ ", serviceLocation=" + serviceLocation + "]";
	}

	
	
}
