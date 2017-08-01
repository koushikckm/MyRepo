package com.mazdausa.mmg.service.rest.response.vo;

import javax.xml.bind.annotation.XmlElement;

public class ClosedRecalls {

	private String recallNo;
	private String vin;
	
	@XmlElement(name="recallNo")
	public String getRecallNo() {
		return recallNo;
	}
	public void setRecallNo(String recallNo) {
		this.recallNo = recallNo;
	}
	
	@XmlElement(name="vin")
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	
}
