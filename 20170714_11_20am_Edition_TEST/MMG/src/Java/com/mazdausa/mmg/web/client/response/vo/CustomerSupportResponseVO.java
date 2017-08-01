package com.mazdausa.mmg.web.client.response.vo;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * This value object, represents the Customer Support Response, that is being sent to the client.
 * @author Anilk
 */
@XmlRootElement
public class CustomerSupportResponseVO {

	private String phone,email,allowemail,status,crashlog,message;
	
	//new variables for SSG feature 
	private String ssgfile,resourcefile,ssgfilever,resourcefilever;
	//new variables for updated SSG service
	private String imagebaseurl,videobaseurl;
	
	//MMG Q4 new variable for force upgrade
	private String newVersion;
	
	//MMG Q4 new variable for jci link
	private String jciurl;
	
	
	@Override
	public String toString() {
		return "CustomerSupportResponseVO [ssgfile=" + ssgfile
				+ ", imagebaseurl=" + imagebaseurl + ", videobaseurl="
				+ videobaseurl + "]";
	}
	

	
	public String getJciurl() {
		return jciurl;
	}

	public void setJciurl(String jciurl) {
		this.jciurl = jciurl;
	}

	public String getNewVersion() {
		return newVersion;
	}

	public void setNewVersion(String newVersion) {
		this.newVersion = newVersion;
	}

	public String getImagebaseurl() {
		return imagebaseurl;
	}

	public void setImagebaseurl(String imagebaseurl) {
		this.imagebaseurl = imagebaseurl;
	}

	public String getVideobaseurl() {
		return videobaseurl;
	}

	public void setVideobaseurl(String videobaseurl) {
		this.videobaseurl = videobaseurl;
	}

	public String getAllowemail() {
		return allowemail;
	}

	public void setAllowemail(String allowemail) {
		this.allowemail = allowemail;
	}

	public String getSsgfilever() {
		return ssgfilever;
	}

	public void setSsgfilever(String ssgfilever) {
		this.ssgfilever = ssgfilever;
	}

	public String getResourcefilever() {
		return resourcefilever;
	}

	public void setResourcefilever(String resourcefilever) {
		this.resourcefilever = resourcefilever;
	}

	public String getSsgfile() {
		return ssgfile;
	}

	public void setSsgfile(String ssgfile) {
		this.ssgfile = ssgfile;
	}

	public String getResourcefile() {
		return resourcefile;
	}

	public void setResourcefile(String resourcefile) {
		this.resourcefile = resourcefile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCrashlog() {
		return crashlog;
	}

	public void setCrashlog(String crashlog) {
		this.crashlog = crashlog;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
