package com.mazdausa.mmg.service.soap.response.vo;

import javax.xml.bind.annotation.XmlElement;

import com.mazdausa.mmg.constants.ApplicationConstants;

public class GetEgiftsVO {
	
	private String error;
    private GetEgiftsDetailVO egiftsDetail;
    
    @XmlElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_RESPONSE_PARAMETER_GETEGIFTS_GETEGIFTSRETURN_ERROR)
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}

	@XmlElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_RESPONSE_PARAMETER_GETEGIFTS_GETEGIFTSRETURN_EGIFTDETAILS)
	public GetEgiftsDetailVO getEgiftsDetail() {
		return egiftsDetail;
	}

	public void setEgiftsDetail(GetEgiftsDetailVO egiftsDetail) {
		this.egiftsDetail = egiftsDetail;
	}
    
}
