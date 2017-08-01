package com.mazda.configuration.common.transferobject;

import java.io.File;
import java.io.Serializable;

/**
 * registration parameters
 * @author Yogendra.gupta
 * @since 26-10-2014
 * @version 1.0
 */
public class RegistrationTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;  
	private int id;
	private String customerId;
	private String title;
	private String vtitle;
	private String firstName;
	private String lastName;
	private String email;
	private String confEmail;
	private String password;
	private String confPassword;
	private String city;
	private String state;
	private String street1address;
	private String street2address;
	private String zip;
	private String flag;
	private String addVehicleFlag;
	private String mobilePhone;
	private boolean validEmail;
	private boolean login=false;
	private int loginType;
	private String remember;
	private File uploadedFile;
	private String uploadedFileContentType;
	private String uploadedFileFileName;
	private String phoneType;
	
	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	/*
	 * vehical variable
	 * **/
	private String carlineDesc;
	private String vin;
	private String carlineCode;
	private String mdlCode;
	private String trim;
	private String engine;
	private String intColorDesc;
	private String extColorCode;
	private String extColorDesc;
	private String transmission;
	private int mdlYear;
	private int mileage;
	private String milesPerDay;
	private int schedule;
	private String drivingCondition;
	
	/*dealer Detail*/
	private String serviceDealerID;
	private String dealerName;
	private String dealerCity;
	private String dealerZip;
	private String dealerAddress;
	private String dealerState;
	private String dealerStreet;
	private String dealerPhone;
	private String dealerDistance;
	private String editUpdateFlag;
	private String serviceUrl;
	private String expServiceFlag;
	
	private String imgName;
	
	/*public void reset(){
		title="";
		fname="";
		lname="";
		email="";
		confEmail="";
		password="";
		confPassword="";
		cityName="";
		stateName="";
		streetAd1="";
		streetAd2="";
		zipCode="";
		phoneNumber="";
		validEmail=false;
		vin="";
		schedule=0;
		carlineDesc="";
		mileage=0;
		milesPerDay="";
		mdlYear=0;
		carlineCode="";
	}*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfEmail() {
		return confEmail;
	}

	public void setConfEmail(String confEmail) {
		this.confEmail = confEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	

	public boolean isValidEmail() {
		return validEmail;
	}

	public void setValidEmail(boolean validEmail) {
		this.validEmail = validEmail;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	

	public int getSchedule() {
		return schedule;
	}

	public void setSchedule(int schedule) {
		this.schedule = schedule;
	}

	public String getCarlineDesc() {
		return carlineDesc;
	}

	public void setCarlineDesc(String carlineDesc) {
		this.carlineDesc = carlineDesc;
	}

	public String getCarlineCode() {
		return carlineCode;
	}

	public void setCarlineCode(String carlineCode) {
		this.carlineCode = carlineCode;
	}

	public String getTrim() {
		return trim;
	}

	public void setTrim(String trim) {
		this.trim = trim;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getIntColorDesc() {
		return intColorDesc;
	}

	public void setIntColorDesc(String intColorDesc) {
		this.intColorDesc = intColorDesc;
	}

	public String getExtColorDesc() {
		return extColorDesc;
	}

	public void setExtColorDesc(String extColorDesc) {
		this.extColorDesc = extColorDesc;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public int getMdlYear() {
		return mdlYear;
	}

	public void setMdlYear(int mdlYear) {
		this.mdlYear = mdlYear;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	

	public String getMilesPerDay() {
		return milesPerDay;
	}

	public void setMilesPerDay(String milesPerDay) {
		this.milesPerDay = milesPerDay;
	}

	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet1address() {
		return street1address;
	}

	public void setStreet1address(String street1address) {
		this.street1address = street1address;
	}

	public String getStreet2address() {
		return street2address;
	}

	public void setStreet2address(String street2address) {
		this.street2address = street2address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getServiceDealerID() {
		return serviceDealerID;
	}

	public void setServiceDealerID(String serviceDealerID) {
		this.serviceDealerID = serviceDealerID;
	}

	public String getDealerAddress() {
		return dealerAddress;
	}

	public void setDealerAddress(String dealerAddress) {
		this.dealerAddress = dealerAddress;
	}

	public String getDealerPhone() {
		return dealerPhone;
	}

	public void setDealerPhone(String dealerPhone) {
		this.dealerPhone = dealerPhone;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getMdlCode() {
		return mdlCode;
	}

	public void setMdlCode(String mdlCode) {
		this.mdlCode = mdlCode;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the dealerName
	 */
	public String getDealerName() {
		return dealerName;
	}

	/**
	 * @param dealerName the dealerName to set
	 */
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	/**
	 * @return the dealerStreet
	 */
	public String getDealerStreet() {
		return dealerStreet;
	}

	/**
	 * @param dealerStreet the dealerStreet to set
	 */
	public void setDealerStreet(String dealerStreet) {
		this.dealerStreet = dealerStreet;
	}

	/**
	 * @return the dealerDistance
	 */
	public String getDealerDistance() {
		return dealerDistance;
	}

	/**
	 * @param dealerDistance the dealerDistance to set
	 */
	public void setDealerDistance(String dealerDistance) {
		this.dealerDistance = dealerDistance;
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

	public String getDealerState() {
		return dealerState;
	}

	public void setDealerState(String dealerState) {
		this.dealerState = dealerState;
	}

	public String getAddVehicleFlag() {
		return addVehicleFlag;
	}

	public void setAddVehicleFlag(String addVehicleFlag) {
		this.addVehicleFlag = addVehicleFlag;
	}

	/**
	 * @return the editUpdateFlag
	 */
	public String getEditUpdateFlag() {
		return editUpdateFlag;
	}

	/**
	 * @param editUpdateFlag the editUpdateFlag to set
	 */
	public void setEditUpdateFlag(String editUpdateFlag) {
		this.editUpdateFlag = editUpdateFlag;
	}

	/**
	 * @return the drivingCondition
	 */
	public String getDrivingCondition() {
		return drivingCondition;
	}

	/**
	 * @param drivingCondition the drivingCondition to set
	 */
	public void setDrivingCondition(String drivingCondition) {
		this.drivingCondition = drivingCondition;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getExpServiceFlag() {
		return expServiceFlag;
	}

	public void setExpServiceFlag(String expServiceFlag) {
		this.expServiceFlag = expServiceFlag;
	}

	/**
	 * @return the remember
	 */
	public String getRemember() {
		return remember;
	}

	/**
	 * @param remember the remember to set
	 */
	public void setRemember(String remember) {
		this.remember = remember;
	}

	public String getVtitle() {
		return vtitle;
	}

	public void setVtitle(String vtitle) {
		this.vtitle = vtitle;
	}

	public String getExtColorCode() {
		return extColorCode;
	}

	public void setExtColorCode(String extColorCode) {
		this.extColorCode = extColorCode;
	}

	/**
	 * @return the imgName
	 */
	public String getImgName() {
		return imgName;
	}

	/**
	 * @param imgName the imgName to set
	 */
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public File getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(File uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getUploadedFileContentType() {
		return uploadedFileContentType;
	}

	public void setUploadedFileContentType(String uploadedFileContentType) {
		this.uploadedFileContentType = uploadedFileContentType;
	}

	public String getUploadedFileFileName() {
		return uploadedFileFileName;
	}

	public void setUploadedFileFileName(String uploadedFileFileName) {
		this.uploadedFileFileName = uploadedFileFileName;
	}

	
	
}
