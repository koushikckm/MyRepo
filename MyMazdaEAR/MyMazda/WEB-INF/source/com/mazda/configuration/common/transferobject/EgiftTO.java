package com.mazda.configuration.common.transferobject;

import java.io.Serializable;

public class EgiftTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String certificateNo;
	private String egiftStatus;
	private String amount;
	private String issueDate;
	private String redemptionAmount;
	private String redemptionDate;
	private String expirationDate;
	private String vin;
	private String dealerName;
	
	public String getEgiftStatus() {
		return egiftStatus;
	}
	public void setEgiftStatus(String egiftStatus) {
		this.egiftStatus = egiftStatus;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getRedemptionAmount() {
		return redemptionAmount;
	}
	public void setRedemptionAmount(String redemptionAmount) {
		this.redemptionAmount = redemptionAmount;
	}
	public String getRedemptionDate() {
		return redemptionDate;
	}
	public void setRedemptionDate(String redemptionDate) {
		this.redemptionDate = redemptionDate;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	

}
