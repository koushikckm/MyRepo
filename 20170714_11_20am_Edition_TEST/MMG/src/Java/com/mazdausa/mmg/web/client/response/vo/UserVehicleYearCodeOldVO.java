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
public class UserVehicleYearCodeOldVO {

    private String year;
    private List<String> models;
    private long lastchangetime;

    public List<String> getModels() {
        return models;
    }

    public void setModels(List<String> models) {
        this.models = models;
    }
  

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public long getLastchangetime() {
        return lastchangetime;
    }

    public void setLastchangetime(long lastchangetime) {
        this.lastchangetime = lastchangetime;
    }

    
    

    

}
