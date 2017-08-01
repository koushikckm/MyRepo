package com.mazdausa.mmg.web.client.request.vo;


import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Value Object, contains the details of the Customer Support request sent by the client.
 * @author Anilk
 */
@XmlRootElement
public class CustomerSupportRequestVO {
	
	//Variables to send mail
	private String username,useremail,emailto,emailsubject,emailmessage,emailstatusmessage,vin,userphone ;
	private int emailstatus;
	
	//Variables for crash report
	private String crashdata,devicetype,devicemodel,userid,guid;
	
	//Added @ 17-01-2014 -Variables for email coupons
	private String couponvalue,address,disclaimer;
	
	//Setter/Getters


	public String getDevicemodel() {
		return devicemodel;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public String getCouponvalue() {
		return couponvalue;
	}
	public void setCouponvalue(String couponvalue) {
		this.couponvalue = couponvalue;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public void setDevicemodel(String devicemodel) {
		this.devicemodel = devicemodel;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	public String getVin() {
		return vin;
	}	
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	
	public String getCrashdata() {
		return crashdata;
	}

	public void setCrashdata(String crashdata) {
		this.crashdata = crashdata;
	}
	public String getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getEmailto() {
		return emailto;
	}
	public void setEmailto(String emailto) {
		this.emailto = emailto;
	}
	public String getEmailsubject() {
		return emailsubject;
	}
	public void setEmailsubject(String emailsubject) {
		this.emailsubject = emailsubject;
	}
	public String getEmailmessage() {
		return emailmessage;
	}
	public void setEmailmessage(String emailmessage) {
		this.emailmessage = emailmessage;
	}
	public String getEmailstatusmessage() {
		return emailstatusmessage;
	}
	public void setEmailstatusmessage(String emailstatusmessage) {
		this.emailstatusmessage = emailstatusmessage;
	}
	public int getEmailstatus() {
		return emailstatus;
	}
	public void setEmailstatus(int emailstatus) {
		this.emailstatus = emailstatus;
	}
	
		
	
}
