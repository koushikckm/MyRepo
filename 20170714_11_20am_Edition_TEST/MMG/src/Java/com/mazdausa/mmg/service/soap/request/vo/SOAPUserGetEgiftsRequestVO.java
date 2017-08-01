package com.mazdausa.mmg.service.soap.request.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.constants.ApplicationConstants;

@XmlRootElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_REQUEST_PARAMETER_GETEGIFTS, namespace = ApplicationConstants.SERVICE_SOAP_GETEGIFTS_REQUESTELEMENT_NAMESPACE)
public class SOAPUserGetEgiftsRequestVO {

	private String customerId;
	private String vin;
	
    @XmlElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_REQUEST_PARAMETER_GETEGIFTS_CUSTOMERID)
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
    @XmlElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_REQUEST_PARAMETER_GETEGIFTS_VIN)
	public String getVin() {
		return vin;
	}
	
	public void setVin(String vin) {
		this.vin = vin;
	}	
}
