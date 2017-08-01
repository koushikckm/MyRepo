package com.mazdausa.mmg.web.client.response.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.service.rest.response.vo.RestServiceRecordVO;


/**
 * This Value Object, holds the details of the service upload request sent by the client.
 * @author anilk
 *
 */
@XmlRootElement(name="info")
public class ServiceUploadResponseVO {
	private String vin,custId;
	private List<ServiceRecordResponseVO> serviceRecords;
	private String status,error;
	
	
	
	public ServiceUploadResponseVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ServiceUploadResponseVO(String vin, String custId,
			List<ServiceRecordResponseVO> serviceRecords) {
		super();
		this.vin = vin;
		this.custId = custId;
		this.serviceRecords = serviceRecords;
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
	
	@XmlElementWrapper(name = "serviceRecords")
	@XmlElement(name="serviceRecord")
	public List<ServiceRecordResponseVO> getServiceRecords() {
		return serviceRecords;
	}
	public void setServiceRecords(List<ServiceRecordResponseVO> serviceRecords) {
		this.serviceRecords = serviceRecords;
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
		return "ServiceUploadResponseVO [vin=" + vin + ", custId=" + custId
				+ ", serviceRecords=" + serviceRecords + ", status=" + status
				+ ", error=" + error + "]";
	}
	
	
}
