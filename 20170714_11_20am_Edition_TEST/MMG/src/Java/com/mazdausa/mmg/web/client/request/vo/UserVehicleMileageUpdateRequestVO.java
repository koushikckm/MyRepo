/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.request.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Request Value Object will contain all the details for updating mileage that are being sent by the client.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement
public class UserVehicleMileageUpdateRequestVO {

    private String error, drivingConditions, vin, calcDate, mileage, milesPerDay;

    public String getCalcDate() {
        return calcDate;
    }

    public void setCalcDate(String calcDate) {
        this.calcDate = calcDate;
    }

    public String getDrivingConditions() {
        return drivingConditions;
    }

    public void setDrivingConditions(String drivingConditions) {
        this.drivingConditions = drivingConditions;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getMilesPerDay() {
        return milesPerDay;
    }

    public void setMilesPerDay(String milesPerDay) {
        this.milesPerDay = milesPerDay;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    
}
