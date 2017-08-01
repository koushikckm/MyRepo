package com.mazdausa.mmg.service.soap.response.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.mazdausa.mmg.constants.ApplicationConstants;

public class GetEgiftsDetailVO {
	
	private int count;
	private List<GetEgiftsDetailsVehicleVO> egiftList;
	 
	@XmlElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_RESPONSE_PARAMETER_GETEGIFTS_GETEGIFTSRETURN_EGIFTDETAILS_COUNT)
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	@XmlElementWrapper(name = ApplicationConstants.XML_SOAP_GETEGIFTS_RESPONSE_PARAMETER_GETEGIFTS_GETEGIFTSRETURN_EGIFTDETAILS_EGIFTLIST)
	@XmlElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_RESPONSE_PARAMETER_GETEGIFTS_GETEGIFTSRETURN_EGIFTDETAILS_EGIFTLIST_EGIFT)
	public List<GetEgiftsDetailsVehicleVO> getEgiftList() {
		return egiftList;
	}
	
	public void setEgiftList(List<GetEgiftsDetailsVehicleVO> egiftList) {
		this.egiftList = egiftList;
	}

	 
}
