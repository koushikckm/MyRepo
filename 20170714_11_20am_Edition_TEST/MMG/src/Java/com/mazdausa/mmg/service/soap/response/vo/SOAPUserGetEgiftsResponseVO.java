package com.mazdausa.mmg.service.soap.response.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.constants.ApplicationConstants;

@XmlRootElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_RESPONSE_PARAMETER_GETEGIFTS, namespace = ApplicationConstants.SERVICE_SOAP_GETEGIFTS_RESPONSEELEMENT_NAMESPACE)
public class SOAPUserGetEgiftsResponseVO {
	
	private GetEgiftsVO egifts;

    @XmlElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_RESPONSE_PARAMETER_GETEGIFTS_GETEGIFTSRETURN)
	public GetEgiftsVO getEgifts() {
		return egifts;
	}

	public void setEgifts(GetEgiftsVO egifts) {
		this.egifts = egifts;
	}
}
