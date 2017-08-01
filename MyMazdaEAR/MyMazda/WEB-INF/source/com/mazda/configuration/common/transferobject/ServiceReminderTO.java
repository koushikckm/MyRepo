package com.mazda.configuration.common.transferobject;

import java.io.Serializable;
import java.util.ArrayList;

import antlr.collections.List;

public class ServiceReminderTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String vin;
	private String couponNumber;
	private String specificationPrice;
	private String couponHeader;
	private String dealerName;
	private String dealerAddress;
	private String dealerState;
	private String dealerCity;
	private String dealerZip;
	private String disclaimer;
	private String dollarOff;
	private String brakeFlag;
	private String tireFlag;
	private String brtext;
	private String titext;
	private String tiImg;
	private String brImg;
	private String serviceDate;
	private ArrayList<String> textLine = new ArrayList<String>();
	
	
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getCouponNumber() {
		return couponNumber;
	}
	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}
	public String getSpecificationPrice() {
		return specificationPrice;
	}
	public void setSpecificationPrice(String specificationPrice) {
		this.specificationPrice = specificationPrice;
	}
	public String getCouponHeader() {
		return couponHeader;
	}
	public void setCouponHeader(String couponHeader) {
		this.couponHeader = couponHeader;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getDealerAddress() {
		return dealerAddress;
	}
	public void setDealerAddress(String dealerAddress) {
		this.dealerAddress = dealerAddress;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public String getDollarOff() {
		return dollarOff;
	}
	public void setDollarOff(String dollarOff) {
		this.dollarOff = dollarOff;
	}
	/**
	 * @return the textLine
	 */
	/**
	 * @return the textLine
	 */
	public ArrayList<String> getTextLine() {
		return textLine;
	}
	/**
	 * @param textLine the textLine to set
	 */
	public void setTextLine(ArrayList<String> textLine) {
		this.textLine = textLine;
	}
	public String getBrakeFlag() {
		return brakeFlag;
	}
	public void setBrakeFlag(String brakeFlag) {
		this.brakeFlag = brakeFlag;
	}
	public String getTireFlag() {
		return tireFlag;
	}
	public void setTireFlag(String tireFlag) {
		this.tireFlag = tireFlag;
	}
	public String getBrtext() {
		return brtext;
	}
	public void setBrtext(String brtext) {
		this.brtext = brtext;
	}
	public String getTitext() {
		return titext;
	}
	public void setTitext(String titext) {
		this.titext = titext;
	}
	public String getTiImg() {
		return tiImg;
	}
	public void setTiImg(String tiImg) {
		this.tiImg = tiImg;
	}
	public String getBrImg() {
		return brImg;
	}
	public void setBrImg(String brImg) {
		this.brImg = brImg;
	}
	public String getDealerState() {
		return dealerState;
	}
	public void setDealerState(String dealerState) {
		this.dealerState = dealerState;
	}
	public String getDealerCity() {
		return dealerCity;
	}
	public void setDealerCity(String dealerCity) {
		this.dealerCity = dealerCity;
	}
	public String getDealerZip() {
		return dealerZip;
	}
	public void setDealerZip(String dealerZip) {
		this.dealerZip = dealerZip;
	}
	public String getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}
	
	
	

}
