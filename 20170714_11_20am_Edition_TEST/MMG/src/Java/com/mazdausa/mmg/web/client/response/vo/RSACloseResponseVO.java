package com.mazdausa.mmg.web.client.response.vo;

public class RSACloseResponseVO {
	
	private String status;
	private String description;
	private String successRequestIds;
	private String failedRequestIds;
	
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
	public String getSuccessRequestIds() {
		return successRequestIds;
	}
	public void setSuccessRequestIds(String successRequestIds) {
		this.successRequestIds = successRequestIds;
	}
	public String getFailedRequestIds() {
		return failedRequestIds;
	}
	public void setFailedRequestIds(String failedRequestIds) {
		this.failedRequestIds = failedRequestIds;
	}
	
	
}
