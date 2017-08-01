package com.mazdausa.mmg.service.rest.response.vo;

public class RestServiceRecordDateVO {
	private String year,month,day;

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
