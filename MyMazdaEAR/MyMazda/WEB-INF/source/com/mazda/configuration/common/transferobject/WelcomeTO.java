package com.mazda.configuration.common.transferobject;

import java.io.Serializable;
import java.util.List;



public class WelcomeTO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int year;
	private List<ModelTO> models;
	
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public List<ModelTO> getModels() {
		return models;
	}
	public void setModels(List<ModelTO> models) {
		this.models = models;
	}
	
		
}
