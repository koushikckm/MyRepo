/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import java.util.List;

/**
 * This Value Object, will be responsible for representing the Value Objects,  that will be sent in response to Vehicle YEAR CODe REQUEST.
 * @author PankajB
 * @version 1.0
 */
public class UserVehicleYearCodeResponseOldVO {

     private String error,description,status;
     private long lastchangetime;
     private List<UserVehicleYearCodeOldVO> models;

    public long getLastchangetime() {
        return lastchangetime;
    }

    public void setLastchangetime(long lastchangetime) {
        this.lastchangetime = lastchangetime;
    }

    public List<UserVehicleYearCodeOldVO> getModels() {
        return models;
    }

    public void setModels(List<UserVehicleYearCodeOldVO> models) {
        this.models = models;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


     

}
