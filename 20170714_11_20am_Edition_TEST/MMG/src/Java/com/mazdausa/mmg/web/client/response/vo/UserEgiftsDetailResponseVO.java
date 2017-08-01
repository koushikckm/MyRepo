package com.mazdausa.mmg.web.client.response.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.service.soap.response.vo.GetEgiftsDetailsVehicleVO;

@XmlRootElement
public class UserEgiftsDetailResponseVO {
	
	private String error,description,count,status;
	
	private List<GetEgiftsDetailsVehicleVO> egifts;
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<GetEgiftsDetailsVehicleVO> getEgifts() {
		return egifts;
	}

	public void setEgifts(List<GetEgiftsDetailsVehicleVO> egifts) {
		this.egifts = egifts;
	}

}
