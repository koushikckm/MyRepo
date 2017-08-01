package com.mazda.configuration.common.transferobject;

import java.io.Serializable;
import java.util.List;

public class ModelTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String modelCode;
	private String modelName;
	private List<String> trims;
	
	
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public List<String> getTrims() {
		return trims;
	}
	public void setTrims(List<String> trims) {
		this.trims = trims;
	}

}
