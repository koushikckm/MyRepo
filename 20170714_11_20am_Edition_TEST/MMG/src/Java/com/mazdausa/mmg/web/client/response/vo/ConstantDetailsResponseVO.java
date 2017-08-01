/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import java.util.List;

/**
 * This Value Object, will be responsible for representing the Value Objects,  that will be sent in response to constant REQUEST.
 * @author PankajKh
 * @version 1.0
 */
public class ConstantDetailsResponseVO {

    private String error,description,status;
    private List<ConstantResponseVO> constantDetails;
    private VehicleScanTimeOutResponseVO scanDetails;

    public VehicleScanTimeOutResponseVO getScanDetails() {
        return scanDetails;
    }

    public void setScanDetails(VehicleScanTimeOutResponseVO scanDetails) {
        this.scanDetails = scanDetails;
    }

    public List<ConstantResponseVO> getConstantDetails() {
        return constantDetails;
    }

    public void setConstantDetails(List<ConstantResponseVO> constantDetails) {
        this.constantDetails = constantDetails;
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
