package com.mazdausa.mmg.service.rest.response.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Vehicle_Recall_Data")
public class VehiclesRecallResponseVO {

   
	private List<VehiclesRecall> vehicalsList;

	@XmlElement(name="Vehicle_Recall")
	public List<VehiclesRecall> getVehicalsList() {
		return vehicalsList;
	}

	public void setVehicalsList(List<VehiclesRecall> vehicalsList) {
		this.vehicalsList = vehicalsList;
	}
	
}
