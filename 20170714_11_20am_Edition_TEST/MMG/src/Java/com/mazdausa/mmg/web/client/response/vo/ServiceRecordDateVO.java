package com.mazdausa.mmg.web.client.response.vo;

public class ServiceRecordDateVO {
	private String year,month,day;

	
	
	public ServiceRecordDateVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public ServiceRecordDateVO(String year, String month, String day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}



	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "RestServiceRecordDateVO [year=" + year + ", month=" + month
				+ ", day=" + day + "]";
	}
	
	

}
