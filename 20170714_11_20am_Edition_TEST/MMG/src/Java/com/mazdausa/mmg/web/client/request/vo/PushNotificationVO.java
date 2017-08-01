package com.mazdausa.mmg.web.client.request.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Value Object, holds the details of the push notification request sent by the client.
 * @author anilk
 *
 */

@XmlRootElement
public class PushNotificationVO {
	
	private String pushtitle,pushmessage,devicetype;
	private String status;
	

	public PushNotificationVO() {
		super();
	}

	
	
	public PushNotificationVO(String status) {
		super();
		this.status = status;
	}



	//setter/getter
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getPushtitle() {
		return pushtitle;
	}


	public void setPushtitle(String pushtitle) {
		this.pushtitle = pushtitle;
	}


	public String getPushmessage() {
		return pushmessage;
	}


	public void setPushmessage(String pushmessage) {
		this.pushmessage = pushmessage;
	}


	public String getDevicetype() {
		return devicetype;
	}


	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
	


	
}
