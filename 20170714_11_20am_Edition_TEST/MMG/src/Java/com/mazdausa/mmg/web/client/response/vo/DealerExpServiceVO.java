package com.mazdausa.mmg.web.client.response.vo;

import java.util.List;

public class DealerExpServiceVO {
	
	private List<String> resourceList;
	private String htmlResource;
	private String version;
	private String status;
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DealerExpServiceVO(){}
	
	public DealerExpServiceVO(List<String> resourceList, String htmlResource,
			String version) {
		super();
		this.resourceList = resourceList;
		this.htmlResource = htmlResource;
		this.version = version;
	}
	
	public List<String> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<String> resourceList) {
		this.resourceList = resourceList;
	}
	public String getHtmlResource() {
		return htmlResource;
	}
	public void setHtmlResource(String htmlResource) {
		this.htmlResource = htmlResource;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	

	
}
