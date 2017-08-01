package com.mazdausa.mmg.web.client.response.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="serviceRecord")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceRecordResponseVO {
	
	private String ownerEntered,mileage,amount,serviceFacility,serviceType,opCode,musaRefId;
	private ServiceRecordDateVO serviceDate;
	private List<String> comments;
	private String status,error;	
	
	
	
	public ServiceRecordResponseVO(ServiceRecordDateVO serviceDate,
			List<String> comments) {
		super();
		this.serviceDate = serviceDate;
		this.comments = comments;
	}

	public ServiceRecordResponseVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
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

	
	public ServiceRecordDateVO getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(ServiceRecordDateVO serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ServiceRecordResponseVO [ownerEntered=" + ownerEntered
				+ ", mileage=" + mileage + ", amount=" + amount
				+ ", serviceFacility=" + serviceFacility + ", serviceType="
				+ serviceType + ", opCode=" + opCode + ", musaRefId="
				+ musaRefId + ", serviceDate=" + serviceDate + ", comments="
				+ comments + ", status=" + status + ", error=" + error + "]";
	}
	


	
	
}
