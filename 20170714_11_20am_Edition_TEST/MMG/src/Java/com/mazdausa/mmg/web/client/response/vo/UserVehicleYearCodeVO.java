/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import java.util.List;

/**
 * This is the value object, which will contain the Vehicle Year and the number of codes for the  vehicle.
 * @author PankajB
 */
public class UserVehicleYearCodeVO {

    private String year;
   // private List<String> model;

	//Added for Q4 release @28-11-2013
    private List<UserVehicleModelTrimVO> models;
    

//    public List<String> getModel() {
//		return model;
//	}
//
//	public void setModel(List<String> model) {
//		this.model = model;
//	}

	public List<UserVehicleModelTrimVO> getModels() {
		return models;
	}

	public void setModels(List<UserVehicleModelTrimVO> models) {
		this.models = models;
	}

	public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    

}
