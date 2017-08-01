/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.soap.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This VO will actually contain the Mileage Details of the vehicle
 * @author PankajB
 * @version 1.0
 */
public class MileageDetailsVO {

    private String error,drivingConditions,vin,calcDate,mileage,milesPerDay;

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_RESPONSE_PARAMETER_MILEAGEDETAILS_MILEAGE_CALCDATE)
    public String getCalcDate() {
        return calcDate;
    }

    public void setCalcDate(String calcDate) {
        this.calcDate = calcDate;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_RESPONSE_PARAMETER_MILEAGEDETAILS_MILEAGE_DRIVINGCONDITIONS)
    public String getDrivingConditions() {
        return drivingConditions;
    }

    public void setDrivingConditions(String drivingConditions) {
        this.drivingConditions = drivingConditions;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_RESPONSE_PARAMETER_MILEAGEDETAILS_MILEAGE_ERROR)
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_RESPONSE_PARAMETER_MILEAGEDETAILS_MILEAGE_MILEAGE)
    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_RESPONSE_PARAMETER_MILEAGEDETAILS_MILEAGE_MILESPERDAY)
    public String getMilesPerDay() {
        return milesPerDay;
    }

    public void setMilesPerDay(String milesPerDay) {
        this.milesPerDay = milesPerDay;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_RESPONSE_PARAMETER_MILEAGEDETAILS_MILEAGE_VIN)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    
}
