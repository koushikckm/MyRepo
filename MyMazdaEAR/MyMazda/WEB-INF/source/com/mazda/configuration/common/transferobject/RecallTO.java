package com.mazda.configuration.common.transferobject;

import java.io.Serializable;

public class RecallTO implements Serializable{

	/**
	 * 
	 */
	/*private static final long serialVersionUID = 1L;
	private String startDate;
	private String recallSSPNo;
	private String recallSSPDesc;
	private String vin;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getRecallSSPNo() {
		return recallSSPNo;
	}
	public void setRecallSSPNo(String recallSSPNo) {
		this.recallSSPNo = recallSSPNo;
	}
	public String getRecallSSPDesc() {
		return recallSSPDesc;
	}
	public void setRecallSSPDesc(String recallSSPDesc) {
		this.recallSSPDesc = recallSSPDesc;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}*/
	
	private String vin,make,model,refresh_date;
	private boolean status,recalls_available,number_of_recalls;
	private int year,manufacturer_id;
	
	private RecallDetailsTO recalls;

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRefresh_date() {
		return refresh_date;
	}

	public void setRefresh_date(String refresh_date) {
		this.refresh_date = refresh_date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isRecalls_available() {
		return recalls_available;
	}

	public void setRecalls_available(boolean recalls_available) {
		this.recalls_available = recalls_available;
	}

	public boolean isNumber_of_recalls() {
		return number_of_recalls;
	}

	public void setNumber_of_recalls(boolean number_of_recalls) {
		this.number_of_recalls = number_of_recalls;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getManufacturer_id() {
		return manufacturer_id;
	}

	public void setManufacturer_id(int manufacturer_id) {
		this.manufacturer_id = manufacturer_id;
	}

	public RecallDetailsTO getRecalls() {
		return recalls;
	}

	public void setRecalls(RecallDetailsTO recalls) {
		this.recalls = recalls;
	}
	
	

}
