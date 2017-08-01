package com.mazdausa.mmg.web.client.request.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RSACloseRequestVO {

	private String requestIds;
	
	public String getRequestIds() {
		return requestIds;
	}
	public void setRequestIds(String requestIds) {
		this.requestIds = requestIds;
	}
	
	
}
