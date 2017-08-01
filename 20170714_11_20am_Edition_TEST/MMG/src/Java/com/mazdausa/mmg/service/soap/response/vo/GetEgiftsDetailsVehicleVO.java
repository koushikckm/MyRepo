package com.mazdausa.mmg.service.soap.response.vo;

import javax.xml.bind.annotation.XmlElement;

import com.mazdausa.mmg.constants.ApplicationConstants;

public class GetEgiftsDetailsVehicleVO {
	
	private String certificateNo;
	private String egiftStatus;
	private String amount;
	private String issueDate;
	private String redemptionAmount;
	private String redemptionDate;
	private String expirationDate;
	private String vinNo;
	private String dealerName;
	
	@XmlElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_RESPONSE_PARAMETER_GETEGIFTS_GETEGIFTSRETURN_EGIFTDETAILS_EGIFTLIST_EGIFT_EGIFTSTATUS)
	public String getEgiftStatus() {
		return egiftStatus;
	}

	public void setEgiftStatus(String egiftStatus) {
		this.egiftStatus = egiftStatus;
	}

	@XmlElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_RESPONSE_PARAMETER_GETEGIFTS_GETEGIFTSRETURN_EGIFTDETAILS_EGIFTLIST_EGIFT_VINNO)
	public String getVinNo() {
		return vinNo;
	}

	public void setVinNo(String vinNo) {
		this.vinNo = vinNo;
	}

	@XmlElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_RESPONSE_PARAMETER_GETEGIFTS_GETEGIFTSRETURN_EGIFTDETAILS_EGIFTLIST_EGIFT_DEALERNAME)
	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	@XmlElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_RESPONSE_PARAMETER_GETEGIFTS_GETEGIFTSRETURN_EGIFTDETAILS_EGIFTLIST_EGIFT_CERTIFICATENO)
	public String getCertificateNo() {
		return certificateNo;
	}
	
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	
	@XmlElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_RESPONSE_PARAMETER_GETEGIFTS_GETEGIFTSRETURN_EGIFTDETAILS_EGIFTLIST_EGIFT_AMOUNT)
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@XmlElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_RESPONSE_PARAMETER_GETEGIFTS_GETEGIFTSRETURN_EGIFTDETAILS_EGIFTLIST_EGIFT_ISSUEDATE)
	public String getIssueDate() {
		return issueDate;
	}
	
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	
	@XmlElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_RESPONSE_PARAMETER_GETEGIFTS_GETEGIFTSRETURN_EGIFTDETAILS_EGIFTLIST_EGIFT_REDEMPTIONAMOUNT)
	public String getRedemptionAmount() {
		return redemptionAmount;
	}
	
	public void setRedemptionAmount(String redemptionAmount) {
		this.redemptionAmount = redemptionAmount;
	}
	
	@XmlElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_RESPONSE_PARAMETER_GETEGIFTS_GETEGIFTSRETURN_EGIFTDETAILS_EGIFTLIST_EGIFT_REDEMPTIONDATE)
	public String getRedemptionDate() {
		return redemptionDate;
	}
	
	public void setRedemptionDate(String redemptionDate) {
		this.redemptionDate = redemptionDate;
	}
	
	@XmlElement(name = ApplicationConstants.XML_SOAP_GETEGIFTS_RESPONSE_PARAMETER_GETEGIFTS_GETEGIFTSRETURN_EGIFTDETAILS_EGIFTLIST_EGIFT_EXPIRATIONDATE)
	public String getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
}
