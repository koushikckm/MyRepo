package com.mazdausa.mmg.web.client.request.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RelcallAlertsJobStatusVO {
	
	@XmlElement(name="status")
	private String status;// Success,Error
	@XmlElement(name="description")
	private String description;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
