package com.mazdausa.mmg.web.client.response.vo;

import java.util.List;

public class RSAResponseVO {

	private List<UserVehicleRSAResponseVO> requests;
	private String status;
	private String description;
	
	public List<UserVehicleRSAResponseVO> getRequests() {
		return requests;
	}

	public void setRequests(List<UserVehicleRSAResponseVO> requests) {
		this.requests = requests;
	}

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
