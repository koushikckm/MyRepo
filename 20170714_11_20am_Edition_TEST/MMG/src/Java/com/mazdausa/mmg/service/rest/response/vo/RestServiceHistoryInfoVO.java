package com.mazdausa.mmg.service.rest.response.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="info")
public class RestServiceHistoryInfoVO {
	private String vin,custId;
	private List<RestServiceRecordVO> serviceRecords;
	private String status,error;
	
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
	
	@XmlElementWrapper(name = "serviceRecords")
	@XmlElement(name="serviceRecord")
	public List<RestServiceRecordVO> getServiceRecords() {
		return serviceRecords;
	}
	public void setServiceRecords(List<RestServiceRecordVO> serviceRecords) {
		this.serviceRecords = serviceRecords;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "RestServiceHistoryInfoVO [vin=" + vin + ", custId=" + custId
				+ ", serviceRecords=" + serviceRecords + ", error=" + error
				+ "]";
	}
	
	

}
