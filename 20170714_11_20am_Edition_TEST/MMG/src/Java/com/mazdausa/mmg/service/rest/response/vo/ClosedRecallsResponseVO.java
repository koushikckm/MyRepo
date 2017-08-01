package com.mazdausa.mmg.service.rest.response.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Recall_Status_Closed_Data")
public class ClosedRecallsResponseVO {

	private List<ClosedRecalls> closedRecallList;

	@XmlElement(name="Recall_Status_Closed")
	public List<ClosedRecalls> getClosedRecallList() {
		return closedRecallList;
	}

	public void setClosedRecallList(List<ClosedRecalls> closedRecallList) {
		this.closedRecallList = closedRecallList;
	}
	
	
}
