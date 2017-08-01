package com.mazdausa.mmg.web.client.response.vo;

public class UserVehicleRSAResponseVO {

	private String rsaRequestStatus;
	private RSALinkDetailsVO link;
	private String status;
	private String description;
	private String rsaRequestId;
	private String problem;
	private String location;
	private String time;
	private String vin;
	
	public String getRsaRequestStatus() {
		return rsaRequestStatus;
	}
	public void setRsaRequestStatus(String rsaRequestStatus) {
		this.rsaRequestStatus = rsaRequestStatus;
	}
	public RSALinkDetailsVO getLink() {
		return link;
	}
	public void setLink(RSALinkDetailsVO link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRsaRequestId() {
		return rsaRequestId;
	}
	public void setRsaRequestId(String rsaRequestId) {
		this.rsaRequestId = rsaRequestId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	
}
